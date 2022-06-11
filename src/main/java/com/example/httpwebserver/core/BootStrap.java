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

        ServerSocket serverSocket = null;
        try {
            long start = System.currentTimeMillis();
            int port = ServerParser.getPort();
            Logger.log("httpserver start on " + port + " port");
            serverSocket = new ServerSocket(port);
            long end = System.currentTimeMillis();
            Logger.log("httpserver started, " + (end-start) + "ms");
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
