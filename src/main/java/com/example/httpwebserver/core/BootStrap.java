package com.example.httpwebserver.core;

import com.example.httpwebserver.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/*
* httpServer main program entrance
* @author Vinny.YC.Tang
* @version 1.0
* @since 1.0
* */
public class BootStrap {

    /*
    * @param args
    **/
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        long begin = System.currentTimeMillis();
        Logger.log("httpserver start");
        ServerSocket serverSocket = null;
        try {
            int port = 8080;
            serverSocket = new ServerSocket(port);
            long end = System.currentTimeMillis();
            Logger.log("httpserver started, " + (end-begin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket != null && !serverSocket.isClosed()){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
