package com.book.model;

/**
 * @author zhangzuoqiang
 * @Todo 简单的POJO，与数据库一一对应，但是字段很多的话，建立这么一个POJO本身就是一件很麻烦的事情。
 * @Modify
 */
public class Book {

    private String uuid;
    private String name;
    private String price;
    private String author;
    private String content;
    private String summary;
    private String createTime;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(final String summary) {
        if (null == summary) {
            this.summary = "";
            return;
        }
        this.summary = summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        if (null == content) {
            this.content = "";
            return;
        }
        this.content = content;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

}
