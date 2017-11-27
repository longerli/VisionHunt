package bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class PhySpecial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long phyId;
	private String phyLabel;
	private String phyDes;
	private String phyContent;
	private Date phyDate;
	public PhySpecial() {
		super();
	}
	public PhySpecial(String phyLabel, String phyDes, String phyContent, Date phyDate) {
		super();
		this.phyLabel = phyLabel;
		this.phyDes = phyDes;
		this.phyContent = phyContent;
		this.phyDate = phyDate;
	}
	

}
