package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import bean.User;
import service.PhotoService;
import service.UserService;
import util.LogicException;
import util.PageList;

@WebServlet("/useraction")
@MultipartConfig(maxFileSize=1024*1024*8,maxRequestSize=1024*1024*10)
public class UserAction extends HttpServlet{


	private static final long serialVersionUID = 1L;
	private static MultipartConfig config = PhotoAction.class.getAnnotation(MultipartConfig.class);
	private final static String[] ALLOWED_IMAGE_TYPE={"png","jpeg","jpg","gif","bmp","tga"};
	private UserService service;
	private PhotoService photoService;
	@Override
	public void init() throws ServletException {
		service=new UserService();
		
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		
		if("save".equals(cmd)){
			this.saveUser(req, resp);
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
				this.editAccount(req, resp);
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
		if("userMainPage".equals(cmd)){
			try {
				this.userMainPage(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if("forgetPwd".equals(cmd)){
			this.forgetPwd(req, resp);
		}
		
	}
	
	public void saveUser(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charzet=utf-8");
		
		try {
			
			String userName = req.getParameter("userName");
			String userPwd = req.getParameter("userPwd");
			String userEmail = req.getParameter("userEmail");
			String userTel = req.getParameter("userTel");
			User user = new User(userName,userPwd,userEmail,userTel);
			Part headPart = req.getPart("file");
			String header = headPart.getHeader("Content-Disposition");
			String fileName = StringUtils.substringBetween(header, "filename=\"", "\"");
			String ext=FilenameUtils.getExtension(fileName);
			//判断文件格式是否正确
			if(!Arrays.asList(ALLOWED_IMAGE_TYPE).contains(ext)){
				throw new LogicException("只支持图片类型包括png,jpeg,jpg,gif,tga,bmp");
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

			String saveflag=service.saveUser(user);
			
			PrintWriter out = resp.getWriter();
			out.write(saveflag);
			
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

		resp.setContentType("text/html;charset=utf-8");
		String userEmail=req.getParameter("email");
		String userPwd=req.getParameter("password");
		PrintWriter out = resp.getWriter();
		User user = service.checkLogin(userEmail, userPwd);
		
		if(user==null){
			out.write("用户名或密码错误");
		}else{
			out.write("登陆成功");
			HttpSession session = req.getSession();
			session.setAttribute("USER_IN_SESSION", user);
			session.setMaxInactiveInterval(7200);
			
		}
		
	}
	
	public void editAccount(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, SQLException {
		
		resp.setContentType("text/html;charset=utf-8");
		
		String userId=req.getParameter("userId");
		String userName=req.getParameter("userName");
		String userPwd=req.getParameter("userPwd");
		String userEmail=req.getParameter("userEmail");
		String userTel=req.getParameter("userTel");
		
		User user = new User(userName,userPwd,userEmail,userTel);
		PrintWriter out = resp.getWriter();
		user.setUserId(Long.parseLong(userId));
		
		Boolean updateflag = service.Update(user);
		if(updateflag==true){
			out.write("修改成功");
		}else{
			out.write("修改失败");
		}
	}
	
	public void editPwd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, SQLException{

		resp.setContentType("text/html;charset=utf-8");
		long userId=Long.parseLong(req.getParameter("userId"));
		String NewPwd=req.getParameter("newPwd");
		PrintWriter out = resp.getWriter();
		Boolean editPwdFlag = service.editPwd(NewPwd, userId);
		if(editPwdFlag==true){
			out.write("修改成功");
		}else{
			out.write("修改失败");
		}
	}
	
	public void userMainPage(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, SQLException{

		photoService=new PhotoService();

		Map<String,Object> map = new HashMap<String,Object>();
		Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
		Long userId=Long.parseLong(req.getParameter("userId"));
		PageList pList = photoService.separatePage(currentPage,userId);
		//PageList aPageList = articleService.separatePage(currentPage);
		
		map.put("plist", pList);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/JSP/UserMainPage.jsp").forward(req, resp);
	}
	
	public void forgetPwd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String userEmail=req.getParameter("userEmail");
		String newPwd=req.getParameter("newPwd");
		PrintWriter out = resp.getWriter();
		String editflag = service.forgetPwd(userEmail, newPwd);
		out.write(editflag);
	
	}
}
