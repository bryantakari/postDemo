package com.post.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String messageId;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "senderId", referencedColumnName = "userId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId", referencedColumnName = "userId")
    private User receiver;

    @Column
    private String type;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtCreated;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtUpdated;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }
}
