package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.PhotoComment;
import idao.IPhotoCommentDao;
import util.DBUtil;

public class PhotoCommentDao implements IPhotoCommentDao{
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public Boolean insertPhotoComment(PhotoComment photoComment){
		conn=DBUtil.getConnect();
		boolean saveflag=true;
		try {
			pstm=conn.prepareStatement("INSERT INTO photocomment (photoId,userId,photoCommContent,photoCommDate) VALUES(?,?,?,?)");
			pstm.setLong(1, photoComment.getPhotoId());
			pstm.setLong(2, photoComment.getUserId());
			pstm.setString(3, photoComment.getPhotoCommContent());
			pstm.setString(4, photoComment.getPhotoCommDate());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return saveflag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			saveflag=false;
			return saveflag;
		}
		
	}
	
	public List<PhotoComment> selectPhotoCommentByPhotoId(Long photoId) throws SQLException{
		List<PhotoComment> commList = new ArrayList<PhotoComment>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photocomment WHERE photoId=?");
		pstm.setLong(1, photoId);
		rs=pstm.executeQuery();
		while(rs.next()){
			PhotoComment photoComment = new PhotoComment(rs.getLong(2),rs.getLong(3),rs.getString(4),rs.getString(5));
			photoComment.setPhotoCommId(rs.getLong(1));
			commList.add(photoComment);
		}
		DBUtil.close(conn, pstm, rs);
		return commList;
	}
	
	public void deleteCommentByPhotoId(Long photoId) throws SQLException{
		try {
			conn=DBUtil.getConnect();
			conn.setAutoCommit(false);
			pstm=conn.prepareStatement("DELETE FROM photocomment WHERE photoId=?");
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
