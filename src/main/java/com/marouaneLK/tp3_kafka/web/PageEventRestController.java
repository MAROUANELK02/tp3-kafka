package com.marouaneLK.tp3_kafka.web;

import com.marouaneLK.tp3_kafka.entities.PageEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventRestController {
    private final StreamBridge streamBridge;

    //@Value("${topic}")
    //private String topic;

    public PageEventRestController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic,
            @PathVariable String name) {
        PageEvent pageEvent = new PageEvent(name,
                Math.random() > 0.5 ? "U1" : "U2",
                new Date(), new Random().nextInt(9000));
        streamBridge.send(topic, pageEvent);
        return pageEvent;
    }
}
