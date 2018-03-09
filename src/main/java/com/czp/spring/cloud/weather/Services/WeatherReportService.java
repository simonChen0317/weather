package com.czp.spring.cloud.weather.Services;

import com.czp.spring.cloud.weather.vo.Weather;

/**
 * @Author: simonChen
 * @Despriction:Weather Report Service
 * @CreateDate:2018/3/9 16:37
 * @UpdateUser:
 * @UpdateDate:2018/3/9 16:37
 */
public interface WeatherReportService {
    /**
    *@method 根据城市ID查询天气信息
    *@author simonChen
    *@version 
     * @param null
    *@return 
    *@exception 
    *@Date 2018/3/9 16:40
    */
    Weather getDateByCityId(String cityId);
}
