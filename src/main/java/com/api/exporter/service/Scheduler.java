package com.api.exporter.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {


    @Scheduled(fixedRate = 5000)
    public void test() {
        System.out.println("test");
    }
}