package com.xhnj.model;

import java.io.Serializable;
import java.util.Date;

public class ShareStrategy implements Serializable {
    private Long id;

    private String nickname;

    private Date shareTime;

    private Integer shareObjType;

    private String shareObjId;

    private String shareObjName;

    private String shareUrl;

    private String pics;

    private Integer showStatus;

    private Date createTime;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getShareObjType() {
        return shareObjType;
    }

    public void setShareObjType(Integer shareObjType) {
        this.shareObjType = shareObjType;
    }

    public String getShareObjId() {
        return shareObjId;
    }

    public void setShareObjId(String shareObjId) {
        this.shareObjId = shareObjId == null ? null : shareObjId.trim();
    }

    public String getShareObjName() {
        return shareObjName;
    }

    public void setShareObjName(String shareObjName) {
        this.shareObjName = shareObjName == null ? null : shareObjName.trim();
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics == null ? null : pics.trim();
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}