package com.api.exporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    /**
     * Runs token refresher every 2 hours
     * @throws Exception
     */
    //@Scheduled(fixedRate = 120*60*1000)
    //@Scheduled(cron = "0 0 0/2 1/1 * ? *")
    public void refreshToken() throws Exception {
        tokenRefresher.refresh();
    }

    /**
     * Runs necessary jobs daily 
     * @throws Exception
     */
    //@Scheduled(fixedRate = 1440*60*1000)
    //@Scheduled(cron = "0 0 0 1/1 * ? *")
    @Bean
    public void runJobs() throws Exception {
        refreshToken();
        heartListService.runHeartList();
        heartGetService.runHeartGet();
    }

}