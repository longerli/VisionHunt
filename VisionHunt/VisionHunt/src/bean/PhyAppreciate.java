package bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class PhyAppreciate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long appreId;
	private long userId;
	private String appreTitle;
	private String appreContent;
	private Date appreDate;
	
	public PhyAppreciate() {
		super();
	}
	public PhyAppreciate(long userId, String appreTitle, String appreContent, Date appreDate) {
		super();
		this.userId = userId;
		this.appreTitle = appreTitle;
		this.appreContent = appreContent;
		this.appreDate = appreDate;
	}
	
	

}
