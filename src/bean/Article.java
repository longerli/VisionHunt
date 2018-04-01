package bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long arId;
	private long userId;
	private String arTitle;
	private String arContent;
	private String arLabel;
	private String arDate;
	private long topId;
	
	public Article(){
		
	}
	
	public Article(long userId,String arTitle,String arContent,String arLabel,String arDate){
		
		this.userId=userId;
		this.arTitle=arTitle;
		this.arContent=arContent;
		this.arLabel=arLabel;
		this.arDate=arDate;
	}

}
