package service;

import java.sql.SQLException;
import bean.Attention;
import dao.AttentionDao;
import iservice.IAttentionService;
import util.PageList;

public class AttentionService implements IAttentionService{
	
	private AttentionDao dao;
	
	public String saveAttention(Attention attention){
		dao=new AttentionDao();
		String saveflag=null;
		saveflag=dao.insertAttention(attention);
		System.out.println(saveflag);
		return saveflag;
	}
	
	
	public PageList userAttentionSeparatePage(Long userId,Integer currentPage) 
			throws SQLException{
		dao=new AttentionDao();
  		PageList pagelist = dao.userAttentionSeparatePage(userId, currentPage);
  		return pagelist;
	}
	
	public Boolean cancelAttention(Long attId){
		dao=new AttentionDao();
		Boolean deleteflag = dao.deleteAttentionById(attId);
		return deleteflag;
	}

}
