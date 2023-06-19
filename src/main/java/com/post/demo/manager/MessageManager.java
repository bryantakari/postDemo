package com.post.demo.manager;

import com.post.demo.request.MessageRequest;
import com.post.demo.response.MessageResponse;

public interface MessageManager {
    public MessageResponse insert(MessageRequest request);
}
