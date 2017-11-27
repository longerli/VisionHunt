package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.PhotoTransmit;
import util.DBUtil;

public class PhotoTransmitDao {
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public void save(PhotoTransmit phototransmit) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("INSERT INTO phototransmit(photoId,userId,transDes) VALUES(?,?,?)" );
		pstm.setLong(1, phototransmit.getPhotoId());
		pstm.setLong(2, phototransmit.getUserId());
		pstm.setString(3, phototransmit.getTransDes());
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
		
	}

}
