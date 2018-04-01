package bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class PhotoSpecial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long speId;
	private String speTitle;
	private String speContent;
	private String speDate;
	
	public PhotoSpecial() {
		super();
	}
	public PhotoSpecial(String speTitle, String speContent, String speDate) {
		super();
		this.speTitle = speTitle;
		this.speContent = speContent;
		this.speDate = speDate;
	}
	
}
