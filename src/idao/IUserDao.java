package idao;

import java.sql.SQLException;

import bean.User;

public interface IUserDao {
	
	public String insertUser(User user) throws SQLException;
	public Boolean Update(User user);
	public User getLogin(String userEmail,String userPwd) throws SQLException;
	public Boolean editPwd(String userPwd,long userId);
	public User findOne(Long userId) throws SQLException;
	public String forgetPwd(String userEmail,String newPwd) throws SQLException;

}
