package service;
import java.sql.SQLException;
import bean.User;
import dao.UserDao;
import iservice.IUserService;

public class UserService implements IUserService{

	private UserDao dao;
	
	public String saveUser(User user) throws SQLException {
		dao=new UserDao();
		
		String saveflag = dao.insertUser(user);
		return saveflag;
		
	}
	
	public User checkLogin(String userEmail,String userPwd) throws SQLException{
		dao=new UserDao();
		if(userEmail!=null&&userPwd!=null){
			User user = dao.getLogin(userEmail, userPwd);
			return user;
		}
		return null;
	}
	
	public Boolean Update(User user) {
		dao=new UserDao();
		Boolean updateflag = dao.Update(user);
		return updateflag;
	}
	
	public Boolean editPwd(String userNewPwd, long userId){
		dao=new UserDao();
		Boolean editPwdFlag = dao.editPwd(userNewPwd, userId);
		return editPwdFlag;
		
	}
	
	public User selectUserById(Long userId) throws SQLException{
		dao=new UserDao();
		User user = dao.findOne(userId);
		return user;
	}
	
	public String forgetPwd(String userEmail,String newPwd) {
		dao=new UserDao();
		try {
			String editflag = dao.forgetPwd(userEmail, newPwd);
			return editflag;
		} catch (SQLException e) {
			e.printStackTrace();
			return "修改密码失败";
		}
		
	}
	
}
