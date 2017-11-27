package service;

import java.sql.SQLException;
import java.util.List;

import bean.Photo;
import dao.PhotoDao;

public class PhotoService {
	private PhotoDao dao;
	
	public void save(Photo photo) throws SQLException{
		dao=new PhotoDao();
		dao.save(photo);
	}
	
	public List<Photo> searchByLabel(String label) throws SQLException{
		dao=new PhotoDao();
		return dao.searchByLabel(label);
	}

}
