package service;

import java.sql.SQLException;

import bean.PhotoCollect;
import dao.PhotoCollectDao;

public class PhotoCollectService {
	
	private PhotoCollectDao dao;
	
	public void save(PhotoCollect photocollect) throws SQLException{
		dao=new PhotoCollectDao();
		System.out.println(photocollect);
		if(photocollect==null){
			return;
		}
		dao.save(photocollect);
	}

}
