package service;

import java.sql.SQLException;
import java.util.List;

import bean.PhotoSpecial;
import dao.PhotoSpecialDao;
import iservice.IPhotoSpecialService;

public class PhotoSpecialService implements IPhotoSpecialService{
	
	private PhotoSpecialDao dao;
	
	
	public Boolean savePhotoSpecial(PhotoSpecial photoSpecial){
		dao=new PhotoSpecialDao();
		Boolean saveflag = dao.insertPhotoSpecial(photoSpecial);
		return saveflag;
	}
	
	
	public List<PhotoSpecial> findAll(){
		dao=new PhotoSpecialDao();
		List<PhotoSpecial> list=null;
		try {
			list = dao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public PhotoSpecial findOne(Long speId){
		dao=new PhotoSpecialDao();
		PhotoSpecial photoSpecial = dao.findOne(speId);
		return photoSpecial;
	}

}
