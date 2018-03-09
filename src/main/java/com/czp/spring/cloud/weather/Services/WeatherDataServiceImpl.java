package com.czp.spring.cloud.weather.Services;

import com.czp.spring.cloud.weather.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/8 16:57
 * @UpdateUser:
 * @UpdateDate:2018/3/8 16:57
 */
public class WeatherDataServiceImpl implements WeatherDataService {
    private final static Logger logger =LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final long TIME_OUT=1800L;

    @Override
    /**
    *@method getDataByCityId
    *@author simonChen
    *@version 
     * @param City
    *@return com.czp.spring.cloud.weather.vo.WeatherResponse
    *@exception 
    *@Date 2018/3/8 17:00
    */
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI+"citykey="+cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI+"city="+cityName;
        return this.doGetWeather(uri);
    }

    @Override
    public void syncDateByCityId(String cityId) {
        String uri =WEATHER_URI+"citykey="+cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String>  ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

    }

    private WeatherResponse doGetWeather(String uri){
        String key = uri;
        String strBody=null;
        WeatherResponse resp=null;
        ObjectMapper mapper = new ObjectMapper();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //先查看缓存，缓存中有的取缓存中的数据
        if (stringRedisTemplate.hasKey(key)){
            logger.info("Redis has data");
            strBody=ops.get(key);
        }else {
            logger.info("Redis don't has data");
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            if (respString.getStatusCodeValue()==200){
                strBody=respString.getBody();
            }
            //将结果写入缓存，这里timeOut是指有效时间。这里设置为10秒。
            ops.set(key, strBody,TIME_OUT,TimeUnit.SECONDS);
        }
        try {
            resp=mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error("ERROR",e);
        }
        return resp;
    }
}
