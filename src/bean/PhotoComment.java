package bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class PhotoComment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long photoCommId;
	private long photoId;
	private long userId;
	private String photoCommContent;
	private String photoCommDate;
	
	public PhotoComment(){
		
	}

	public PhotoComment(long photoId, long userId, String photoCommContent, String photoCommDate) {
		this.photoId = photoId;
		this.userId = userId;
		this.photoCommContent = photoCommContent;
		this.photoCommDate = photoCommDate;
	}
	
	

}
