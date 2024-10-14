package com.example.decs.schedule;

import com.example.decs.Service.UserService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CoinScheduler {

    private UserService userService = new UserService();


    public void startCoinIncrementJob() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            userService.incrementUserCoinsDaily();
        }, 0, 1, TimeUnit.DAYS);  // Runs every day
    }
}