package idao;

import java.sql.SQLException;
import java.util.List;

import bean.PhotoCollect;
import util.PageList;

public interface IPhotoCollectDao {
	
	public String insertPhotoCollect(PhotoCollect photoCollect);
	//public List<PhotoCollect> findAll() throws SQLException;
	public PageList userCollectSeparatePage(Long userId,Integer currentPage) throws SQLException;
	public List<PhotoCollect> selectPhotoCollectByPhotoId(Long photoId) throws SQLException;
	public void deleteCollectByPhotoId(Long photoId) throws SQLException;
	public Boolean deleteCollectById(Long colId);

}
