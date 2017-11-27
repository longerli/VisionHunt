package action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoComment;
import service.PhotoCommentServcie;

@WebServlet("/commentphoto")

public class PhotoCommentAction extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	private PhotoCommentServcie service;
	
	public void init() throws ServletException {
		service=new PhotoCommentServcie();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		//System.out.println(cmd);
		if("comment".equals(cmd)){
			this.save(req, resp);
		}
		if("commentFromTopic".equals(cmd)){
			this.saveCommentFromTopic(req, resp);
		}
	}
	

	public void save(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		long photoId=Long.parseLong(req.getParameter("photoId"));
		long userId=Long.parseLong(req.getParameter("userId"));
		String photoCommContent = req.getParameter("commContent");
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String photoCommDate = df.format(new Date());
		PhotoComment photoComment = new PhotoComment(photoId,userId,photoCommContent,photoCommDate);
		
		
		try {
			service.save(photoComment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		resp.sendRedirect("homepage");
	}
	
	

	public void saveCommentFromTopic(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		long photoId=Long.parseLong(req.getParameter("photoId"));
		long userId=Long.parseLong(req.getParameter("userId"));
		String photoCommContent = req.getParameter("commContent");
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String photoCommDate = df.format(new Date());
		PhotoComment photoComment = new PhotoComment(photoId,userId,photoCommContent,photoCommDate);
		System.out.println(photoComment);
		try {
			service.save(photoComment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
