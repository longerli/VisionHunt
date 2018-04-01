package idao;

import java.sql.SQLException;
import java.util.List;

import bean.PhotoComment;

public interface IPhotoCommentDao {
	
	public Boolean insertPhotoComment(PhotoComment photoComment);
	public List<PhotoComment> selectPhotoCommentByPhotoId(Long photoId) throws SQLException;
	public void deleteCommentByPhotoId(Long photoId) throws SQLException;

}
