package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Topic;
import util.DBUtil;

public class TopicDao {
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public void save(Topic topic) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("INSERT INTO topic (userId,topLabel,topDes,topDate) VALUES(?,?,?,?) ");
		
		pstm.setLong(1, topic.getUserId());
		pstm.setString(2, topic.getTopLabel());
		pstm.setString(3, topic.getTopDes());
		pstm.setString(4, topic.getTopDate());
		
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
	}
	
	
	public List<Topic> findAll() throws SQLException{
		
		List<Topic> topicList=new ArrayList<Topic>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM topic ORDER BY topId DESC");
		pstm.executeQuery();
		rs=pstm.executeQuery();
		while(rs.next()){
			Topic topic=new Topic(rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
			topic.setTopId(rs.getLong(1));
			topicList.add(topic);
		}
		
		return topicList;
		
	}
	
	public Topic findOne(long topId) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM topic WHERE topId=?");
		pstm.setLong(1, topId);
		pstm.executeQuery();
		rs=pstm.executeQuery();
		while(rs.next()){
			Topic topic = new Topic(rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
			topic.setTopId(rs.getLong(1));
			return topic;
		}
		return null;
	}

}
