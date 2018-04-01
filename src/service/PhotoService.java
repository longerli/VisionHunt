package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Photo;
import bean.PhotoCollect;
import bean.PhotoComment;
import bean.User;
import dao.PhotoCollectDao;
import dao.PhotoCommentDao;
import dao.PhotoDao;
import dao.PhotoTransmitDao;
import dao.UserDao;
import iservice.IPhotoService;
import util.PageList;

public class PhotoService implements IPhotoService{
	private PhotoDao photoDao;
	private PhotoCommentDao photoCommentDao;
	private PhotoCollectDao photoCollectDao;
	private PhotoTransmitDao transmitDao;
	private UserDao userDao;
	
	public Boolean savePhoto(Photo photo) throws SQLException{
		photoDao=new PhotoDao();
		Boolean saveflag = photoDao.insertPhoto(photo);
		return saveflag;
	}
	
	public List<Photo> searchJoinedTopic(String label,long topId) throws SQLException{
		photoDao=new PhotoDao();
		return photoDao.searchJoinedTopic(label,topId);
	}
	
	
	public List<Photo> findAll() throws SQLException{
		photoDao=new PhotoDao();
		List<Photo> photoList = photoDao.findAll();
		return photoList;
	}
	
	public PageList separatePage(Integer currentPage,long userId) throws SQLException{
		photoDao = new PhotoDao();
		PageList pagelist = photoDao.separatePage(currentPage,userId);
		return pagelist;
	}
	
	public Map<String,Object> selectPhotoDetailsByPhotoId(Long photoId) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		photoDao=new PhotoDao();
		photoCommentDao=new PhotoCommentDao();
		photoCollectDao=new PhotoCollectDao();
		userDao=new UserDao();
		Photo photo = photoDao.selectPhotoById(photoId);
		List<PhotoComment> commList = photoCommentDao.selectPhotoCommentByPhotoId(photoId);
		List<PhotoCollect> collectList = photoCollectDao.selectPhotoCollectByPhotoId(photoId);
		List<User> commUserList = new ArrayList<User>();
		List<User> collectUserList = new ArrayList<User>();
		for(PhotoComment photoComment:commList){
			 User commUser = userDao.findOne(photoComment.getUserId());
			 commUserList.add(commUser);
		}
		for(PhotoCollect collect:collectList){
			User collectUser = userDao.findOne(collect.getUserId());
			collectUserList.add(collectUser);
		}
		map.put("photo", photo);
		map.put("commList", commList);
		map.put("collectList", collectList);
		map.put("commUserList",commUserList);
		map.put("collectUserList",collectUserList);
		return map;
	}
	
	
	public Boolean editPhoto(Photo photo) throws SQLException{
		photoDao=new PhotoDao();
		Boolean editflag = photoDao.editPhoto(photo);
		return editflag;
	}
	
	public Boolean deletePhoto(Long photoId) {
		photoDao=new PhotoDao();
		photoCollectDao=new PhotoCollectDao();
		photoCommentDao=new PhotoCommentDao();
		transmitDao=new PhotoTransmitDao();
		boolean deleteflag=true;
		try {
			photoDao.deletePhoto(photoId);
			photoCollectDao.deleteCollectByPhotoId(photoId);
			photoCommentDao.deleteCommentByPhotoId(photoId);
			transmitDao.deleteTransmitByPhotoId(photoId);
			return deleteflag;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			deleteflag=false;
			return deleteflag;
		}
	}
	
	public List<Photo> searchPhotoByLabel(String photoLabel){
		photoDao=new PhotoDao();
		List<Photo> photoList = photoDao.queryPhotoByLabel(photoLabel);
		return photoList;
	}

}
