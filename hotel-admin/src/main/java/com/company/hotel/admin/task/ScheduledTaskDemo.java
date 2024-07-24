package com.company.hotel.admin.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTaskDemo {
    @Scheduled(cron = "0 * * * * *")
    public  void reportCurrentTime(){
        log.info("当前时间为："+System.currentTimeMillis());
    }
}
