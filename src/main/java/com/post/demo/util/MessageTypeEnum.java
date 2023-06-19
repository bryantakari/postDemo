package com.post.demo.util;

public enum MessageTypeEnum {
    MESSAGE_TYPE_SCHEDULED("SCHEDULED"),
    MESSAGE_TYPE_DIRECT("DIRECT")
    ;
    private String value;

    MessageTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
