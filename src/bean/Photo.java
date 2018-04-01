package bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class Photo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long photoId;
	private long userId;
	private long topId;
	private String photoPath;
	private String photoLabel;
	private String photoDes;
	private String photoDate;
	
	public Photo(){
		
	}
	
	public Photo(long userId,String photoPath,String photoLabel,
			String photoDes,String photoDate){
		this.userId=userId;
		this.photoPath=photoPath;
		this.photoLabel=photoLabel;
		this.photoDes=photoDes;
		this.photoDate=photoDate;
		
	}

}
