package com.example.httpwebserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Date Util Class
* @author Vinny.YC.Tang
* @version 1.0
* @since 1.0
*
* */
public class DateUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    private DateUtil(){}

    /*
    * Get system's current time
    * @return String[yyyy-MM-dd HH:mm:ss SSS]
    * */
    public static String getCurrentTime(){
        return dateFormat.format(new Date());
    }
}
