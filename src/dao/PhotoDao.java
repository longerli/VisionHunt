package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Photo;
import idao.IPhotoDao;
import util.DBUtil;
import util.PageList;

public class PhotoDao implements IPhotoDao{
	
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	
	public Boolean insertPhoto(Photo photo){
		conn=DBUtil.getConnect();
		boolean saveflag=true;
		try {
			pstm=conn.prepareStatement("INSERT INTO photo "
					+ "(userId,photoPath,photoLabel,photoDes,photoDate,topId) VALUES(?,?,?,?,?,?)");
			pstm.setLong(1, photo.getUserId());
			pstm.setString(2, photo.getPhotoPath());
			pstm.setString(3, photo.getPhotoLabel());
			pstm.setString(4, photo.getPhotoDes());
			pstm.setString(5, photo.getPhotoDate());
			pstm.setLong(6, photo.getTopId());
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return saveflag;
		} catch (SQLException e) {
			// TODO: handle exception
			saveflag=false;
			return saveflag;
		}
		
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
			photo.setTopId(rs.getLong(7));
			photoList.add(photo);
		}
		DBUtil.close(conn, pstm, rs);
		return photoList;
		
	}
	
	public List<Photo> searchJoinedTopic(String label,long topId) throws SQLException{
		List<Photo> photoList = new ArrayList<Photo>();
		
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photo WHERE photoLabel LIKE '%"+
				label+"%' OR topId=? ORDER BY photoId DESC");
		pstm.setLong(1, topId);
		rs=pstm.executeQuery();
		while(rs.next()){
			Photo photo = new Photo(rs.getLong(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			photo.setPhotoId(rs.getLong(1));
			photo.setTopId(rs.getLong(7));
			photoList.add(photo);
			
		}
		
		DBUtil.close(conn, pstm, rs);
		return photoList;
	}
	
	
	public PageList separatePage(Integer currentPage,long userId) throws SQLException{
		conn=DBUtil.getConnect();
		int totalCount=0;
		Integer pageSize=15;
		pstm=conn.prepareStatement("SELECT * FROM photo WHERE userId=?");
		pstm.setLong(1, userId);
		rs=pstm.executeQuery();
		rs.last();
		totalCount=rs.getRow();//获得查询结果总数
		DBUtil.close(null, pstm, rs);
		//总页
		Integer totalPage = totalCount % pageSize==0 ? totalCount / pageSize : totalCount / pageSize+1;
		//上一页
		Integer prePage = currentPage-1>=1 ? currentPage-1 : 1;
		//下一页
		Integer nextPage = currentPage + 1<=totalPage ? currentPage+1:totalPage;
		
		//获得当前页的结果集
		List<Photo> list = new ArrayList<>();
		PreparedStatement ptm = conn.prepareStatement("SELECT * FROM photo WHERE userId=? ORDER BY photoId DESC LIMIT "
				+(currentPage-1)*pageSize+","+pageSize);
		ptm.setLong(1, userId);
		ResultSet s = ptm.executeQuery();
		while(s.next()){
			Photo photo = new Photo(s.getLong(2),s.getString(3),s.getString(4),s.getString(5),s.getString(6));
			photo.setPhotoId(s.getLong(1));
			photo.setTopId(s.getLong(7));
			list.add(photo);
		}
		
		DBUtil.close(conn, ptm, s);
		PageList pagelist = new PageList(list,totalCount,currentPage,pageSize,prePage,nextPage,totalPage);
		return pagelist;
	}
	
	public Photo selectPhotoById(Long photoId) throws SQLException{
		conn=DBUtil.getConnect();
		List<Photo> list = new ArrayList<Photo>();
		pstm=conn.prepareStatement("SELECT * FROM photo WHERE photoId=?");
		pstm.setLong(1, photoId);
		rs=pstm.executeQuery();
		while(rs.next()){
			Photo photo=new Photo(rs.getLong(2),rs.getString(3),
					rs.getString(4),rs.getString(5),rs.getString(6));
			photo.setPhotoId(rs.getLong(1));
			photo.setTopId(rs.getLong(7));
			list.add(photo);
		}
		return list.size()==1?list.get(0):null;
	}
	
	public Boolean editPhoto(Photo photo) {
		conn=DBUtil.getConnect();
		boolean editflag=true;
		try {
			pstm=conn.prepareStatement("UPDATE photo SET userId=?,photoPath=?,photoLabel=?,photoDes=?,photoDate=? WHERE photoId=?");
			pstm.setLong(1, photo.getUserId());
			pstm.setString(2, photo.getPhotoPath());
			pstm.setString(3, photo.getPhotoLabel());
			pstm.setString(4, photo.getPhotoDes());
			pstm.setString(5, photo.getPhotoDate());
			pstm.setLong(6, photo.getPhotoId());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return editflag;
		} catch (SQLException e) {
			// TODO: handle exception
			editflag=false;
			return editflag;
		}
		
	}
	
	
	public void deletePhoto(Long photoId) throws SQLException{
		try {
			conn=DBUtil.getConnect();
			conn.setAutoCommit(false);
			pstm=conn.prepareStatement("DELETE FROM photo WHERE photoId=?");
			pstm.setLong(1, photoId);
			pstm.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO: handle exception
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
			DBUtil.close(conn, pstm, null);
		}
		
	}
	
	public List<Photo> queryPhotoByLabel(String photoLabel){
		conn=DBUtil.getConnect();
		List<Photo> photoList = new ArrayList<Photo>();
		try {
			pstm=conn.prepareStatement("SELECT * FROM photo WHERE photoLabel LIKE '%"+photoLabel+"%' ORDER BY photoId DESC");
			rs=pstm.executeQuery();
			while(rs.next()){
				Photo photo = new Photo(rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				photo.setPhotoId(rs.getLong(1));
				photo.setTopId(rs.getLong(7));
				photoList.add(photo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return photoList;
	}
	
}
