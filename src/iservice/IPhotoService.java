package iservice;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import bean.Photo;
import util.PageList;

public interface IPhotoService {
	
	public Boolean savePhoto(Photo photo) throws SQLException;
	public List<Photo> searchJoinedTopic(String label,long topId) throws SQLException;
	public List<Photo> findAll() throws SQLException;
	public PageList separatePage(Integer currentPage,long userId) throws SQLException;
	public Map<String,Object> selectPhotoDetailsByPhotoId(Long photoId) throws SQLException;
	public Boolean editPhoto(Photo photo) throws SQLException;
	public Boolean deletePhoto(Long photoId);
	public List<Photo> searchPhotoByLabel(String PhotoLabel);

}
