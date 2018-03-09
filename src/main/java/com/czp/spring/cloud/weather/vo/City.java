package com.czp.spring.cloud.weather.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author: simonChen
 * @Despriction:
 * @CreateDate:2018/3/9 14:24
 * @UpdateUser:
 * @UpdateDate:2018/3/9 14:24
 */
@XmlRootElement(name="d")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlAttribute(name="d1")
    private String cityId;

    @XmlAttribute(name="d2")
    private String cityName;

    @XmlAttribute(name="d3")
    private String cityCode;

    @XmlAttribute(name="d4")
    private String provice;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }
}
