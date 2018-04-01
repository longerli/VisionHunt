package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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
			this.savePhotoCollect(req, resp);
		}
		if("selectUserCollect".equals(cmd)){
			this.userCollectSeparatePage(req, resp);
		}
		if("cancelCollect".equals(cmd)){
			this.cancelCollect(req, resp);
		}
		
	}
	

	public void savePhotoCollect(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		long userId=Long.parseLong(req.getParameter("userId"));
		long photoId=Long.parseLong(req.getParameter("photoId"));
		
		PhotoCollect photocollect = new PhotoCollect(userId,photoId); 
		PrintWriter out = resp.getWriter();
		try {
			String saveflag = service.savePhotoCollect(photocollect);
			out.write(saveflag);
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void cancelCollect(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Long colId=Long.parseLong(req.getParameter("colId"));
		Boolean deleteflag = service.cancelCollect(colId);
		if(deleteflag==true){
			out.write("删除成功");
			out.close();
		}else{
			out.write("删除失败");
			out.close();
		}
		
	}
	
	protected void userCollectSeparatePage(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Long userId=Long.parseLong(req.getParameter("userId"));
		Integer currentPage = Integer.parseInt(req.getParameter("currentPage"));
		Map<String,Object> map = new HashMap<String,Object>();
		PrintWriter out = resp.getWriter();
		try {
			map = service.userCollectSeparatePage(userId, currentPage);
			ObjectMapper objm = new ObjectMapper();
			resp.setContentType("text/text;charset=utf-8");
			out.write(objm.writeValueAsString(map));
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
