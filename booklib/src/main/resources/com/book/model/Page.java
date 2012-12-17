package com.book.model;

/**
 * @author zhangzuoqiang
 * @Email z.zuoqiang@gmail.com
 * @time 2012-3-13 下午7:12:35
 * @Todo 分页
 * @Modify
 */
public class Page {

    /**
     * URL上页码参数 *
     */
    public static String pageNumberParameterName = "pageIndex";

    /**
     * 当前页码 *
     */
    private int pageIndex;
    /**
     * 每页记录数 *
     */
    private int pageSize = 20;
    /**
     * 总记录数 *
     */
    private int totalCount;
    /**
     * 页码上的地址 *
     */
    private String url;

    /**
     * Index starts from 1, 2, 3...
     */
    public Page(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * Index starts from 1, 2, 3...
     */
    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    /**
     * 带URL地址构造方法，最常用的一种
     *
     * @param pageIndex 当前页
     * @param pageSize  每页记录数
     * @param url       URL地址
     */
    public Page(int pageIndex, int pageSize, String url) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.url = url;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 开始页
     *
     * @return
     */
    public int getFirstIndex() {
        return (pageIndex - 1) * pageSize;
    }

    /**
     * 最后页
     *
     * @return
     */
    public int getLastIndex() {
        int n = getFirstIndex() + pageSize;
        if (n > totalCount) {
            n = totalCount;
        }
        return n;
    }

    public int getPageCount() {
        if (totalCount == 0) {
            return 0;
        }
        return totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }

    public boolean isEmpty() {
        return totalCount == 0;
    }

    public boolean getHasPrevious() {
        return pageIndex > 1;
    }

    public boolean getHasNext() {
        return pageIndex < getPageCount();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
