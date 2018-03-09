package com.czp.spring.cloud.weather.jobs;

import com.czp.spring.cloud.weather.Services.CityListService;
import com.czp.spring.cloud.weather.Services.WeatherDataService;
import com.czp.spring.cloud.weather.Services.WeatherDataServiceImpl;
import com.czp.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @Author: simonChen
 * @Despriction:Weather Data Sync Job.
 * @CreateDate:2018/3/9 13:55
 * @UpdateUser:
 * @UpdateDate:2018/3/9 13:55
 */
public class WeatherDataSyncJob extends QuartzJobBean {
    private final static Logger logger =LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    @Autowired
    private CityListService cityListService;
    @Autowired
    private WeatherDataService weatherDataService;
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Weather Data Sync Job Start!.");
        //1、获取城市ID列表
        List<City> cityList=null;
        try {
            cityList = cityListService.listCity();
        } catch (Exception e) {
            logger.error("Exception!",e);
        }
        //2、遍历城市Id获取天气数据
        for (City city: cityList){
            String cityId=city.getCityId();
            logger.info("Weather Data Sync Job, cityId:" + cityId);
            weatherDataService.syncDateByCityId(cityId);
        }
        logger.info("Weather Data Sync Job End!.");
    }
}
