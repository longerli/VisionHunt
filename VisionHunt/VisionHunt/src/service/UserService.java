package service;
import java.sql.SQLException;
import java.util.List;

import bean.User;
import dao.UserDao;
import util.DBUtil;

public class UserService {

	private UserDao dao;
	
	public void save(User user) {
		dao=new UserDao();
		
		try {
			dao.save(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User checkLogin(String userEmail,String userPwd) throws SQLException{
		dao=new UserDao();
		if(userEmail!=null&&userPwd!=null){
			User user = dao.getLogin(userEmail, userPwd);
			return user;
		}
		return null;
	}
	
	public void Update(User user) throws SQLException{
		dao=new UserDao();
		dao.Update(user);
	}
	
	public void editPwd(String userNewPwd, long userId) throws SQLException{
		dao=new UserDao();
		dao.editPwd(userNewPwd, userId);
		
	}
	
}
