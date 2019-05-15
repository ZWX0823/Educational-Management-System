package com.springmvc.po;

public class PagingVO {
    //当前页码，默认为第一页
    private int currentPageNo = 1;
    //总页码
    private int totalPageCount;
    //页面容量
    private int pageSize = 5;
    //上一页
    private int prePageNo;
    //下一页
    private int nextPageNo;
    //要前往的页码，默认为0
    private int toPageNo = 0;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    //设置当前页码
    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo!=1){
            this.prePageNo = currentPageNo - 1;
        }
        this.nextPageNo = currentPageNo + 1;

        this.currentPageNo = currentPageNo;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {     //参数totalPageCount指的是总的元组数目
        if (totalPageCount%pageSize > 0){
            this.totalPageCount = (totalPageCount/pageSize) + 1;
        }
        this.totalPageCount = totalPageCount/pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePageNo() {
        return prePageNo;
    }

    public void setPrePageNo(int prePageNo) {
        this.prePageNo = prePageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public int getToPageNo() {
        return toPageNo;
    }

    public void setToPageNo(int toPageNo) {

        this.toPageNo = (toPageNo-1) * pageSize;    //this.toPageNo是第toPageNo页第一个元组的下标（从0开始）
        //设置跳转后当前的页码
        setCurrentPageNo(toPageNo);
    }
}
