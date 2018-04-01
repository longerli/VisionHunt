package iservice;

import java.sql.SQLException;
import java.util.Map;

import bean.PhotoCollect;

public interface IPhotoCollectService {
	
	public String savePhotoCollect(PhotoCollect photoCollect) throws SQLException;
	//public List<PhotoCollect> findAll() throws SQLException;
	public Map<String,Object> userCollectSeparatePage(Long userId,Integer currentPage) throws SQLException;
	public Boolean cancelCollect(Long colId);

}
