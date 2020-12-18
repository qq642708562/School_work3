package vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


public class Page implements Serializable{
	private int pageSize;
	private int pageNumber;
	private String sort;
	private String sortOrder;
	
	public Page() {
		super();
	}

	public Page(int pageSize, int pageNumber, String sort, String sortOrder) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.sort = sort;
		this.sortOrder = sortOrder;
	}
	
	public static Page getPageParams(HashMap<String, Object> mapPage) {
		int pageSize = Integer.parseInt((String) mapPage.get("pageSize"));
		int pageNumber = Integer.parseInt((String) mapPage.get("pageNumber"));
		String sort = (String) mapPage.get("userName");
		String sortOrder = (String) mapPage.get("desc");
		return new Page(pageSize,pageNumber,sort,sortOrder);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String toString(){
		return "pageSize:"+this.pageSize+"  pageNumber:"+this.pageNumber
				+"  sort:"+this.sort+"  sortOrder:"+this.sortOrder;
	}
	
}

