package com.yed.system.model;

import java.util.Date;

import com.yed.common.entity.BaseEntity;

public class Log extends BaseEntity{
    private Integer id;

    private String logUrl;

    private String logIp;

    private String logAgent;

    private String userName;

    private Date beginTime;

    private Date endTime;

    private String logParam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl == null ? null : logUrl.trim();
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp == null ? null : logIp.trim();
    }

    public String getLogAgent() {
        return logAgent;
    }

    public void setLogAgent(String logAgent) {
        this.logAgent = logAgent == null ? null : logAgent.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLogParam() {
        return logParam;
    }

    public void setLogParam(String logParam) {
        this.logParam = logParam == null ? null : logParam.trim();
    }
}