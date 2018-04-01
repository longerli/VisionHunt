package iservice;

import java.util.List;

import bean.PhotoSpecial;

public interface IPhotoSpecialService {
	
	public Boolean savePhotoSpecial(PhotoSpecial photoSpecial);
	public List<PhotoSpecial> findAll();
	public PhotoSpecial findOne(Long speId);

}
