package iservice;

import java.sql.SQLException;

import bean.PhotoComment;

public interface IPhotoCommentService {
	
	public Boolean savePhotoComment(PhotoComment photoComment) throws SQLException;

}
