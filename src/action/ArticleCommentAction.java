package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ArticleComment;
import service.ArticleCommentService;

@WebServlet("/articlecomment")
public class ArticleCommentAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ArticleCommentService service;
	
	public void init() throws ServletException {
		service=new ArticleCommentService();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		if("saveArticleComment".equals(cmd)){
			this.saveArticleComment(req, resp);
		}
	}
	
	protected void saveArticleComment(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		Long arId=Long.parseLong(req.getParameter("arId"));
		Long userId=Long.parseLong(req.getParameter("userId"));
		String arCommContent = req.getParameter("arCommContent");
		DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String arCommDate=df.format(new Date());
		
		ArticleComment articleComment = new ArticleComment(arId,userId,arCommContent,arCommDate);
		Boolean saveflag = service.saveArticleComment(articleComment);
		PrintWriter out = resp.getWriter();
		if(saveflag==true){
			out.write(arCommDate);
		}else{
			out.write("文章评论失败");
		}
		out.close();
	}

}
