package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoTransmit;
import dao.PhotoTransmitDao;
@WebServlet("/transmitphoto")
public class PhotoTransmitAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private PhotoTransmitDao dao;
	
	@Override
	public void init() throws ServletException {
		dao=new PhotoTransmitDao();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		if("transmit".equals(cmd)){
			this.transmit(req, resp);
		}
		if("save".equals(cmd)){
			this.save(req, resp);
		}
		
	}
	
	protected void transmit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		long photoId=Long.parseLong(req.getParameter("photoId"));
		String beTransedUserName=req.getParameter("beTransedUserName");
		req.getSession().setAttribute("PHOTOID_IN_SESSION", photoId);
		req.getSession().setAttribute("TRANSEDUSERNAME_IN_SESSION", beTransedUserName);
		resp.sendRedirect("/JSP/TransmitPhoto.jsp");
		
	}
	
	protected void save(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		long photoId=Long.parseLong(req.getParameter("photoId"));
		long userId=Long.parseLong(req.getParameter("userId"));
		String transDes=req.getParameter("transDes");
		
		PhotoTransmit phototransmit = new PhotoTransmit(photoId,userId,transDes);
		
		try {
			dao.save(phototransmit);
			resp.sendRedirect("/homepage");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
