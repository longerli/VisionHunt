package bean;

import java.io.Serializable;

import lombok.Data;
@Data
public class PhotoTransmit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long transId;
	private long userId;
	private long photoId;
	private String transDes;
	
	public PhotoTransmit(){
		
	}

	public PhotoTransmit(long photoId, long userId, String transDes) {
		
		this.photoId = photoId;
		this.userId = userId;
		this.transDes = transDes;
	}
	
	
}
