package com.example.httpwebserver.core;

import com.example.httpwebserver.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
* httpServer main program entrance
* @author Vinny.YC.Tang
* @version 1.0
* @since 1.0
* */
public class BootStrap {


    private final static String[] webAppNames = {"benq", "bank"};
    /*
    * @param args
    **/
    public static void main(String[] args) {
        start();
    }

    public static void start() {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            int port = ServerParser.getPort();
            long start = System.currentTimeMillis();
            Logger.log("httpserver start on " + port + " port");

            WebParser.parser(webAppNames);
            serverSocket = new ServerSocket(port);

            long end = System.currentTimeMillis();
            Logger.log("httpserver started, " + (end-start) + "ms");

            while(true) {
                clientSocket = serverSocket.accept();
                new Thread(new HandlerRequest(clientSocket)).start();
            }

        } catch (Exception e) {
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
