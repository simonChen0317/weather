package com.czp.spring.cloud.weather.Services;

import com.czp.spring.cloud.weather.vo.Weather;
import com.czp.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/9 16:40
 * @UpdateUser:
 * @UpdateDate:2018/3/9 16:40
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
    @Autowired
    private WeatherDataService weatherDataService;
    @Override
    public Weather getDateByCityId(String cityId) {
        WeatherResponse reps =weatherDataService.getDataByCityId(cityId);
        return reps.getData();
    }
}
