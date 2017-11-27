package bean;


import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private String userName;
	private String userPwd;
	private String userEmail;
	private String userTel;
	private String userIcon;
	
	public User(){
		
	}
	
	public User(String userName,String userPwd,String userEmail,String userTel){
		this.userName=userName;
		this.userPwd=userPwd;
		this.userEmail=userEmail;
		this.userTel=userTel;
		
	}

}
