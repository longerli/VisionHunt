package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Attention;
import idao.IAttentionDao;
import util.DBUtil;
import util.PageList;

public class AttentionDao implements IAttentionDao{
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public String insertAttention(Attention attention) {
		List<Long> beAttUserIdList = new ArrayList<Long>();
		conn=DBUtil.getConnect();
		try {
			pstm=conn.prepareStatement("SELECT beAttUserId FROM attention WHERE userId=?");
			pstm.setLong(1, attention.getUserId());
			
			rs=pstm.executeQuery();
			while(rs.next()){
				beAttUserIdList.add(rs.getLong(1));
			}
			
			if(beAttUserIdList.contains(attention.getBeAttUserId())){
				return "该用户您已关注";
			}else{
				
				pstm=conn.prepareStatement("INSERT INTO attention (userId,beAttUserId) VALUES (?,?)");
				pstm.setLong(1, attention.getUserId());
				pstm.setLong(2, attention.getBeAttUserId());
				pstm.executeUpdate();
				DBUtil.close(conn, pstm, null);
				return "关注成功";
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "关注失败";
		}
		
	}
	
	public PageList userAttentionSeparatePage(Long userId,Integer currentPage) 
			throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM attention WHERE userId=?");
		pstm.setLong(1, userId);
		rs=pstm.executeQuery();
		rs.last();
		Integer totalCount=rs.getRow();
		DBUtil.close(null, pstm, rs);
		Integer pageSize=20;
		Integer totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		Integer prePage=currentPage-1>=1?currentPage-1:1;
		Integer nextPage=currentPage+1<=totalPage?currentPage+1:totalPage;
		
		PreparedStatement ptm=conn.prepareStatement("SELECT * FROM attention WHERE userId=? ORDER BY attId DESC LIMIT "
					+(currentPage-1)*pageSize+","+pageSize);
		
		ptm.setLong(1, userId);
		List<Attention> list = new ArrayList<>();
		ResultSet s = ptm.executeQuery();
		while(s.next()){
			Attention attention  = new Attention(s.getLong(2),s.getLong(3));
			attention.setAttId(s.getLong(1));
			list.add(attention);
		}
		DBUtil.close(conn, ptm, s);
		PageList pagelist = new PageList(list,totalCount,currentPage,pageSize,prePage,nextPage,totalPage);
		return pagelist;
	}
	
	public Boolean deleteAttentionById(Long attId){
		conn=DBUtil.getConnect();
		boolean deleteflag=true;
		try {
			pstm=conn.prepareStatement("DELETE FROM attention WHERE attId=?");
			pstm.setLong(1, attId);
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return deleteflag;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			deleteflag=false;
			return deleteflag;
		}
		
	}

}
