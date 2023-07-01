package com.post.demo.service;

import com.post.demo.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, MessageDTO> kafkaTemplateDTO;
    private static final String FIRST_TOPIC = "messageTopicA";
    public void sendMessageDTO(MessageDTO data){
        Message<MessageDTO> messageDTOMessage = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,FIRST_TOPIC).build();
        kafkaTemplateDTO.send(messageDTOMessage);
    }
}
