package com.api.exporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private HeartListService heartListService;

    @Autowired
    private HeartGetService heartGetService;

    @Autowired
    private TokenRefresher tokenRefresher;

    //@Scheduled(fixedRate = 5000)
    public void test() {
        System.out.println("test");
    }

    /**
     * Runs token refresher
     * @throws Exception
     */
    public void refreshToken() throws Exception {
        tokenRefresher.refresh();
    }

    /**
     * Runs necessary jobs
     * @throws Exception
     */
    public void runJobs() throws Exception {
        heartListService.runHeartList();
        heartGetService.runHeartGet();
    }

}