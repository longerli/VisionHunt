package bean;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class Topic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long topId;
	private long  userId;
	private String topLabel;
	private String topDes;
	private String topDate;
	
	public Topic() {
		
	}
	public Topic(long userId, String topLabel, String topDes, String topDate) {
		this.userId = userId;
		this.topLabel = topLabel;
		this.topDes = topDes;
		this.topDate = topDate;
	}
	
	

}
