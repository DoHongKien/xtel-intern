package com.example.redispubsub.controller;

import com.example.redispubsub.publisher.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private Publisher publisher;

    @PostMapping("/publisher")
    public void publish (@RequestBody String message) {
        LOGGER.info("Message published: {}", message);
        publisher.publisher(message);
    }
}
