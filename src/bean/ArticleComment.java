package bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ArticleComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long arCommId;
	private long userId;
	private long arId;
	private String arCommContent;
	private String arCommDate;
	public ArticleComment() {
	}
	
	public ArticleComment(long arId, long userId, String arCommContent, String arCommDate) {
		this.userId = userId;
		this.arId = arId;
		this.arCommContent = arCommContent;
		this.arCommDate = arCommDate;
	}
	
	

}
