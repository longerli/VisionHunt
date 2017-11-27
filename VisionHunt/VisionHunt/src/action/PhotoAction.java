package action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

import bean.Photo;
import service.PhotoService;
import util.LogicException;

@WebServlet("/photoaction")
@MultipartConfig(maxFileSize=1024*1024*8,maxRequestSize=1024*1024*10)

public class PhotoAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static MultipartConfig config
    = PhotoAction.class.getAnnotation(MultipartConfig.class);
	
	private PhotoService service;
	
	@Override
	public void init() throws ServletException {
		service=new PhotoService();
	}
	
	//允许上传的图片类型
	private final static String[] ALLOWED_IMAGE_TYPE={"png","jpeg","jpg","gif","bmp","tga"};
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		if("upload".equals(cmd)){
			this.save(req, resp);
		}
	}
	
	
	protected void save(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		try{
			
			Photo photo = new Photo();
			photo.setUserId(Long.parseLong(req.getParameter("userId")));
			photo.setPhotoLabel(req.getParameter("photoLabel"));
			photo.setPhotoDes(req.getParameter("photoDes"));
			DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
			photo.setPhotoDate(df.format(new Date()));
			
			Part photoPart = req.getPart("photoUp");
			String header = photoPart.getHeader("Content-Disposition");
			String fileName = StringUtils.substringBetween(header, "filename=\"", "\"");
			String ext=FilenameUtils.getExtension(fileName);
			//判断上传图片类型
			if(!Arrays.asList(ALLOWED_IMAGE_TYPE).contains(ext)){
				throw new LogicException("只支持图片类型上传包括png,jpeg,jpg,gif,tga,bmp��ʽ");
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
			//System.out.println(randomFileName);
			photo.setPhotoPath("/Upload/"+randomFileName);
			//System.out.println(photo);
			service.save(photo);
			photoPart.write(file.getAbsolutePath());
			resp.sendRedirect("/homepage");
		}catch(LogicException e){
			String errorMessage = e.getMessage();
			req.setAttribute("errorMsg", errorMessage);
			req.getRequestDispatcher("/JSP/UploadPhoto.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
		
		
	protected void update(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
	}
	

}
