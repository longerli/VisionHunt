package service;

import bean.Photo;
import bean.PhotoTransmit;
import dao.PhotoDao;
import dao.PhotoTransmitDao;
import iservice.IPhotoTransmitService;

public class PhotoTransmitService implements IPhotoTransmitService{
	
	private PhotoTransmitDao dao;
	private PhotoDao photoDao;
	
	public String savePhotoTransmit(PhotoTransmit photoTransmit){
		dao=new PhotoTransmitDao();
		photoDao=new PhotoDao();
		
		try {
			Photo photo = photoDao.selectPhotoById(photoTransmit.getPhotoId());
			photo.setUserId(photoTransmit.getUserId());
			Boolean insertflag = photoDao.insertPhoto(photo);
			if(insertflag==true){
				String saveflag = dao.insertPhotoTransmit(photoTransmit);
				return saveflag;
			}else{
				return "转发失败";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return "转发失败";
		}
		
	}

}
