package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoCollect;
import service.PhotoCollectService;

@WebServlet("/collectphoto")
public class PhotoCollectAction extends HttpServlet{

	private PhotoCollectService service;
	
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		service=new PhotoCollectService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		
		if("collect".equals(cmd)){
			this.save(req, resp);
		}
		
		
	}
	

	public void save(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		long userId=Long.parseLong(req.getParameter("userId"));
		long photoId=Long.parseLong(req.getParameter("photoId"));
		
		PhotoCollect photocollect = new PhotoCollect(userId,photoId);
		System.out.println(photocollect);
		try {
			service.save(photocollect);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.sendRedirect("/homepage");
		
		
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
	}

}
