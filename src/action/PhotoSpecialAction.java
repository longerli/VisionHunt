package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PhotoSpecial;
import service.PhotoSpecialService;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/photospecialaction")
public class PhotoSpecialAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private PhotoSpecialService service;
	
	public void init() throws ServletException {
		service=new PhotoSpecialService();
	}
	
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		
		if("releaseSpecial".equals(cmd)){
			req.getRequestDispatcher("/WEB-INF/AdministratorView/ReleasePhotoSpecial.jsp").forward(req, resp);
		}
		if("savePhotoSpecial".equals(cmd)){
			this.savePhotoSpecial(req, resp);
		}
		if("browser".equals(cmd)){
			this.showPhotoSpecialList(req, resp);
		}
		if("showDetails".equals(cmd)){
			this.showDetails(req, resp);
		}
	}
	
	protected void showPhotoSpecialList(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Map<String,Object> map=new HashMap<String,Object>();
		List<PhotoSpecial> photoSpecialList = service.findAll();
		
		List<String> imgUrl = new ArrayList<String>();
		Map<Integer,String> showStr=new HashMap<Integer,String>();
		for(PhotoSpecial photospecial:photoSpecialList){
			String content=photospecial.getSpeContent();
			String imgurl=StringUtils.substringBetween(content, "src=\"", "\"");
			if(imgurl==null||"".equals(imgurl.trim())){
				String pattern = "[\u4e00-\u9fa5]+";
				Pattern pat = Pattern.compile(pattern);
				Matcher macher= pat.matcher(content);
				if(macher.find()){
					int start=macher.start();
					int end=macher.end();
					String mcontent=content.substring(start, end);
					String showContent=mcontent.substring(0, 30);
					showStr.put(photoSpecialList.indexOf(photospecial)+1,showContent);
				}
				
			}
			imgUrl.add(imgurl);
		}
		map.put("list", photoSpecialList);
		map.put("imgurl",imgUrl);
		map.put("textContent",showStr);
		req.setAttribute("map",map);
		req.getRequestDispatcher("/JSP/PhotoSpecialBrowser.jsp").forward(req, resp);
	}
	
	protected void showDetails(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/text;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Long speId=Long.parseLong(req.getParameter("speId"));
		PhotoSpecial photoSpecial = service.findOne(speId);
		ObjectMapper objm = new ObjectMapper();
		
		String data=objm.writeValueAsString(photoSpecial);
		out.write(data);
		out.flush();
		out.close();
	}
	
	protected void savePhotoSpecial(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String speTitle=req.getParameter("speTitle");
		String speContent=req.getParameter("speContent");
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String speDate=df.format(new Date());
		PhotoSpecial photoSpecial = new PhotoSpecial(speTitle,speContent,speDate);
		Boolean saveflag = service.savePhotoSpecial(photoSpecial);
		if(saveflag==true){
			out.write("发布成功");
		}else{
			out.write("发布失败");
		}
		
	}

}
