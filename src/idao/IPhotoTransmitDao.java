package idao;

import java.sql.SQLException;

import bean.PhotoTransmit;

public interface IPhotoTransmitDao {
	
	public String insertPhotoTransmit(PhotoTransmit phototransmit);
	public void deleteTransmitByPhotoId(Long photoId) throws SQLException;

}
