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
//private int currentPageNum;//��ǰҳ
//private int pageSize=5;//ÿҳ��ʾ������
//private int totalRecords;//�ܼ�¼����
//private int startIndex;//��ѯ�Ŀ�ʼ��¼����
//private int totalPageNum;//��ҳ��
//private int prePageNum;//��һҳ
//private int nextPageNum;//��һҳ
//private List records;//�ֺ�ҳ�Ľ���������ֻ����5������
//
////������ʾҳ������ԡ����ǵ�������ҳ�������ֻ��ʾ9��ҳ�롣��ǰҳ��������������Զ����  
//private int beginPageNum;
//private int endPageNum;
//
///**
// * 
// *  Ҫ��ʹ�ô��࣬�����ṩ2������
// * @param currentPageNum        ��ǰҳ
// * @param totalRecords          �ܼ�¼����
// */
//public Page(int currentPageNum,int totalRecords){
//    this.currentPageNum=currentPageNum;
//    this.totalRecords=totalRecords;
//
//    //���㿪ʼ��¼����   
//    startIndex = (currentPageNum -1)* pageSize;
//
//    //������ҳ��
//    totalPageNum =  totalRecords%pageSize==0 ? totalRecords/pageSize : totalRecords/pageSize+1;
//
//    //����ҳ��
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
//    //������һҳ
//    prePageNum=currentPageNum-1;
//    //�����ǰҳ�ǵ�һҳ����һҳ���ǵ�һҳ
//    return prePageNum<1?1:prePageNum;
//}
//
//public int getNextPageNum() {
//    //������һҳ
//    nextPageNum=currentPageNum+1;
//    //�����ǰҳ�����һҳ����һҳ�������һҳ
//    return  nextPageNum>totalPageNum?totalPageNum:nextPageNum;
//}
