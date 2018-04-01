package idao;

import java.sql.SQLException;
import java.util.List;

import bean.PhotoSpecial;

public interface IPhotoSpecialDao {
	
	public Boolean insertPhotoSpecial(PhotoSpecial photoSpecial);
	public List<PhotoSpecial> findAll() throws SQLException;
	
	public PhotoSpecial findOne(Long speId);

}
