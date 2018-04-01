package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Photo;
import bean.User;
import dao.PhotoDao;
import dao.UserDao;
import iservice.IIndexService;

public class IndexService implements IIndexService{
	
	private PhotoDao photoDao;
	private UserDao userDao;
	
	public List<Photo> showPhoto() throws SQLException{
		List<Photo> photoList=new ArrayList<Photo>();
		photoDao=new PhotoDao();
		photoList = photoDao.findAll();
		return photoList;
	}
	
	public User showUser(long userId) throws SQLException{
		userDao=new UserDao();
		User user=new User();
		user=userDao.findOne(userId);
		return user;
	}

}
