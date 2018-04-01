package service;

import bean.ArticleComment;
import dao.ArticleCommentDao;
import iservice.IArticleCommentService;

public class ArticleCommentService implements IArticleCommentService{
	
	private ArticleCommentDao dao;
	
	public Boolean saveArticleComment(ArticleComment articleComment){
		dao=new ArticleCommentDao();
		Boolean saveflag = dao.insertArticleComment(articleComment);
		return saveflag;
	}
}
