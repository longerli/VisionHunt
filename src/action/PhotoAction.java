package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Photo;
import service.PhotoService;
import util.LogicException;

@WebServlet("/photoaction")
@MultipartConfig(maxFileSize=1024*1024*8,maxRequestSize=1024*1024*10)

public class PhotoAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static MultipartConfig config
    = PhotoAction.class.getAnnotation(MultipartConfig.class);
	
	private PhotoService photoService;
	
	@Override
	public void init() throws ServletException {
		photoService=new PhotoService();
	}
	
	//允许上传的图片类型
	private final static String[] ALLOWED_IMAGE_TYPE={"png","jpeg","jpg","gif","bmp","tga"};
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		if("upload".equals(cmd)){
			this.uploadPhoto(req, resp);
		}
		if("participateinByPhoto".equals(cmd)){
			this.participateinTopicByPhoto(req, resp);
		}
		if("photoDetails".equals(cmd)){
			this.showPhotoDetails(req, resp);
		}
		if("editPhoto".equals(cmd)){
			this.editPhoto(req, resp);
		}
		
		if("deletePhoto".equals(cmd)){
			this.deletePhoto(req, resp);
		}
		if("searchPhoto".equals(cmd)){
			this.searchPhotoByLabel(req, resp);
		}
	}
	
	
	protected void uploadPhoto(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		try{
			Photo photo = new Photo();
			photo.setUserId(Long.parseLong(req.getParameter("userId")));
			photo.setPhotoLabel(req.getParameter("photoLabel"));
			photo.setPhotoDes(req.getParameter("photoDes"));
			String topId = req.getParameter("topId");
			if(!"".equals(topId.trim()) && topId!=null){
				photo.setTopId(Long.parseLong(topId));
			}else{
				photo.setTopId(0);
			}
			//System.out.println(photo.getTopId());
			DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
			photo.setPhotoDate(df.format(new Date()));
			
			Part photoPart = req.getPart("photoUp");
			String header = photoPart.getHeader("Content-Disposition");
			String fileName = StringUtils.substringBetween(header, "filename=\"", "\"");
			String ext=FilenameUtils.getExtension(fileName);
			//判断上传图片类型
			if(!Arrays.asList(ALLOWED_IMAGE_TYPE).contains(ext)){
				throw new LogicException("只支持图片类型上传包括png,jpeg,jpg,gif,tga,bmp");
			}
			if(photoPart.getSize()>config.maxFileSize()){
				throw new LogicException("单个上传文件不能8M");
			}
			if(req.getContentLengthLong()>config.maxRequestSize()){
				throw new LogicException("一次上传完整数据不能超过10M");
			}
			//确保文件名唯一，并将图片写到/Upload目录
			String dir = req.getServletContext().getRealPath("/Upload");
			String randomFileName=UUID.randomUUID().toString()+"."+ext;
			File file = new File(dir,randomFileName);
			photo.setPhotoPath("/Upload/"+randomFileName);
			
			Boolean saveflag = photoService.savePhoto(photo);
			if(saveflag==true){
				photoPart.write(file.getAbsolutePath());
				resp.sendRedirect("/homepage");
			}else{
				resp.sendRedirect("/JSP/UploadPhoto.jsp");
			}
			
		}catch(LogicException e){
			String errorMessage = e.getMessage();
			req.setAttribute("errorMsg", errorMessage);
			req.getRequestDispatcher("/JSP/UploadPhoto.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	protected void editPhoto(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		try {
			
			String photoDes=req.getParameter("photoDes");
			String photoLabel=req.getParameter("photoLabel");
			Long photoId=Long.parseLong(req.getParameter("photoId"));
			Long userId=Long.parseLong(req.getParameter("userId"));
			String originPhotoPath=req.getParameter("originPhotoPath");
			
			Photo photo=new Photo();
			DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
			photo.setPhotoDate(df.format(new Date()));
			photo.setPhotoDes(photoDes);
			photo.setPhotoLabel(photoLabel);
			photo.setPhotoId(photoId);
			photo.setUserId(userId);
			
			Part photoPart = req.getPart("file");
			if(photoPart!=null){
				String header = photoPart.getHeader("Content-Disposition");
				String fileName = StringUtils.substringBetween(header, "filename=\"", "\"");
				String originFileName1=StringUtils.substringAfter(originPhotoPath, "/");
				String originFileName2=StringUtils.substringAfter(originFileName1, "/");
				String ext=FilenameUtils.getExtension(fileName);
				if(!Arrays.asList(ALLOWED_IMAGE_TYPE).contains(ext)){
					throw new LogicException("只支持图片类型上传包括png,jpeg,jpg,gif,tga,bmp");
				}
				if(photoPart.getSize()>config.maxFileSize()){
					throw new LogicException("单个上传文件不能8M");
				}
				if(req.getContentLengthLong()>config.maxRequestSize()){
					throw new LogicException("一次上传完整数据不能超过10M");
				}
				
				String dir = req.getServletContext().getRealPath("/Upload");
				String randomFileName=UUID.randomUUID().toString()+"."+ext;
			
				File file = new File(dir,randomFileName);
				File originPhotoFile=new File(dir,originFileName2);
				boolean deleteFlag = originPhotoFile.delete();
				
				if(deleteFlag){
					photoPart.write(file.getAbsolutePath());
					photo.setPhotoPath("/Upload/"+randomFileName);
				}
			}else{
				photo.setPhotoPath(originPhotoPath);
			}
			
			Boolean editflag = photoService.editPhoto(photo);
			if(editflag==true){
				out.write("修改成功");
			}else{
				out.write("修改失败");
			}
			
		}catch(LogicException e){
			String errorMessage = e.getMessage();
			out.write(errorMessage);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	protected void participateinTopicByPhoto(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String,Object> map = new HashMap<String,Object>();
		String label=req.getParameter("topLabel");
		String topId=req.getParameter("topId");
		map.put("label",label);
		map.put("topId", topId);
		
		req.setAttribute("map", map);
		
		req.getRequestDispatcher("/JSP/UploadPhoto.jsp").forward(req, resp);
		
	}
	
	protected void showPhotoDetails(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Long photoId = Long.parseLong(req.getParameter("photoId"));
		ObjectMapper objm = new ObjectMapper();
		PrintWriter out = resp.getWriter();
		try {
			 Map<String, Object> map = photoService.selectPhotoDetailsByPhotoId(photoId);
			 resp.setContentType("text/text;charset=utf-8");
			 String data = objm.writeValueAsString(map);
			 out.write(data);
			 out.flush();
			 out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	protected void deletePhoto(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Long photoId=Long.parseLong(req.getParameter("photoId"));
		//String photoPath=req.getParameter("photoPath");
		/*String originFileName1=StringUtils.substringAfter(photoPath, "/");
		String originFileName2=StringUtils.substringAfter(originFileName1, "/");

		String dir = req.getServletContext().getRealPath("/Upload");
		File photoFile=new File(dir,originFileName2);
		boolean deleteFlag = photoFile.delete();*/
		
			Boolean deleteflag = photoService.deletePhoto(photoId);
			if(deleteflag==true){
				out.write("删除成功");
			}else{
				out.write("删除失败");
			}
			
		
		out.close();
	}
	
	protected void searchPhotoByLabel(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String photoLabel=req.getParameter("photoLabel");
		List<Photo> photoList = photoService.searchPhotoByLabel(photoLabel);
		ObjectMapper objm = new ObjectMapper();
		String data=objm.writeValueAsString(photoList);
		resp.setContentType("text/text;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(data);
		out.flush();
		out.close();
	}

}
