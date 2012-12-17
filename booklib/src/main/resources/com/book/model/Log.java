package com.book.model;

/**
 * @author zhangzuoqiang
 * @Todo
 * @Modify
 */
public class Log {

    private String uuid;
    private String userName;
    private String ip;
    private String resourcePattern;
    private String resourceId;
    private boolean success;
    private String remarks;
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

    public String getResourcePattern() {
        return this.resourcePattern;
    }

    public void setResourcePattern(final String resourcePattern) {
        this.resourcePattern = resourcePattern;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
