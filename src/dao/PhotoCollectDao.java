package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.PhotoCollect;
import idao.IPhotoCollectDao;
import util.DBUtil;
import util.PageList;

public class PhotoCollectDao implements IPhotoCollectDao{
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	public String insertPhotoCollect(PhotoCollect photoCollect) {
		conn=DBUtil.getConnect();
		try {
			List<Long> photoIdList = new ArrayList<>();
			pstm=conn.prepareStatement("SELECT photoId FROM photocollect WHERE userId=?");
			
			pstm.setLong(1, photoCollect.getUserId());
			rs=pstm.executeQuery();
			while(rs.next()){
				photoIdList.add(rs.getLong(1));
			}
			if(photoIdList.contains(photoCollect.getPhotoId())){
				DBUtil.close(conn, pstm, rs);
				return "该照片以收藏";
			}else{
				pstm=conn.prepareStatement("INSERT INTO photocollect (userId,photoId) VALUES(?,?)");
				pstm.setLong(1, photoCollect.getUserId());
				pstm.setLong(2, photoCollect.getPhotoId());
				pstm.executeUpdate();
				DBUtil.close(conn, pstm, null);
				return "收藏成功";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			return "收藏失败";
		}
		
	}
	
	
	public PageList userCollectSeparatePage(Long userId,Integer currentPage) 
			throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photocollect WHERE userId=? ORDER BY colId DESC");
		pstm.setLong(1, userId);
		rs=pstm.executeQuery();
		rs.last();
		Integer totalCount = rs.getRow();
		DBUtil.close(null, pstm, rs);
		Integer pageSize=15;
		List<PhotoCollect> list = new ArrayList<>();
		Integer totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		Integer prePage = currentPage-1>=1?currentPage-1:1;
		Integer nextPage = currentPage+1<=totalPage?currentPage+1:totalPage;
		
		PreparedStatement ptm = conn.prepareStatement("SELECT * FROM photocollect WHERE userId=? ORDER BY colId DESC LIMIT "
				+(currentPage-1)*pageSize+","+pageSize);
		ptm.setLong(1, userId);
		
		ResultSet s = ptm.executeQuery();
		while(s.next()){
			PhotoCollect collect  = new PhotoCollect(s.getLong(2),s.getLong(3));
			collect.setColId(s.getLong(1));
			list.add(collect);
		}
		DBUtil.close(conn, ptm, s);
		PageList pagelist = new PageList(list,totalCount,currentPage,pageSize,prePage,nextPage,totalPage);
		return pagelist;
	}
	
	public List<PhotoCollect> selectPhotoCollectByPhotoId(Long photoId) throws SQLException{
		List<PhotoCollect> collectList = new ArrayList<PhotoCollect>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM photocollect WHERE photoId=?");
		pstm.setLong(1, photoId);
		rs=pstm.executeQuery();
		while(rs.next()){
			PhotoCollect photoCollect = new PhotoCollect(rs.getLong(2),rs.getLong(3));
			photoCollect.setColId(rs.getLong(1));
			collectList.add(photoCollect);
		}
		return collectList;
	}
	
	public void deleteCollectByPhotoId(Long photoId) throws SQLException{
		try {
			conn=DBUtil.getConnect();
			conn.setAutoCommit(false);
			pstm=conn.prepareStatement("DELETE FROM photocollect WHERE photoId=?");
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
	
	public Boolean deleteCollectById(Long colId){
		conn=DBUtil.getConnect();
		boolean deleteflag=true;
		try {
			pstm=conn.prepareStatement("DELETE FROM photocollect WHERE colId=?");
			pstm.setLong(1, colId);
			pstm.executeUpdate();
			DBUtil.close(conn, pstm, null);
			return deleteflag;
		} catch (SQLException e) {
			// TODO: handle exception
			deleteflag=false;
			return deleteflag;
		}
		
	}
}
