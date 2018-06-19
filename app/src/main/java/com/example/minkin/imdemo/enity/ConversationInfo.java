package com.example.minkin.imdemo.enity;

/*
 *创建人:yanggl
 *创建时间:2018-6-19  18:29
 *类描述:
 *备注:
 */
public class ConversationInfo {
    private String avatarUrl;
    private String nickName;
    private String lastMessage;
    private String lastTime;
    private String unReadMessage;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getUnReadMessage() {
        return unReadMessage;
    }

    public void setUnReadMessage(String unReadMessage) {
        this.unReadMessage = unReadMessage;
    }
}
