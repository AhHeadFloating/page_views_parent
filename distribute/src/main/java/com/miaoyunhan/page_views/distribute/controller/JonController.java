package com.miaoyunhan.page_views.distribute.controller;

import com.miaoyunhan.page_views.distribute.constant.JobConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

@RestController
public class JonController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    @RequestMapping("startJob")
    public Boolean startJob(String jobName){
        stopJob(jobName);
        JobConstant.scheduledFutureMap.get(jobName);
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(JobConstant.jobMap.get(jobName), new CronTrigger("0/6 * * * * *"));
        JobConstant.scheduledFutureMap.put("wuYou",future);
        System.out.println("DynamicTask.startCron()");
        return true;
    }

    @RequestMapping("/stopJob")
    public String stopJob(String jobName) {
        ScheduledFuture future = JobConstant.scheduledFutureMap.get(jobName);
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTask.stopCron()");
        return "stopCron";
    }

}
