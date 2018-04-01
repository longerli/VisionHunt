package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Photo;
import bean.Topic;
import bean.User;
import service.IndexService;
import service.PhotoService;
import service.TopicService;

@WebServlet("/topicaction")
public class TopicAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private TopicService service;
	private PhotoService photoService;
	private IndexService indexService;
	
	@Override
	public void init() throws ServletException {
		service=new TopicService();
		photoService=new PhotoService();
		indexService=new IndexService();
	}
	

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		if("show".equals(cmd)){
			this.showTopicList(req, resp);
		}
		if("save".equals(cmd)){
			this.saveTopic(req, resp);
		}
		
		if("showDetails".equals(cmd)){
			this.showDetails(req, resp);
		}
		
	}
	
	protected void saveTopic(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		long userId=Long.parseLong(req.getParameter("userId"));
		String topLabel=req.getParameter("topLabel");
		String topDes=req.getParameter("topDes");
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String topDate=df.format(new Date());
		PrintWriter out = resp.getWriter();
		Topic topic = new Topic(userId,topLabel,topDes,topDate);
		Boolean saveflag = service.saveTopic(topic);
		if(saveflag==true){
			out.write("发布成功");
		}else{
			out.write("发布失败");
		}
	}
	
	protected void showTopicList(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Topic> topicList=service.showTopicList();
			List<List<Photo>> list=new ArrayList<List<Photo>>();
			
			for(Topic topic:topicList){
				List<Photo> photoList = photoService.searchJoinedTopic(topic.getTopLabel(), topic.getTopId());
				list.add(photoList);
			}
			
			//req.setAttribute("topicList", topicList);
			
			map.put("topicList",topicList);
			map.put("list", list);
			req.setAttribute("map", map);
			req.getRequestDispatcher("/JSP/TopicBrowser.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	
	public void showDetails(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		long topId=Long.parseLong(req.getParameter("topId"));
		Map<String,Object> map = new HashMap<String,Object>();
		Topic topic = new Topic();
		topic=service.findOne(topId);
		map.put("topic", topic);
		try {
			List<Photo> photoList = photoService.searchJoinedTopic(topic.getTopLabel(),topic.getTopId());
			map.put("photoList", photoList);
			List<User> userList = new ArrayList<User>();
			for(Photo photo:photoList){
				User user = indexService.showUser(photo.getUserId());
				userList.add(user);
			}
			map.put("userList", userList);
			//System.out.println(map);
			req.setAttribute("map", map);
			req.getRequestDispatcher("/JSP/TopicDetails.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
