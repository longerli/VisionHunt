package action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import bean.User;
import service.UserService;
import util.LogicException;

@WebServlet("/useraction")
@MultipartConfig(maxFileSize=1024*1024*8,maxRequestSize=1024*1024*10)
public class UserAction extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private static MultipartConfig config = PhotoAction.class.getAnnotation(MultipartConfig.class);
	private final static String[] ALLOWED_IMAGE_TYPE={"png","jpeg","jpg","gif","bmp","tga"};
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		service=new UserService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		
		if("save".equals(cmd)){
			this.save(req, resp);
		}else if("login".equals(cmd)){
			try {
				this.login(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if("edit".equals(cmd)){
			try {
				this.edit(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("editPwd".equals(cmd)){
			try {
				this.editPwd(req,resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void save(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		try {
			resp.setContentType("text/html;charset=utf-8");
			String userName = req.getParameter("userName");
			String userPwd = req.getParameter("userPwd");
			String confirmPwd = req.getParameter("confirm");
			String userEmail = req.getParameter("userEmail");
			String userTel = req.getParameter("userTel");
			User user = new User(userName,userPwd,userEmail,userTel);
			
			Part headPart = req.getPart("userIcon");
			String header = headPart.getHeader("Content-Disposition");
			String fileName = StringUtils.substringBetween(header, "filename=\"", "\"");
			String ext=FilenameUtils.getExtension(fileName);
			//判断文件格式是否正确
			if(!Arrays.asList(ALLOWED_IMAGE_TYPE).contains(ext)){
				throw new LogicException("只支持图片类型包括png,jpeg,jpg,gif,tga,bmp��ʽ");
			}
			if(headPart.getSize()>config.maxFileSize()){
				throw new LogicException("单个上传文件不能超过8M");
			}
			if(req.getContentLengthLong()>config.maxRequestSize()){
				throw new LogicException("一次上传完整数据不能超过10M");
			}
			String dir = req.getServletContext().getRealPath("/HeadImages");
			String randomFileName=UUID.randomUUID().toString()+"."+ext;
			File file = new File(dir,randomFileName);
			user.setUserIcon("/HeadImages/"+randomFileName);
			headPart.write(file.getAbsolutePath());
			
			if(confirmPwd.equals(userPwd)){
				service.save(user);
				resp.sendRedirect("/JSP/Login.jsp");
			}else{
				resp.sendRedirect("/JSP/Regist.jsp");
			}
			
		}catch(LogicException e){
			String errorMessage = e.getMessage();
			req.setAttribute("errorMsg", errorMessage);
			req.getRequestDispatcher("/JSP/Regist.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
	public void login(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, SQLException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String userEmail=req.getParameter("email");
		String userPwd=req.getParameter("password");
		
		User user = service.checkLogin(userEmail, userPwd);
		if(user==null){
			req.setAttribute("errorMessage", "用户名或密码错误");
			req.getRequestDispatcher("/JSP/Login.jsp").forward(req, resp);
			return;
		}
		
		req.getSession().setAttribute("USER_IN_SESSION", user);
		resp.sendRedirect("/homepage");
		
	}
	
	public void edit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, SQLException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String userId=req.getParameter("userId");
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		String userEmail=req.getParameter("userEmail");
		String userTel=req.getParameter("userTel");
		
		User user = new User(userName,userPwd,userEmail,userTel);
		
		user.setUserId(Long.parseLong(userId));
		
		service.Update(user);
		resp.sendRedirect("/JSP/Login.jsp");
	}
	
	public void editPwd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, SQLException{
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		long userId=Long.parseLong(req.getParameter("userId"));
		String userNewPwd=req.getParameter("newPwd");
		String confirmPwd=req.getParameter("confirmPwd");
		
		if(userNewPwd!=confirmPwd){
			req.setAttribute("errormessage", "两次输入密码不一致");
			req.getRequestDispatcher("/JSP/EditPwd.jsp");
		}
		
		service.editPwd(userNewPwd, userId);
		resp.sendRedirect("/homepage");
	}
}
