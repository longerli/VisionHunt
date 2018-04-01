package idao;

import java.sql.SQLException;
import java.util.List;

import bean.Photo;
import util.PageList;

public interface IPhotoDao {
	
	public Boolean insertPhoto(Photo photo);
	public List<Photo> findAll() throws SQLException;
	public List<Photo> searchJoinedTopic(String label,long topId) throws SQLException;
	public PageList separatePage(Integer currentPage,long userId) throws SQLException;
	public Photo selectPhotoById(Long photoId) throws SQLException;
	public Boolean editPhoto(Photo photo);
	public void deletePhoto(Long photoId) throws SQLException;
	public List<Photo> queryPhotoByLabel(String photoLabel);

}
