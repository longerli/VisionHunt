package service;

import java.sql.SQLException;

import bean.PhotoComment;
import dao.PhotoCommentDao;

public class PhotoCommentServcie {
	
	private PhotoCommentDao dao;
	
	public void save(PhotoComment photoComment) throws SQLException{
		dao=new PhotoCommentDao();
		dao.save(photoComment);
	}

}
