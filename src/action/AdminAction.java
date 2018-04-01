package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoSpecial;
import service.PhotoSpecialService;

@WebServlet("/adminhomepage")
public class AdminAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private PhotoSpecialService speService;
	
	public void init() throws ServletException {
		
		speService=new PhotoSpecialService();

	}
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<PhotoSpecial> speList = speService.findAll();
		req.setAttribute("speList", speList);
		req.getRequestDispatcher("/WEB-INF/AdministratorView/AdminHomePage.jsp").forward(req, resp);
	}
	
}
