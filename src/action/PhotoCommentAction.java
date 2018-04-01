package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoComment;
import service.PhotoCommentServcie;

@WebServlet("/commentphoto")

public class PhotoCommentAction extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	private PhotoCommentServcie photoCommentService;
	
	public void init() throws ServletException {
		photoCommentService=new PhotoCommentServcie();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		if("comment".equals(cmd)){
			this.savePhotoComment(req, resp);
		}
		if("commentFromTopic".equals(cmd)){
			this.saveCommentFromTopic(req, resp);
		}
	}
	

	public void savePhotoComment(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		long photoId=Long.parseLong(req.getParameter("photoId"));
		long userId=Long.parseLong(req.getParameter("userId"));
		String photoCommContent = req.getParameter("commContent");
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String photoCommDate = df.format(new Date());
		PhotoComment photoComment = new PhotoComment(photoId,userId,photoCommContent,photoCommDate);
		PrintWriter out = resp.getWriter();
		
		try {
			Boolean saveflag = photoCommentService.savePhotoComment(photoComment);
			if(saveflag==true){
				out.write("评论成功");
			}else{
				out.write("评论失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
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
			photoCommentService.savePhotoComment(photoComment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
