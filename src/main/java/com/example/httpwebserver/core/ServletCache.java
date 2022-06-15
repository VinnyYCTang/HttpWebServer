package com.example.httpwebserver.core;

import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/*
* Servlet object pool.
* @author   Vinny.YC.Tang
* @version  1.0
* @sonce    1.0
*
* */
public class ServletCache {

    private static Map<String, Servlet> servletMap = new HashMap<>();

    public static void put(String urlPattern, Servlet servlet){
        servletMap.put(urlPattern, servlet);
    }

    public static Servlet get(String urlPattern){
        return servletMap.get(urlPattern);
    }
}
