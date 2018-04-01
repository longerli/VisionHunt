package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.ArticleComment;
import idao.IArticleCommentDao;
import util.DBUtil;

public class ArticleCommentDao implements IArticleCommentDao{
	
	private Connection conn;
	private PreparedStatement pstm;
	
	public Boolean insertArticleComment(ArticleComment articleComment) {
		conn=DBUtil.getConnect();
		boolean saveflag=true;
		try {
			pstm=conn.prepareStatement("INSERT INTO articlecomment (arId,userId,arCommContent,arCommDate) VALUES(?,?,?,?)");
			pstm.setLong(1, articleComment.getArId());
			pstm.setLong(2, articleComment.getUserId());
			pstm.setString(3, articleComment.getArCommContent());
			pstm.setString(4, articleComment.getArCommDate());
			
			pstm.executeUpdate();
			
			DBUtil.close(conn, pstm, null);
			return saveflag;
		} catch (SQLException e) {
			// TODO: handle exception
			saveflag=false;
			return saveflag;
		}
		
		
	}
	
	public void deleteArticleComment(Long arId) throws SQLException{
		try {
			conn=DBUtil.getConnect();
			conn.setAutoCommit(false);
			pstm=conn.prepareStatement("DELETE FROM articlecomment WHERE arId=?");
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
