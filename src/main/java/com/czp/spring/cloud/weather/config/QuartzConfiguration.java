package com.czp.spring.cloud.weather.config;

import com.czp.spring.cloud.weather.jobs.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/9 14:02
 * @UpdateUser:
 * @UpdateDate:2018/3/9 14:02
 */
@Configuration
public class QuartzConfiguration {
    private static final int TIME = 1800; // 更新频率
//这里要实现两个类的注入：JobDetail、Trigger
    @Bean
    //注意，newJob时指定的时我们写的定时任务逻辑类
    public JobDetail weaterDataSyncJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    @Bean
    public Trigger weaterDataSyncTrigger(){
        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weaterDataSyncJobDetail()).withIdentity("weaterDataSyncTrigger").withSchedule(schedBuilder).build();
    }
}
