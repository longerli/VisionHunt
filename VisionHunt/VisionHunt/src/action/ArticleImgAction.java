package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/articleimgaction")
@MultipartConfig()
public class ArticleImgAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private final static ObjectMapper objm = new ObjectMapper();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws 	ServletException, IOException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8"); 
		upload.setSizeMax(1024 * 1024*5); 
		
		Map<String , Object> map = new HashMap<String , Object>(); //json map
		try {
			List<String> data = new ArrayList<String>(); //存放路径
			List<FileItem> items = upload.parseRequest(req); //得到请求信息
			map.put("errno", 0);
			for (FileItem item : items) {
				String fileName=FilenameUtils.getName(item.getName());
				String ext = FilenameUtils.getExtension(fileName);
				String dir = req.getServletContext().getRealPath("/ArticleUploadImages");
				String randomFileName=UUID.randomUUID().toString()+"."+ext;
				File file = new File(dir,randomFileName);
				item.write(file);
				String basePath="/ArticleUploadImages/"+randomFileName;
				data.add(basePath);
			}
			map.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			/*map.put("errno", 1);
			map.put("data", new ArrayList());*/ 
		}
		//map转JSON字符串
	    String imgUrl = objm.writeValueAsString(map);
	    resp.setContentType("text/text;charset=utf-8");
	    PrintWriter out = resp.getWriter();
	    out.print(imgUrl); //返回地址
	    out.flush();
	    out.close();
	}
	
	
}
