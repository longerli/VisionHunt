package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Article;
import idao.IArticleDao;
import util.DBUtil;
import util.PageList;

public class ArticleDao implements IArticleDao{
	
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	
	public Boolean insertArticle(Article article) {
		conn=DBUtil.getConnect();
		boolean saveflag=true;
		try {
			pstm=conn.prepareStatement("INSERT INTO article (userId,arTitle,arContent,arLabel,arDate,topId) VALUES(?,?,?,?,?,?)");
			
			pstm.setLong(1, article.getUserId());
			pstm.setString(2, article.getArTitle());
			pstm.setString(3, article.getArContent());
			pstm.setString(4, article.getArLabel());
			pstm.setString(5, article.getArDate());
			pstm.setLong(6, article.getTopId());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return saveflag;
		} catch (SQLException e) {
			// TODO: handle exception
			saveflag=false;
			return saveflag;
		}
		
	}
	
	
	public PageList browserArticle(Integer currentPage) throws SQLException{
		conn=DBUtil.getConnect();
		Integer pageSize=15;
		pstm=conn.prepareStatement("SELECT * FROM article");
		rs=pstm.executeQuery();
		rs.last();
		Integer totalCount=rs.getRow();
		
		PreparedStatement ptm=conn.prepareStatement("SELECT * FROM article ORDER BY arId DESC LIMIT "
		+(currentPage-1)*pageSize+","+pageSize);
		
		Integer prePage=currentPage-1>=1?currentPage-1:1;
		Integer totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		Integer nextPage=currentPage+1<=totalPage?currentPage+1:totalPage;
		
		ResultSet s = ptm.executeQuery();
		List<Article> list = new ArrayList<Article>();
		while(s.next()){
			Article article = new Article(s.getLong(2),s.getString(3),s.getString(4),s.getString(5),s.getString(6));
			article.setArId(s.getLong(1));
			article.setTopId(s.getLong(7));
			list.add(article);
		}
		PageList plist = new PageList(list, totalCount, currentPage, pageSize, prePage, nextPage, totalPage);
		return plist;
	}
	
	
	public PageList separatePage(Integer currentPage,long userId) throws SQLException{
		conn=DBUtil.getConnect();
		Integer pageSize=4;
		pstm=conn.prepareStatement("SELECT * FROM article WHERE userId=?");
		pstm.setLong(1, userId);
		rs=pstm.executeQuery();
		rs.last();
		Integer totalCount=rs.getRow();
		
		PreparedStatement ptm = conn.prepareStatement("SELECT * FROM article WHERE userId=? ORDER BY arId DESC LIMIT "
				+(currentPage-1)*pageSize+","+pageSize);
		ptm.setLong(1, userId);
		Integer prePage=currentPage-1>=1?currentPage-1:1;
		Integer totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		Integer nextPage=currentPage+1<=totalPage?currentPage+1:totalPage;
		
		ResultSet s = ptm.executeQuery();
		
		List<Article> list = new ArrayList<>();
		while(s.next()){
			Article article = new Article(s.getLong(2),s.getString(3),s.getString(4),s.getString(5),s.getString(6));
			article.setArId(s.getLong(1));
			article.setTopId(s.getLong(7));
			list.add(article);
		}
		
		PageList plist = new PageList(list, totalCount, currentPage, pageSize, prePage, nextPage, totalPage);
		return plist;
	}
	
	
	public Article selectArticleById(Long arId) throws SQLException{
		List<Article> list = new ArrayList<Article>();
		conn=DBUtil.getConnect();
		pstm=conn.prepareStatement("SELECT * FROM article WHERE arId=?");
		pstm.setLong(1, arId);
		
		rs=pstm.executeQuery();
		while(rs.next()){
			Article article = new Article(rs.getLong(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			article.setArId(rs.getLong(1));
			article.setTopId(rs.getLong(7));
			list.add(article);
		}
		
		return list.get(0);
	}
	
	public Boolean updateArticle(Article article){
		conn=DBUtil.getConnect();
		boolean editflag=true;
		try {
			pstm=conn.prepareStatement("UPDATE article SET arTitle=?,arContent=?,arLabel=?,arDate=? WHERE arId=?");
			pstm.setString(1, article.getArTitle());
			pstm.setString(2, article.getArContent());
			pstm.setString(3, article.getArLabel());
			pstm.setString(4, article.getArDate());
			pstm.setLong(5, article.getArId());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return editflag;
		} catch (SQLException e) {
			// TODO: handle exception
			editflag=false;
			return editflag;
		}
		
	}
	
	public void deleteArticle(Long arId) throws SQLException{
		try {
			conn=DBUtil.getConnect();
			conn.setAutoCommit(false);
			pstm=conn.prepareStatement("DELETE FROM article WHERE arId=?");
			pstm.setLong(1, arId);
			pstm.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
			DBUtil.close(conn, pstm, null);
		}
		
	}

}
