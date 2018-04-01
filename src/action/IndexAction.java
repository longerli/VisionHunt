package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import bean.Photo;
import bean.PhotoSpecial;
import bean.User;
import service.IndexService;
import service.PhotoSpecialService;

@WebServlet("/homepage")
public class IndexAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private PhotoSpecialService photoSpecialService;
	private IndexService service;
	
	@Override
	public void init() throws ServletException {
		service=new IndexService();
		photoSpecialService=new PhotoSpecialService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			List<Photo> photoList = service.showPhoto();
			List<User> userList = new ArrayList<User>();
			List<PhotoSpecial> photoSpecialList = photoSpecialService.findAll();
			
			Random random = new Random();
			
			int speIndex=random.nextInt(photoSpecialList.size());
			PhotoSpecial photoSpecial = photoSpecialList.get(speIndex);
			String speContent=photoSpecial.getSpeContent();
			String speImgurl=StringUtils.substringBetween(speContent, "src=\"", "\"");
			
			
			for(Photo photo:photoList){
				User user = service.showUser(photo.getUserId());
				userList.add(user);
			}
			
			map.put("photoList", photoList);
			map.put("userList", userList);
			map.put("photoSpecial", photoSpecial);
			map.put("speImgUrl",speImgurl);
			
			req.setAttribute("map", map);
			req.getRequestDispatcher("/JSP/index.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
