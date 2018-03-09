package com.czp.spring.cloud.weather.Services;

import com.czp.spring.cloud.weather.vo.WeatherResponse;

/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/8 16:52
 * @UpdateUser:
 * @UpdateDate:2018/3/8 16:52
 */
public interface WeatherDataService {
    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);
    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);
    /**
     * 根据城市Id来同步天气数据
     * */
    void syncDateByCityId(String cityId);
}
