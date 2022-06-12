package com.example.httpwebserver.core;

import com.example.httpwebserver.util.Logger;
import java.io.*;
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
        PrintWriter out = null;
        Logger.log("httpServer thread: " + Thread.currentThread().getName());
        try {
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            out = new PrintWriter(clientSocket.getOutputStream());


            String requestLine = br.readLine();// GET resource.html HTTP/1.1
            String requestURI = requestLine.split(" ")[1];
            // Check user's request is static resource or not.
            if(requestURI.endsWith(".html") || requestURI.endsWith(".htm")){
                responseStaticPage(requestURI, out);
            }
            out.flush();
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

    /*
    * Deal with the static resource which URI points to.
    * @param String requestURI
    * @param PrintWriter out
    * */
    public void responseStaticPage(String requestURI, PrintWriter out) {

        // requestURI : /welcome/index.html
        // static resource path : welcome/index.html
        String htmlPath = requestURI.substring(1);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(htmlPath));
            StringBuilder html = new StringBuilder();
            html.append("HTTP/1.1 200 OK\n");
            html.append("Content-Type:text/html;charset=utf-8\n\n");
            String temp = null;
            while((temp = br.readLine()) != null){
                html.append(temp);
            }
            out.print(html);
        } catch (FileNotFoundException e) {
            // 404 NotFound
            StringBuilder html = new StringBuilder();
            html.append("HTTP/1.1 404 NotFound\n");
            html.append("Content-Type:text/html;charset=utf-8\n\n");
            html.append("<html>");
            html.append("<head>");
            html.append("<title>404-錯誤</title>");
            html.append("<meta content='text/html;charset=utf-8' />");
            html.append("</head>");
            html.append("<body>");
            html.append("<H1 style='color:red'>404錯誤</H1>");
            html.append("</body>");
            html.append("</html>");
            out.print(html);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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