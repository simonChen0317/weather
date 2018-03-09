package com.czp.spring.cloud.weather.Services;

import com.czp.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * @Author: simonChen
 * @Despriction:City List Service
 * @CreateDate:2018/3/9 14:43
 * @UpdateUser:
 * @UpdateDate:2018/3/9 14:43
 */
public interface CityListService {
    /**
     * 获取city列表
     * */
    List<City> listCity() throws Exception;
}
