package com.post.demo.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class GenerateUtil {
    public String generateUser(){
        return String.valueOf(Timestamp.valueOf(LocalDateTime.now()))+"user";
    }
}
