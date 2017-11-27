package service;

import java.sql.SQLException;

import bean.Article;
import dao.ArticleDao;

public class ArticleService {
	
	private ArticleDao dao;
	
	public void save(Article article){
		dao=new ArticleDao();
		try {
			dao.save(article);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
