package com.book.model;

import com.book.util.Utils;

/**
 * @author zhangzuoqiang
 * @Todo
 * @Modify
 */
public class User {

    private String uuid;
    private String loginName;
    private String email;
    private int status;
    private String password;
    private String name;
    private int sex;
    private String birthday;
    private String homeAddr;
    private String cardid;
    private String groups;
    private String createTime;
    private String activedTime;
    private String formatTime;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getGroups() {
        return this.groups;
    }

    public void setGroups(final String groups) {
        this.groups = groups;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getHomeAddr() {
        return this.homeAddr;
    }

    public void setHomeAddr(final String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(final String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(final int sex) {
        this.sex = sex;
    }

    public String getCardid() {
        return this.cardid;
    }

    public void setCardid(final String cardid) {
        this.cardid = cardid;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

    public String getActivedTime() {
        return this.activedTime;
    }

    public void setActivedTime(final String activedTime) {
        this.activedTime = activedTime;
    }


    public String getFormatTime() {
        if ("0".equals(this.getActivedTime())) {
            return "0";
        }
        this.setFormatTime(Utils.getFormatTime(this.getActivedTime()));
        return this.formatTime;
    }

    public void setFormatTime(final String formatTime) {
        this.formatTime = formatTime;
    }

}
