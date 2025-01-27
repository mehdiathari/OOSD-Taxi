package com.UNI.Taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UNI.Taxi.service.KafkaSender;

//@Controller
@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class ApacheKafkaWebController
{
    @Autowired
    KafkaSender kafkaSender;

//    @GetMapping(value = "/javainuse-kafka/producer")
    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaSender.send(message);

        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }
}
