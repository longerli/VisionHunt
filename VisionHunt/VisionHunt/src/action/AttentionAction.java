package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attention;
import bean.User;
import service.AttentionService;

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
		//System.out.println(cmd);
		if("addAttention".equals(cmd)){
			this.save(req, resp);
		}

	}
	
	public void save(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Long beAttUserId=Long.parseLong(req.getParameter("beAttUserId"));
		User user =  (User) req.getSession().getAttribute("USER_IN_SESSION");
		Attention attention = new Attention(user.getUserId(),beAttUserId);
		if(attention.getUserId()==attention.getBeAttUserId()){
			out.write("不用关注自己啊！");
			return;
		}
		String saveflag = service.save(attention);
		out.write(saveflag);
		out.close();
	}

}
