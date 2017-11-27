package bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class ArticleComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long arCommId;
	private long userId;
	private long arId;
	private String arCommContent;
	private Date arCommDate;
	public ArticleComment() {
	}
	
	public ArticleComment(long arId, long userId, String arCommContent, Date arCommDate) {
		this.userId = userId;
		this.arId = arId;
		this.arCommContent = arCommContent;
		this.arCommDate = arCommDate;
	}
	
	

}
