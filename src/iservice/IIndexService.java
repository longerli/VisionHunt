package iservice;

import java.sql.SQLException;
import java.util.List;

import bean.Photo;
import bean.User;

public interface IIndexService {
	
	public List<Photo> showPhoto() throws SQLException;
	public User showUser(long userId) throws SQLException;

}
