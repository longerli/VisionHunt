package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoTransmit;
import service.PhotoTransmitService;
@WebServlet("/transmitphoto")
public class PhotoTransmitAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private PhotoTransmitService service;
	
	@Override
	public void init() throws ServletException {
		service=new PhotoTransmitService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		if("transmit".equals(cmd)){
			this.transmit(req, resp);
		}
		if("save".equals(cmd)){
			this.savePhotoTransmit(req, resp);
		}
		
	}
	
	protected void transmit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		Map<String,Object> map = new HashMap<>();
		
		long photoId=Long.parseLong(req.getParameter("photoId"));
		String beTransedUserName=req.getParameter("beTransedUserName");
		
		
		map.put("photoId", photoId);
		map.put("beTransedUserName", beTransedUserName);
		
		req.setAttribute("map", map);
		
		req.getRequestDispatcher("/JSP/TransmitPhoto.jsp").forward(req, resp);
	}
	
	protected void savePhotoTransmit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		long photoId=Long.parseLong(req.getParameter("photoId"));
		long userId=Long.parseLong(req.getParameter("userId"));
		String transDes=req.getParameter("transDes");
		PrintWriter out = resp.getWriter();
		PhotoTransmit photoTransmit = new PhotoTransmit(photoId,userId,transDes);
		String saveflag = service.savePhotoTransmit(photoTransmit);
		
		out.write(saveflag);
		out.flush();
		out.close();
	
	}

}
