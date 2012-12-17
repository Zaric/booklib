package com.book.model;

/**
 * @author zhangzuoqiang
 * @Todo
 * @Modify
 */
public class Remark {

    private String uuid;
    private String userName;
    private String bookUid;
    private String essay;
    private String createTime;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getBookUid() {
        return this.bookUid;
    }

    public void setBookUid(final String bookUid) {
        this.bookUid = bookUid;
    }

    public String getEssay() {
        return this.essay;
    }

    public void setEssay(final String essay) {
        this.essay = essay;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

}
