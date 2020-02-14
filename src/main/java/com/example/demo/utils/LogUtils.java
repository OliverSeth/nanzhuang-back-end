package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Oliver Seth on 2020/2/14 16:54
 */
public class LogUtils {
    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static void getWarnLog(String msg) {
        logger.warn(" " + msg);
    }

    public static void getWarnLog(String username, String msg) {
        logger.warn(" User " + username + " " + msg);
    }

    public static void getInfoLog(String username, String msg) {
        logger.info(" User " + username + " " + msg);
    }

    public static void getErrorLog(String username, String msg) {
        logger.error(" User " + username + " " + msg);
    }

    public static void getErrorLog(String username, String msg, Exception e) {
        logger.error(" Throw exception when User " + username + " " + msg);
        logger.error("Exception message: " + e.toString());
    }

    public static void getErrorLog(String msg, Exception e) {
        logger.error("Throw exception when " + msg);
        logger.error("Exception message: " + e.toString());
    }
}
