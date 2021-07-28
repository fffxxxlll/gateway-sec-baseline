package com.group4.secbaselinebackend.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author feng xl
 * @date 2021/7/27 0027 16:09
 */

public class PropertiesReader {


    public static Properties properties;
    static {
        properties = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("E:\\IntelliJ IDEA 2018.3\\j2ee_project\\sec-baseline\\sec-baseline-backend\\src\\main\\resources\\alertconf.properties"));
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
         ///加载属性列表
    }


    public static Object getValueByKey(String key) {
        return properties.getProperty(key);
    }
}
