package com.chuwa.hw.bank_springboot.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {
    @Scheduled(cron = "*/3 * * * * *")
    public void performTask(){
        System.out.println("Schedule tasks: " + new Date());
    }
}
