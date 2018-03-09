package com.czp.spring.cloud.weather.Services;

import com.czp.spring.cloud.weather.Utils.XmlBuilder;
import com.czp.spring.cloud.weather.vo.City;
import com.czp.spring.cloud.weather.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/9 14:45
 * @UpdateUser:
 * @UpdateDate:2018/3/9 14:45
 */
@Service
public class CityListServiceImpl implements CityListService {
    @Override
    public List<City> listCity() throws Exception {
        //读取xml文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader br =new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
        StringBuffer buffer =new StringBuffer();
        String line="";
        while ((line=br.readLine())!=null){
            buffer.append(line);
        }
        br.close();
        //将xml转为Java对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class,buffer.toString());
        return cityList.getCityList();
    }
}
