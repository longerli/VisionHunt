package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Photo;
import util.DBUtil;

public class PhotoDao {
	
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	
	public void save(Photo photo) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("INSERT INTO photo "
				+ "(userId,photoPath,photoLabel,photoDes,photoDate) VALUES(?,?,?,?,?)");
		
		pstm.setLong(1, photo.getUserId());
		pstm.setString(2, photo.getPhotoPath());
		pstm.setString(3, photo.getPhotoLabel());
		pstm.setString(4, photo.getPhotoDes());
		pstm.setString(5, photo.getPhotoDate());
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
	}
	
	public List<Photo> findAll() throws SQLException{
		
		List<Photo> photoList=new ArrayList<Photo>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photo ORDER BY photoId DESC");
		pstm.executeQuery();
		rs=pstm.executeQuery();
		
		while(rs.next()){
			Photo photo = new Photo(rs.getLong(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			photo.setPhotoId(rs.getLong(1));
			photoList.add(photo);
		}
		
		DBUtil.close(conn, pstm, rs);
		return photoList;
		
	}
	
	public List<Photo> searchByLabel(String label) throws SQLException{
		List<Photo> photoList = new ArrayList<Photo>();
		
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photo WHERE photoLabel LIKE '%"+
				label+"%' ORDER BY photoId DESC");
		rs=pstm.executeQuery();
		while(rs.next()){
			Photo photo = new Photo(rs.getLong(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			photo.setPhotoId(rs.getLong(1));
			photoList.add(photo);
			
		}
		
		DBUtil.close(conn, pstm, rs);
		return photoList;
	}
	
}
