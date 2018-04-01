package idao;

import java.sql.SQLException;

import bean.ArticleComment;

public interface IArticleCommentDao {
	
	public Boolean insertArticleComment(ArticleComment articleComment);
	public void deleteArticleComment(Long arId) throws SQLException;

}
