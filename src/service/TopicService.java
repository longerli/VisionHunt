package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Topic;
import dao.TopicDao;
import iservice.ITopicService;

public class TopicService implements ITopicService{
	
	private TopicDao dao;
	
	public List<Topic> showTopicList() throws SQLException{
		dao=new TopicDao();
		List<Topic> topicList = new ArrayList<Topic>();
		topicList=dao.findAll();
		return topicList;
		
	}
	
	
	public Boolean saveTopic(Topic topic){
		dao=new TopicDao();
		Boolean saveflag = dao.insertTopic(topic);
		return saveflag;
	}
	
	public Topic findOne(long topId){
		dao=new TopicDao();
		return dao.findOne(topId);
	}
	
}
