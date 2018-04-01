package iservice;

import java.sql.SQLException;
import java.util.List;

import bean.Topic;

public interface ITopicService {
	
	public List<Topic> showTopicList() throws SQLException;
	public Boolean saveTopic(Topic topic);
	public Topic findOne(long topId);

}
