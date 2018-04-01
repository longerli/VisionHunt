package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import idao.IUserDao;
import util.DBUtil;

public class UserDao implements IUserDao{
	
	Connection conn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	
	public String insertUser(User user){
		List<String> userEmailList=new ArrayList<String>();
		try {
			conn = DBUtil.getConnect();
			PreparedStatement ptm=conn.prepareStatement("SELECT userEmail FROM user");
			ResultSet s = ptm.executeQuery();
			while(s.next()){
				String userEmail = s.getString(1);
				userEmailList.add(userEmail);
				
			}
			DBUtil.close(null, ptm, s);
			if(userEmailList.contains(user.getUserEmail())){
				return "该邮箱以被注册";
			}
			pstm=conn.prepareStatement("INSERT INTO user (userName,userPwd,userEmail,userTel,userIcon) VALUES(?,?,?,?,?)");
			
			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getUserPwd());
			pstm.setString(3, user.getUserEmail());
			pstm.setString(4, user.getUserTel());
			pstm.setString(5, user.getUserIcon());
			
			pstm.executeUpdate();
			DBUtil.close(conn, pstm, null);
			return "注册成功";
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return "注册失败";
		}	
		
	}
	
	public Boolean Update(User user) {
		conn = DBUtil.getConnect();
		boolean updateflag=true;
		try {
			pstm=conn.prepareStatement("UPDATE user SET userName=?,userPwd=?,userEmail=?,userTel=? WHERE userId=?");
			pstm.setString(1,user.getUserName());
			pstm.setString(2,user.getUserPwd());
			pstm.setString(3,user.getUserEmail());
			pstm.setString(4,user.getUserTel());
			pstm.setLong(5,user.getUserId());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return updateflag;
		} catch (SQLException e) {
			// TODO: handle exception
			updateflag=false;
			return updateflag;
		}
		
		
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
	
	public Boolean editPwd(String userPwd,long userId) {
			conn=DBUtil.getConnect();
			boolean editPwdFlag=true;
			try {
				pstm=conn.prepareStatement("UPDATE user SET userPwd=? WHERE userId=?");
				pstm.setString(1, userPwd);
				pstm.setLong(2, userId);
				
				pstm.executeUpdate();
				
				DBUtil.close(conn, pstm, null);
				return editPwdFlag;
			} catch (SQLException e) {
				// TODO: handle exception
				editPwdFlag=false;
				return editPwdFlag;
			}
			
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
	
	public String forgetPwd(String userEmail,String newPwd) 
			throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT userEmail FROM user");
		List<String> list = new ArrayList<String>();
		rs=pstm.executeQuery();
		while(rs.next()){
			String email=rs.getString(1);
			list.add(email);
		}
		DBUtil.close(null, pstm, rs);
		if(list.contains(userEmail)){
			PreparedStatement ptm = conn.prepareStatement("UPDATE user SET userPwd=? WHERE userEmail=?");
			ptm.setString(1, newPwd);
			ptm.setString(2, userEmail);
			ptm.executeUpdate();
			
			DBUtil.close(conn, ptm, null);
			return "密码修改成功";
		}else{
			return "无此邮箱账号";
		}
		
	}

}
