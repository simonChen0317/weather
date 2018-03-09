package com.czp.spring.cloud.weather.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @Author: simonChen
 * @Despriction:Xml Builder.
 * @CreateDate:2018/3/9 14:36
 * @UpdateUser:
 * @UpdateDate:2018/3/9 14:36
 */
public class XmlBuilder {
    /**
    *@method 将XML转为指定的POJO
    *@author simonChen
    *@version 
     * @param null
    *@return 
    *@exception 
    *@Date 2018/3/9 14:39
    */
    public static Object xmlStrToObject(Class<?> clazz,String xmlStr) throws Exception {
        Object xmlObject = null;
        Reader reader = null;
        JAXBContext context = JAXBContext.newInstance(clazz);

        // XML 转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();

        reader = new StringReader(xmlStr);
        xmlObject = unmarshaller.unmarshal(reader);

        if (null != reader) {
            reader.close();
        }

        return xmlObject;
    }
}
