package com.post.demo.response;

import com.post.demo.model.Message;
import com.post.demo.model.dto.MessageDTO;

import java.util.List;

public class MessageResponse {
    private boolean isSuccess = false;
    private String errorMessage;
    private String errorType;
    private List<MessageDTO> messageDTOList;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public List<MessageDTO> getMessageDTOList() {
        return messageDTOList;
    }

    public void setMessageDTOList(List<MessageDTO> messageDTOList) {
        this.messageDTOList = messageDTOList;
    }
}
