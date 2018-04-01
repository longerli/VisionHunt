package bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class PhotoCollect implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long colId;
	private long userId;
	private long photoId;
	
	public PhotoCollect(){
		
	}

	public PhotoCollect(long userId, long photoId) {
		this.userId = userId;
		this.photoId = photoId;
	}
	

}
