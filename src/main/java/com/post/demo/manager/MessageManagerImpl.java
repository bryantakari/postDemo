package com.post.demo.manager;

import com.post.demo.controller.MessageController;
import com.post.demo.model.Message;
import com.post.demo.model.User;
import com.post.demo.model.dto.MessageDTO;
import com.post.demo.repository.MessageRepository;
import com.post.demo.repository.UserRepository;
import com.post.demo.request.MessageRequest;
import com.post.demo.response.MessageResponse;
import com.post.demo.scheduler.BizOrderTask;
import com.post.demo.service.KafkaProducer;
import com.post.demo.util.ErrorContextEnum;
import com.post.demo.util.MessageTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.Optional;
@ImportResource("classpath:scheduler.xml")
public class MessageManagerImpl implements MessageManager{
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public MessageResponse insert(MessageRequest request) {
        MessageResponse response = new MessageResponse();
        response.setSuccess(false);

        try{

            if(null == request.getSenderId() || null == request.getReceiverId() || null == request.getMessage()){
                response.setErrorType(ErrorContextEnum.PARAM_ILLEGAL.getCode());
                response.setErrorMessage(ErrorContextEnum.PARAM_ILLEGAL.getValue());
                throw new Exception("Parameter Illegal");
            }
            Optional<User> sender = userRepository.findById(request.getSenderId());
            Optional<User> receiver = userRepository.findById(request.getReceiverId());
            if(sender.isEmpty()){
                log.info("sender not found");
                response.setErrorType(ErrorContextEnum.PARAM_ILLEGAL.getCode());
                response.setErrorMessage(ErrorContextEnum.PARAM_ILLEGAL.getValue());
                throw new Exception("sender not found");
            }
            if(receiver.isEmpty()){
                log.info("receiver not found");
                response.setErrorType(ErrorContextEnum.PARAM_ILLEGAL.getCode());
                response.setErrorMessage(ErrorContextEnum.PARAM_ILLEGAL.getValue());
                throw new Exception("receiver not found");
            }
            if(!request.isValidate()){
                log.info("Validate Time is wrong!");
                response.setErrorMessage(ErrorContextEnum.PARAM_ILLEGAL.getValue());
                response.setErrorType(ErrorContextEnum.PARAM_ILLEGAL.getCode());
                throw new Exception("Validate Time is wrong!");
            }
            Message message = new Message();
            message.setContent(request.getMessage());

            message.setSender(sender.get());
            message.setReceiver(receiver.get());
            Date date = new Date();
            message.setGmtCreated(date);
            message.setGmtUpdated(date);
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessageId("MessageId_"+message.getContent());
            messageDTO.setSenderId(message.getSender().getUserId());
            messageDTO.setContent(message.getContent());
            kafkaProducer.sendMessageDTO(messageDTO);
            if(("true").equalsIgnoreCase(request.getIsScheduled())){
                log.info("This Scheduler Using: "+taskScheduler.getThreadNamePrefix() + " date: " + date);
                message.setType(MessageTypeEnum.MESSAGE_TYPE_SCHEDULED.getValue());
                BizOrderTask bizOrderTask = new BizOrderTask();
                bizOrderTask.setMessage(request.getMessage());
                bizOrderTask.setMessaging(message);
                CronTrigger cronTrigger = new CronTrigger("0 "+request.getMinutes()+" "+request.getHours()+" ? * *");
                taskScheduler.schedule(bizOrderTask,cronTrigger);
            }else{
                log.info(" date: " + date);
                message.setType(MessageTypeEnum.MESSAGE_TYPE_DIRECT.getValue());
                messageRepository.save(message);
            }
            response.setSuccess(true);
            response.setErrorMessage(null);
            response.setErrorType(null);

        }catch (Exception e){
            log.info(e.getMessage());
            log.info("inserting Message is not executed!");
        }
        return response;
    }
}
