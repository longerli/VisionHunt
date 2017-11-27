package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Topic;
import dao.TopicDao;

public class TopicService {
	
	private TopicDao dao;
	
	public List<Topic> show() throws SQLException{
		dao=new TopicDao();
		List<Topic> topicList = new ArrayList<Topic>();
		topicList=dao.findAll();
		return topicList;
		
	}
	
	
	public void save(Topic topic){
		dao=new TopicDao();
		try {
			dao.save(topic);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Topic findOne(long topId){
		dao=new TopicDao();
		try {
			return dao.findOne(topId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
