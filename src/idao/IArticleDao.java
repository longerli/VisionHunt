package idao;

import java.sql.SQLException;

import bean.Article;
import util.PageList;

public interface IArticleDao {
	
	public Boolean insertArticle(Article article);
	public PageList browserArticle(Integer currentPage) throws SQLException;
	public PageList separatePage(Integer currentPage,long userId) throws SQLException;
	public Article selectArticleById(Long arId) throws SQLException;
	public Boolean updateArticle(Article article);
	public void deleteArticle(Long arId) throws SQLException;

}
