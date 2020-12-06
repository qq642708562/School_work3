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





//
//private int currentPageNum;//当前页
//private int pageSize=5;//每页显示的条数
//private int totalRecords;//总记录条数
//private int startIndex;//查询的开始记录索引
//private int totalPageNum;//总页数
//private int prePageNum;//上一页
//private int nextPageNum;//下一页
//private List records;//分好页的结果集（最多只能有5个对象）
//
////用于显示页码的属性。我们的需求是页面上最多只显示9个页码。当前页在允许的情况下永远居中  
//private int beginPageNum;
//private int endPageNum;
//
///**
// * 
// *  要想使用此类，必须提供2个参数
// * @param currentPageNum        当前页
// * @param totalRecords          总记录条数
// */
//public Page(int currentPageNum,int totalRecords){
//    this.currentPageNum=currentPageNum;
//    this.totalRecords=totalRecords;
//
//    //计算开始记录索引   
//    startIndex = (currentPageNum -1)* pageSize;
//
//    //计算总页数
//    totalPageNum =  totalRecords%pageSize==0 ? totalRecords/pageSize : totalRecords/pageSize+1;
//
//    //计算页号
//    if (totalPageNum<9) {    
//        beginPageNum=1;
//        endPageNum=totalPageNum;
//    }else {        
//        beginPageNum=currentPageNum-4;
//        endPageNum=currentPageNum+4;
//        if (beginPageNum<1) {     
//            beginPageNum=1;
//            endPageNum=beginPageNum+8;
//        }
//        if (endPageNum>totalPageNum) {
//            endPageNum=totalPageNum;
//            beginPageNum=endPageNum-8;
//        }
//    }
//
//}
//
//public int getPrePageNum() {
//    //计算上一页
//    prePageNum=currentPageNum-1;
//    //如果当前页是第一页，上一页还是第一页
//    return prePageNum<1?1:prePageNum;
//}
//
//public int getNextPageNum() {
//    //计算下一页
//    nextPageNum=currentPageNum+1;
//    //如果当前页是最后一页，下一页还是最后一页
//    return  nextPageNum>totalPageNum?totalPageNum:nextPageNum;
//}
