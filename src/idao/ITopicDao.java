package idao;

import java.sql.SQLException;
import java.util.List;

import bean.Topic;

public interface ITopicDao {
	
	public Boolean insertTopic(Topic topic);
	public List<Topic> findAll() throws SQLException;
	public Topic findOne(long topId) throws SQLException;
	
}
