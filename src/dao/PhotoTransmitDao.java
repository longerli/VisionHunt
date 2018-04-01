package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.PhotoTransmit;
import idao.IPhotoTransmitDao;
import util.DBUtil;

public class PhotoTransmitDao implements IPhotoTransmitDao{
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	public String insertPhotoTransmit(PhotoTransmit photoTransmit) {
		conn=DBUtil.getConnect();
		List<Long> list = new ArrayList<Long>();
		try {
			PreparedStatement ptm=conn.prepareStatement("SELECT photoId FROM phototransmit WHERE userId=?");
			ptm.setLong(1, photoTransmit.getUserId());
			rs=ptm.executeQuery();
			while(rs.next()){
				long photoId=rs.getLong(1);
				list.add(photoId);
			}
			if(list.contains(photoTransmit.getPhotoId())){
				return "该照片已转发";
			}else{
				pstm=conn.prepareStatement("INSERT INTO phototransmit(photoId,userId,transDes) VALUES(?,?,?)" );
				pstm.setLong(1, photoTransmit.getPhotoId());
				pstm.setLong(2, photoTransmit.getUserId());
				pstm.setString(3, photoTransmit.getTransDes());
				pstm.executeUpdate();
				
				DBUtil.close(conn, pstm, null);
				return "转发成功";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "转发失败";
		}
		
	}
	
	public void deleteTransmitByPhotoId(Long photoId) throws SQLException{
		try {
			conn=DBUtil.getConnect();
			conn.setAutoCommit(false);
			pstm=conn.prepareStatement("DELETE FROM phototransmit WHERE photoId=?");
			pstm.setLong(1, photoId);
			pstm.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
		}finally{
			
			conn.setAutoCommit(true);
			DBUtil.close(conn, pstm, null);
		}
		
	}

}
