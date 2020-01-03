package com.api.exporter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

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
    //@Scheduled(fixedRate = 7200000)
    //@Scheduled(cron = "0 0 0/2 1/1 * ? *")
    public void refreshToken() throws Exception {
        tokenRefresher.refresh();
    }

    /**
     * Runs necessary jobs daily 
     * @throws Exception
     */
    @Scheduled(fixedRate = 86400000)
    //@Scheduled(cron = "0 0 0 1/1 * ? *")
    public void runJobs() throws Exception {
        refreshToken();
        heartListService.runHeartList();
        heartGetService.runHeartGet();
    }

}