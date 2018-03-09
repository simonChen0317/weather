package com.czp.spring.cloud.weather.Controller;

import com.czp.spring.cloud.weather.Services.CityListService;
import com.czp.spring.cloud.weather.Services.WeatherDataService;
import com.czp.spring.cloud.weather.Services.WeatherReportService;
import com.czp.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sun.text.normalizer.NormalizerBase;

/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/8 17:17
 * @UpdateUser:
 * @UpdateDate:2018/3/8 17:17
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
    @Autowired
    private CityListService cityListService;
    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        model.addAttribute("title","天气预报");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList",cityListService.listCity());
        model.addAttribute("report",weatherReportService.getDateByCityId(cityId));
        return new ModelAndView("weather/report","reportModel",model);
    }


}
