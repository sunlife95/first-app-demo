package com.imooc.firstappdemo.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/*
   *定时任务
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {
   // @Scheduled(cron = "0/20 * * * * ?")//每20s执行一次
    public void scheduler(){
        System.out.println(">>>>>>>>>>>>>>> SchedulingConfig.scheduler()"+new Date().toString());
    }
}
