package com.example.redissub.subcriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class Subcriber implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Subcriber.class);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        LOGGER.info("Subscriber: " + message);
    }
}
