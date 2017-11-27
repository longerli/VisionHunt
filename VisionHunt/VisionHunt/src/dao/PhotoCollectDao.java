package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.PhotoCollect;
import util.DBUtil;

public class PhotoCollectDao {
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	public void save(PhotoCollect photocollect) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("INSERT INTO photocollect (userId,photoId) VALUES(?,?)");
		
		pstm.setLong(1, photocollect.getUserId());
		pstm.setLong(2, photocollect.getPhotoId());
		
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
	}
	

}
