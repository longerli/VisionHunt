package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Article;
import bean.User;
import dao.ArticleCommentDao;
import dao.ArticleDao;
import dao.UserDao;
import iservice.IArticleService;
import util.PageList;

public class ArticleService implements IArticleService{
	
	private ArticleDao dao;
	private UserDao userDao;
	private ArticleCommentDao articleCommentDao;
	
	public Boolean saveArticle(Article article){
		dao=new ArticleDao();
		Boolean saveflag = dao.insertArticle(article);
		return saveflag;
	}
	
	public PageList separatePage(Integer currentPage,long userId) throws SQLException{
		dao=new ArticleDao();
		PageList plist = dao.separatePage(currentPage,userId);
		return plist;
	}
	
	public Article selectArticleById(Long arId) throws SQLException{
		dao=new ArticleDao();
		Article article = dao.selectArticleById(arId);
		return article;
	}
	
	public Boolean editArticle(Article article){
		dao=new ArticleDao();
		Boolean editflag = dao.updateArticle(article);
		return editflag;
	}
	
	public Boolean deleteArticle(Long arId){
		dao=new ArticleDao();
		articleCommentDao=new ArticleCommentDao();
		boolean deleteflag=true;
		try {
			articleCommentDao.deleteArticleComment(arId);
			dao.deleteArticle(arId);
			return deleteflag;
		} catch (SQLException e) {
			// TODO: handle exception
			deleteflag=false;
			return deleteflag;
		}
		
	}
	
	public Map<String,Object> browserArticle(Integer currentPage) 
			throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		dao=new ArticleDao();
		userDao=new UserDao();
		
		PageList articlePageList = dao.browserArticle(currentPage);
		//List articleList = articlePageList.getListData();
		List<User> userList=new ArrayList<User>();
		
		for(Object obj:articlePageList.getListData()){
			Article article=(Article)obj;
			Long userId=article.getUserId();
			User user = userDao.findOne(userId);
			userList.add(user);
		}
		
		map.put("articlePageList", articlePageList);
		map.put("userList", userList);
		return map;
	}

}
