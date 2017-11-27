package bean;

import java.io.Serializable;

import lombok.Data;
@Data
public class Attention implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long attId;
	private long userId;
	private long beAttUserId;
	
	public Attention(){
		
	}

	public Attention(long userId, long beAttUserId) {
		super();
		this.userId = userId;
		this.beAttUserId = beAttUserId;
	}
	
	

}
