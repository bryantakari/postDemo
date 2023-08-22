package com.post.demo.request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageRequest {
    private String message;
    private String time;
    private String senderId;
    private String receiverId;
    private String isScheduled;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isValidate(){
        String regex = "^([0-1]?\\d|2[0-3])(?::([0-5]?\\d))?(?::([0-5]?\\d))?$";
        Pattern p = Pattern.compile(regex);
        if(time == null){
            return false;
        }
        Matcher m = p.matcher(time);

        return m.matches();
    }
    public String getHours(){
        String []temp = time.split(":");
        return temp[0];
    }
    public String getMinutes(){
        String []temp = time.split(":");
        return temp[1];
    }
    public String getSeconds(){
        String []temp = time.split(":");
        return temp[2];
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(String isScheduled) {
        this.isScheduled = isScheduled;
    }
}
