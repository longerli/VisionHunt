package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Topic;
import idao.ITopicDao;
import util.DBUtil;

public class TopicDao implements ITopicDao{
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public Boolean insertTopic(Topic topic){
		conn=DBUtil.getConnect();
		Boolean saveflag=true;
		try {
			pstm=conn.prepareStatement("INSERT INTO topic (userId,topLabel,topDes,topDate) VALUES(?,?,?,?) ");
			
			pstm.setLong(1, topic.getUserId());
			pstm.setString(2, topic.getTopLabel());
			pstm.setString(3, topic.getTopDes());
			pstm.setString(4, topic.getTopDate());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return saveflag;
		} catch (SQLException e) {
			// TODO: handle exception
			saveflag=false;
			return saveflag;
		}
		
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
		DBUtil.close(conn, pstm, rs);
		return topicList;
		
	}
	
	public Topic findOne(long topId) {
		conn=DBUtil.getConnect();
		try {
			pstm=conn.prepareStatement("SELECT * FROM topic WHERE topId=?");
			pstm.setLong(1, topId);
			pstm.executeQuery();
			rs=pstm.executeQuery();
			Topic topic = new Topic();
			while(rs.next()){
				//Topic topic = new Topic(rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5));
				topic.setUserId(rs.getLong(2));
				topic.setTopLabel(rs.getString(3));
				topic.setTopDes(rs.getString(4));
				topic.setTopDate(rs.getString(5));
				topic.setTopId(rs.getLong(1));
				
			}
			DBUtil.close(conn, pstm, rs);
			return topic;
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	/*public PageList userTopicSeparatePage(Integer currentPage,long userId) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM topic WHERE userId=?");
		pstm.setLong(1, userId);
		rs=pstm.executeQuery();
		rs.last();
		Integer totalCount=rs.getRow();
		Integer pageSize=15;
		Integer totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		Integer nextPage=currentPage+1<=totalPage?currentPage+1:totalPage;
		Integer prePage=currentPage-1>=1?currentPage-1:1;
		List<Topic> list = new ArrayList<>();
		PreparedStatement ptm = conn.prepareStatement("SELECT * FROM topic WHERE userId=? ORDER BY topId DESC LIMIT "
				+(currentPage-1)*pageSize+","+pageSize);
		ptm.setLong(1, userId);
		ResultSet s = ptm.executeQuery();
		while(s.next()){
			Topic topic = new Topic(s.getLong(2),s.getString(3),s.getString(4),s.getString(5));
			list.add(topic);
		}
		PageList pageList = new PageList(list,totalCount,currentPage,pageSize,prePage,nextPage,totalPage);
		return pageList;
	}
*/
}
