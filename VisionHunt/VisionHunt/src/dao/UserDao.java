package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import util.DBUtil;

public class UserDao {
	
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	
	public void save(User user) throws SQLException{
		
		conn = DBUtil.getConnect();
		
		pstm=conn.prepareStatement("INSERT INTO user (userName,userPwd,userEmail,userTel,userIcon) VALUES(?,?,?,?,?)");
		
		pstm.setString(1, user.getUserName());
		pstm.setString(2, user.getUserPwd());
		pstm.setString(3, user.getUserEmail());
		pstm.setString(4, user.getUserTel());
		pstm.setString(5, user.getUserIcon());
		
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
		
	}
	
	public void Update(User user) throws SQLException{
		conn = DBUtil.getConnect();
		
		pstm=conn.prepareStatement("UPDATE user SET userName=?,userPwd=?,userEmail=?,userTel=? WHERE userId=?");
		pstm.setString(1,user.getUserName());
		pstm.setString(2,user.getUserPwd());
		pstm.setString(3,user.getUserEmail());
		pstm.setString(4,user.getUserTel());
		pstm.setLong(5,user.getUserId());
		
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
		
	}
	
	public User getLogin(String userEmail,String userPwd) throws SQLException{
		List<User> list = new ArrayList<>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM user WHERE userPwd=? AND userEmail=?");
		pstm.setString(1, userPwd);
		pstm.setString(2, userEmail);
		pstm.executeQuery();
		rs=pstm.executeQuery();
		while(rs.next()){
			User user = new User(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			user.setUserId(rs.getLong(1));
			user.setUserIcon(rs.getString(6));
			list.add(user);
		}
		DBUtil.close(conn, pstm, rs);
		return list.size()==1?list.get(0):null;
		
		
	}
	
	public void editPwd(String userPwd,long userId) throws SQLException{
			conn=DBUtil.getConnect();
			pstm=conn.prepareStatement("UPDATE user SET userPwd=? WHERE userId=?");
			pstm.setString(1, userPwd);
			pstm.setLong(2, userId);
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
	}
	
	public User findOne(Long userId) throws SQLException{
		conn=DBUtil.getConnect();
		List<User> userList = new ArrayList<User>();
		pstm=conn.prepareStatement("SELECT * FROM user WHERE userId=?");
		pstm.setLong(1, userId);
		pstm.executeQuery();
		rs=pstm.executeQuery();
		
		while(rs.next()){
			User user = new User(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			user.setUserId(rs.getLong(1));
			user.setUserIcon(rs.getString(6));
			userList.add(user);
		}
		
		
		DBUtil.close(conn, pstm, rs);
		return userList.get(0);

	}

}
