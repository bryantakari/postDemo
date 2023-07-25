package com.post.demo.scheduler;

import com.post.demo.model.Message;
import com.post.demo.model.dto.MessageDTO;
import com.post.demo.repository.MessageRepository;
import com.post.demo.service.KafkaProducer;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BizOrderTask implements SchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(BizOrderTask.class);
    private String message;
    private Message messaging;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    public void run() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageId("MessageId_"+messaging.getContent());
        messageDTO.setSenderId(messaging.getSender().getUserId());
        messageDTO.setContent(messaging.getContent());
        kafkaProducer.sendMessageDTO(messageDTO);
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
