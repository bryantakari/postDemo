package com.post.demo.scheduler;

import com.post.demo.model.Message;
import com.post.demo.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BizOrderTask implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(BizOrderTask.class);
    private String message;
    private Message messaging;
    @Autowired
    MessageRepository messageRepository;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    public void run() {
        messageRepository.save(messaging);
        log.info("Message:["+message+" ],The time is now {}", dateFormat.format(new Date()));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message getMessaging() {
        return messaging;
    }

    public void setMessaging(Message messaging) {
        this.messaging = messaging;
    }
}
