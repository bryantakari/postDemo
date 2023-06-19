package com.post.demo.controller;

import com.post.demo.manager.MessageManager;
import com.post.demo.model.Message;
import com.post.demo.model.User;
import com.post.demo.repository.UserRepository;
import com.post.demo.request.MessageRequest;
import com.post.demo.response.MessageResponse;
import com.post.demo.scheduler.BizOrderTask;
import com.post.demo.util.ErrorContextEnum;
import com.post.demo.util.MessageTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@ImportResource("classpath:scheduler.xml")
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageManager messageManager;
    private static final String MESSAGE_SCHEDULED = "SCHEDULED";
    @PostMapping("/message/schedule")
    public ResponseEntity<MessageResponse> scheduleMessage(@RequestBody MessageRequest request){
        return new ResponseEntity<>(messageManager.insert(request), HttpStatus.CREATED);
    }

    public void setTaskScheduler(ThreadPoolTaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }
}
