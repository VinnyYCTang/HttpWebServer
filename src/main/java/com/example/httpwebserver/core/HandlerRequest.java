package com.example.httpwebserver.core;

import com.example.httpwebserver.util.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
* Handle request from client.
* @author Vinny.YC.Tang
* @version 1.0
* @since 1.0
*
* */
public class HandlerRequest implements Runnable{

    public Socket clientSocket;
    public HandlerRequest(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        BufferedReader br = null;
        Logger.log("httpServer thread: " + Thread.currentThread().getName());
        try {
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
