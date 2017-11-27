package service;

import java.sql.SQLException;

import bean.Attention;
import dao.AttentionDao;

public class AttentionService {
	
	private AttentionDao dao;
	
	public String save(Attention attention){
		dao=new AttentionDao();
		String saveflag=null;
		try {
			saveflag=dao.save(attention);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(saveflag);
		return saveflag;
	}

}
