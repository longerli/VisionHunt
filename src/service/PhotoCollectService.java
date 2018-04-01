package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Photo;
import bean.PhotoCollect;
import dao.PhotoCollectDao;
import dao.PhotoDao;
import iservice.IPhotoCollectService;
import util.PageList;

public class PhotoCollectService implements IPhotoCollectService{
	
	private PhotoCollectDao dao;
	
	public String savePhotoCollect(PhotoCollect photoCollect) throws SQLException{
		dao=new PhotoCollectDao();
		if(photoCollect==null){
			return "收藏失败";
		}
		String saveflag = dao.insertPhotoCollect(photoCollect);
		return saveflag;
	}
	
	public Map<String,Object> userCollectSeparatePage(Long userId,Integer currentPage) throws SQLException{
		dao=new PhotoCollectDao();
		PageList collectPageList = dao.userCollectSeparatePage(userId,currentPage);
		PhotoDao photoDao = new PhotoDao();
		List<Photo> photoList = new ArrayList<Photo>();
		Map<String,Object> map = new HashMap<String,Object>();
		for(Object obj:collectPageList.getListData()){
			PhotoCollect collect = (PhotoCollect)obj;
			Photo photo = photoDao.selectPhotoById(collect.getPhotoId());
			photoList.add(photo);
		}
		map.put("collectPageList", collectPageList);
		map.put("photoList", photoList);
		return map;
		
	}
	
	public Boolean cancelCollect(Long colId){
		dao=new PhotoCollectDao();
		Boolean deleteflag = dao.deleteCollectById(colId);
		return deleteflag;
	}

}
