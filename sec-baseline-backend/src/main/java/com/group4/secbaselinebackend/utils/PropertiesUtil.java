package com.group4.secbaselinebackend.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author feng xl
 * @date 2021/7/27 0027 16:09
 */

public class PropertiesUtil {


    public static final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "\\sec-baseline-backend\\src\\main\\resources\\alertconf.properties";



    public Properties listProperties() {
        InputStream in = null;
        Properties properties = new Properties();
        try {
            in = new BufferedInputStream(new FileInputStream(PROPERTIES_FILE_PATH));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Object getValueByKey(String key) {
        InputStream in = null;
        String property = null;
        Properties properties = new Properties();
        try {
            in = new BufferedInputStream(new FileInputStream(PROPERTIES_FILE_PATH));
            properties.load(in);
            property = properties.getProperty(key);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

    public static Integer setValueOfKey(String key, String value) {
        OutputStream out = null;
        Properties properties = new Properties();
        try {
            out = new BufferedOutputStream(new FileOutputStream(PROPERTIES_FILE_PATH));
            properties.setProperty(key, value);
            properties.store(out, "modified by admin");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
