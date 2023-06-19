package com.post.demo.repository;

import com.post.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,String> {
    
}
