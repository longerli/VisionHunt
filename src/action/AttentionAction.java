package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Attention;
import bean.User;
import service.AttentionService;
import service.UserService;
import util.PageList;

@WebServlet("/attentionaction")
public class AttentionAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private AttentionService service;
	
	public void init() throws ServletException {
		service=new AttentionService();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");

		if("addAttention".equals(cmd)){
			this.saveAttention(req, resp);
		}
		
		if("selectUserAttention".equals(cmd)){
			this.userAttentionSeparatePage(req, resp);
		}
		
		if("cancelAttention".equals(cmd)){
			this.cancelAttention(req, resp);
		}

	}
	
	public void saveAttention(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Long beAttUserId=Long.parseLong(req.getParameter("beAttUserId"));
		User user =  (User) req.getSession().getAttribute("USER_IN_SESSION");
		Attention attention = new Attention(user.getUserId(),beAttUserId);
		if(attention.getUserId()==attention.getBeAttUserId()){
			out.write("不用关注自己啊！");
			return;
		}
		String saveflag = service.saveAttention(attention);
		out.write(saveflag);
		out.close();
	}
	
	public void userAttentionSeparatePage(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Long userId=Long.parseLong(req.getParameter("userId"));
		Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
		UserService userService = new UserService();
		//System.out.println(userId+"--"+currentPage);
		List<User> userList = new ArrayList<>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			PageList attPageList = service.userAttentionSeparatePage(userId, currentPage);
			//List<Attention> attList = attPageList.getListData();
			for(Object obj:attPageList.getListData()){
				Attention attention=(Attention)obj;
				Long beAttUserId=attention.getBeAttUserId();
				User user=userService.selectUserById(beAttUserId);
				userList.add(user);
			}
			map.put("attPageList", attPageList);
			map.put("userList", userList);
			ObjectMapper objm = new ObjectMapper();
			String data = objm.writeValueAsString(map);
			resp.setContentType("text/text;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.write(data);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void cancelAttention(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Long attId=Long.parseLong(req.getParameter("attId"));
		PrintWriter out = resp.getWriter();
		Boolean deleteflag = service.cancelAttention(attId);
		if(deleteflag==true){
			out.write("取消关注成功");
			out.close();
		}else{
			out.write("取消关注失败");
			out.close();
		}
		
	}

}
