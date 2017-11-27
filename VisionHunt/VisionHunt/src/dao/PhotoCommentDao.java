package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.PhotoComment;
import util.DBUtil;

public class PhotoCommentDao {
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public void save(PhotoComment photoComment) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("INSERT INTO photocomment (photoId,userId,photoCommContent,photoCommDate) VALUES(?,?,?,?)");
		pstm.setLong(1, photoComment.getPhotoId());
		pstm.setLong(2, photoComment.getUserId());
		pstm.setString(3, photoComment.getPhotoCommContent());
		pstm.setString(4, photoComment.getPhotoCommDate());
		
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
	}
 
}
