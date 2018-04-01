package iservice;

import java.sql.SQLException;
import bean.Attention;
import util.PageList;

public interface IAttentionService {
	
	public String saveAttention(Attention attention);
	public PageList userAttentionSeparatePage(Long userId,Integer currentPage) throws SQLException;
	public Boolean cancelAttention(Long attId);

}
