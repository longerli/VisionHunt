package service;

import java.sql.SQLException;

import bean.PhotoComment;
import dao.PhotoCommentDao;
import iservice.IPhotoCommentService;

public class PhotoCommentServcie implements IPhotoCommentService{
	
	private PhotoCommentDao dao;
	
	public Boolean savePhotoComment(PhotoComment photoComment) throws SQLException{
		dao=new PhotoCommentDao();
		Boolean saveflag = dao.insertPhotoComment(photoComment);
		return saveflag;
	}

}
