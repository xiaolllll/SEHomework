package com.example.test.bean;

/**
 *   `receiverId` varchar(32) NOT NULL,
 *   `senderId` varchar(32) NOT NULL,
 *   `info` varchar(255) NOT NULL,
 *   `notifyType` int(32) NOT NULL,
 *   `infoHasRead` int(32) DEFAULT 0
 */
public class NotifyInfoBean {
    private String receiverId;
    private String senderId;
    private String Info;
    private int notifyType;
    private boolean infoHasRead;

    public NotifyInfoBean(String receiverId, String senderId, String info, int notifyType, boolean infoHasRead) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        Info = info;
        this.notifyType = notifyType;
        this.infoHasRead = infoHasRead;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public int getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public boolean isInfoHasRead() {
        return infoHasRead;
    }

    public void setInfoHasRead(boolean infoHasRead) {
        this.infoHasRead = infoHasRead;
    }
}
