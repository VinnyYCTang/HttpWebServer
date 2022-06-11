package com.example.httpwebserver.core;

import com.example.httpwebserver.util.Logger;

import javax.xml.soap.SOAPElementFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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
        Socket clientSocket = null;
        BufferedReader br = null;
        try {
            long start = System.currentTimeMillis();
            int port = ServerParser.getPort();
            Logger.log("httpserver start on " + port + " port");

            serverSocket = new ServerSocket(port);

            long end = System.currentTimeMillis();
            Logger.log("httpserver started, " + (end-start) + "ms");

            clientSocket = serverSocket.accept();
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String temp = null;
            while((temp = br.readLine()) != null){
                System.out.println(temp);
            }

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
            if(clientSocket != null && !clientSocket.isClosed()){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
