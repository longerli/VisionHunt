package action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Article;
import service.ArticleService;

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

	}
	
	protected void saveArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String arContent=req.getParameter("arContent");
		String arTitle=req.getParameter("arTitle");
		String arLabel=req.getParameter("arLabel");
		Long userId=Long.parseLong(req.getParameter("userId"));
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String arDate=df.format(new Date());
		
		Article article = new Article(userId,arTitle,arContent,arLabel,arDate);
		service.save(article);
		
	}
	
	protected void editArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

	}
	
	protected void deleteArticle(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

	}



}
