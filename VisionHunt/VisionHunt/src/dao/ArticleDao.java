package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Article;
import util.DBUtil;

public class ArticleDao {
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	public void save(Article article) throws SQLException{
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("INSERT INTO article (userId,arTitle,arContent,arLabel,arDate) VALUES(?,?,?,?,?)");
		
		pstm.setLong(1, article.getUserId());
		pstm.setString(2, article.getArTitle());
		pstm.setString(3, article.getArContent());
		pstm.setString(4, article.getArLabel());
		pstm.setString(5, article.getArDate());
		
		pstm.executeUpdate();
		
		DBUtil.close(conn, pstm, null);
	}

}
