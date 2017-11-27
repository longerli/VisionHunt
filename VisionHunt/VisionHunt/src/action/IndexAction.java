package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Photo;
import bean.User;
import service.IndexService;

@WebServlet("/homepage")
public class IndexAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private IndexService service;
	
	@Override
	public void init() throws ServletException {
		service=new IndexService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		try {
			List<Photo> photoList = service.showPhoto();
			List<User> userList = new ArrayList<User>();
			for(Photo photo:photoList){
				User user = service.showUser(photo.getUserId());
				userList.add(user);
			}
			req.setAttribute("photoList", photoList);
			req.setAttribute("userList", userList);
			req.getRequestDispatcher("/JSP/index.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
