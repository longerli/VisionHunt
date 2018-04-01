package util;

import java.util.List;

import lombok.Data;

@Data
public class PageList {
	
	private List<?> listData;
	private Integer totalCount;
	
	private Integer currentPage;
	private Integer pageSize;
	
	private Integer beginPage=1;
	private Integer prePage;
	private Integer nextPage;
	private Integer totalPage;
	
	public PageList(List<?> listData, Integer totalCount, Integer currentPage, Integer pageSize,Integer prePage,
			Integer nextPage,Integer totalPage) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.prePage=prePage;
		this.nextPage=nextPage;
		this.totalPage=totalPage;
	}
	
	public PageList(){
		
	}
	
}
