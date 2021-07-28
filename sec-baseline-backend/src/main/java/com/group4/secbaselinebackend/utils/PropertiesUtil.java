package com.group4.secbaselinebackend.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author feng xl
 * @date 2021/7/27 0027 16:09
 */

public class PropertiesUtil {


    public static final String PROPERTIES_FILE_PATH = "E:\\IntelliJ IDEA 2018.3\\j2ee_project\\sec-baseline\\sec-baseline-backend\\src\\main\\resources\\alertconf.properties";
    public static Properties properties = new Properties();


    public static Object getValueByKey(String key) {
        InputStream in = null;
        String property = null;
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

    public static void setValueOfKey(String key, String value) {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(PROPERTIES_FILE_PATH));
            properties.setProperty(key, value);
            properties.store(out, "modified by admin");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
