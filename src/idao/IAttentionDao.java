package idao;

import java.sql.SQLException;
import bean.Attention;
import util.PageList;

public interface IAttentionDao {
	
	public String insertAttention(Attention attention);
	//public List<Attention> findAll() throws SQLException;
	public PageList userAttentionSeparatePage(Long userId,Integer currentPage) throws SQLException;
	public Boolean deleteAttentionById(Long attId);

}
