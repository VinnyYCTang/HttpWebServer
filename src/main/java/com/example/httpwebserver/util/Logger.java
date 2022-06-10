package com.example.httpwebserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Logger
* @author Vinny.YC.Tang
* @version 1.0
* @since 1.0
* */
public class Logger {

    private Logger(){}

    /*
    * normal Logger.log
    * @param msg
    * */
    public static void log(String msg){
        System.out.println("[INFO] " + DateUtil.getCurrentTime()+ " " + msg);
    }
}
