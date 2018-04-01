package iservice;

import java.sql.SQLException;
import java.util.Map;

import bean.Article;
import util.PageList;

public interface IArticleService {
	
	public Boolean saveArticle(Article article);
	public PageList separatePage(Integer currentPage,long userId) throws SQLException;
	public Article selectArticleById(Long arId) throws SQLException;
	public Boolean editArticle(Article article);
	public Boolean deleteArticle(Long arId);
	public Map<String,Object> browserArticle(Integer currentPage) throws SQLException;

}
