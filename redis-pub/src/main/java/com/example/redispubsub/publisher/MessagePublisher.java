package com.example.redispubsub.publisher;

import org.springframework.stereotype.Component;

@Component
public interface MessagePublisher {

    void publisher(String messsage);
}
