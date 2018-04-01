package iservice;

import java.sql.SQLException;

import bean.User;

public interface IUserService {
	
	public String saveUser(User user) throws SQLException;
	public User checkLogin(String userEmail,String userPwd) throws SQLException;
	public Boolean Update(User user);
	public Boolean editPwd(String userNewPwd, long userId);
	public User selectUserById(Long userId) throws SQLException;
	public String forgetPwd(String userEmail,String newPwd);

}
