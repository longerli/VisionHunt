package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Attention;
import util.DBUtil;

public class AttentionDao {
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	public String save(Attention attention) throws SQLException{
		List<Long> beAttUserIdList = new ArrayList<Long>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT beAttUserId FROM attention WHERE userId=?");
		pstm.setLong(1, attention.getUserId());
		
		rs=pstm.executeQuery();
		while(rs.next()){
			beAttUserIdList.add(rs.getLong(1));
		}
		//System.out.println(beAttUserIdList);
		if(beAttUserIdList.contains(attention.getBeAttUserId())){
			return "该用户您已关注";
		}else{
			
			pstm=conn.prepareStatement("INSERT INTO attention (userId,beAttUserId) VALUES (?,?)");
			pstm.setLong(1, attention.getUserId());
			pstm.setLong(2, attention.getBeAttUserId());
			pstm.executeUpdate();
		}
		
		DBUtil.close(conn, pstm, null);
		return "关注成功";
		
	}

}
