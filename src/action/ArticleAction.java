package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Article;
import service.ArticleService;
import util.PageList;

@WebServlet("/articleaction")
public class ArticleAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ArticleService service;
	
	@Override
	public void init() throws ServletException {
			service=new ArticleService();
	}
	
	

	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String cmd=req.getParameter("cmd");
		if("saveArticle".equals(cmd)){
			this.saveArticle(req, resp);
		}
		if("joinedByArticle".equals(cmd)){
			this.joinedTopicByArticel(req, resp);
		}
		if("selectArticle".equals(cmd)){
			this.separatePage(req,resp);
		}
		if("articleDetails".equals(cmd)){
			this.articleDetails(req, resp);
		}
		
		if("editArticle".equals(cmd)){
			this.editArticle(req, resp);
		}
		if("deleteArticle".equals(cmd)){
			this.deleteArticle(req, resp);
		}
		if("reviewArticle".equals(cmd)){
			this.reviewArticle(req, resp);
		}

	}

	protected void saveArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String arContent=req.getParameter("arContent");
		String arTitle=req.getParameter("arTitle");
		String arLabel=req.getParameter("arLabel");
		Long userId=Long.parseLong(req.getParameter("userId"));
		String topId = req.getParameter("topId");
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String arDate=df.format(new Date());
		Article article = new Article(userId,arTitle,arContent,arLabel,arDate);
		if(!"".equals(topId)&&topId!=null){
			article.setTopId(Long.parseLong(topId));
		}else{
			article.setTopId(0);
		}
		PrintWriter out = resp.getWriter();
		Boolean saveflag = service.saveArticle(article);
		if(saveflag==true){
			out.write("发表成功");
			
		}else{
			out.write("发表失败");
			
		}
		out.close();
		
	}
	
	protected void editArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Long arId=Long.parseLong(req.getParameter("arId"));
		String arTitle=req.getParameter("arTitle");
		String arContent=req.getParameter("arContent");
		String arLabel=req.getParameter("arLabel");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String arDate = df.format(new Date());
		
		Article article = new Article();
		article.setArId(arId);
		article.setArTitle(arTitle);
		article.setArContent(arContent);
		article.setArLabel(arLabel);
		article.setArDate(arDate);
		
		Boolean editflag = service.editArticle(article);
		if(editflag==true){
			out.write("修改成功");
		}else{
			out.write("修改失败");
		}
		out.close();
		
	}
	
	protected void deleteArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Long arId = Long.parseLong(req.getParameter("arId"));
		Boolean deleteflag = service.deleteArticle(arId);
		if(deleteflag==true){
			out.write("删除成功");
		}else{
			out.write("删除失败");
		}
		
		out.close();
	}
	
	protected void joinedTopicByArticel(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String topId=req.getParameter("topId");
		String topLabel=req.getParameter("topLabel");
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("topId", topId);
		map.put("topLabel", topLabel);
		req.setAttribute("map",map);
		req.getRequestDispatcher("/JSP/PublishArticle.jsp").forward(req, resp);

	}

	protected void separatePage(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		Integer currentPage=Integer.parseInt(req.getParameter("currentPage"));
		Long userId = Long.parseLong(req.getParameter("userId"));
		PrintWriter out = resp.getWriter();
		
		Map<String,Object> map = new HashMap<>();
		List<String> showArContentList = new ArrayList<>();
		try {
			PageList aPageList = service.separatePage(currentPage,userId);
			for(Object obj:aPageList.getListData()){
				String showContent="";
				Article article = (Article)obj;
				
				String reg="[\u4e00-\u9fa5]+";
				Pattern pattern=Pattern.compile(reg);
				Matcher macher = pattern.matcher(article.getArContent());
				if(macher.find()){
					int start=macher.start();
					int end=macher.end();
					String content = article.getArContent().substring(start, end);
					if(content.length()>=30){
						showArContentList.add(content.substring(0,30)+"......");
					}else{
						showArContentList.add(content+"......");
					}
				}else{
					showArContentList.add(showContent);
				}
				//System.out.println(showArContentList);
			}
			map.put("showContentList", showArContentList);
			map.put("apagelist",aPageList);
			//System.out.println(showArContentList);
			ObjectMapper objm = new ObjectMapper();
			String articleShowList = objm.writeValueAsString(map);
			resp.setContentType("text/text;charset=utf-8");
			out.print(articleShowList); 
			//System.out.println(articlePageList);
		    out.flush();
		    out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void articleDetails(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Long arId=Long.parseLong(req.getParameter("arId"));
		try {
			Article article = service.selectArticleById(arId);
			resp.setContentType("text/text;charset=utf-8");
			PrintWriter out = resp.getWriter();
			ObjectMapper objm = new ObjectMapper();
			String data=objm.writeValueAsString(article);
			out.write(data);
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void reviewArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Integer currentPage=Integer.parseInt(req.getParameter("currentPage"));
		try {
			Map<String, Object> map = service.browserArticle(currentPage);
			req.setAttribute("map", map);
			req.getRequestDispatcher("/JSP/ArticleBrowser.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
