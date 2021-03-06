package com.example.httpwebserver.core;

import com.example.httpwebserver.util.Logger;

import javax.servlet.Servlet;
import java.io.*;
import java.net.Socket;
import java.util.Map;

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
            Logger.log("requestURI : " + requestURI);
            // Check user's request is static or dynamic resource.
            if(requestURI.endsWith(".html") || requestURI.endsWith(".htm")){
                // Handle static resource.
                // requestURI : /benq/index.html
                responseStaticPage(requestURI, out);
            } else {
                // Handle dynamic resource.
                // requestURI : /benq/login?username=XXX&password=OOO
                String servletPath = requestURI;
                if(servletPath.contains("?"))
                    servletPath = servletPath.split("[?]")[0];
                String webAppName = servletPath.split("[/]")[1];
                String urlPattern = servletPath.substring(1 + webAppName.length());
                Map<String, String> servletMap = WebParser.servletsMaps.get(webAppName);
                if(servletMap != null) {
                    String servletClassName = servletMap.get(urlPattern);
                    if (servletClassName != null) {
                        out.print("HTTP/1.1 200 OK\n");
                        out.print("Content-Type:text/html;charset=utf-8\n\n");
                        RequestObject requestObject = new RequestObject(requestURI);
                        ResponseObject responseObject = new ResponseObject();
                        responseObject.setWriter(out);
                        // get servlet from Servlet's pool.
                        Servlet servlet = ServletCache.get(urlPattern);
                        if(servlet == null) {
                            // create the servletClass object by reflection.
                            Class c = Class.forName(servletClassName);
                            Object obj = c.newInstance();
                            servlet = (Servlet) obj;
                            ServletCache.put(urlPattern, servlet);
                        }
                        Logger.log("servlet " + servlet);
                        servlet.service(requestObject, responseObject);
                    } else {
                        // connot find the servlet resource.
                        responseNotFoundPage(out);
                    }
                }
            }
            out.flush();
        } catch (Exception e) {
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

        // requestURI : /benq/index.html
        // static resource path : benq/index.html
        String htmlPath = requestURI.substring(1);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(htmlPath));
            StringBuilder html = new StringBuilder();
            html.append("HTTP/1.1 200 OK\n");
            html.append("Content-Type:text/html;charset=utf-8\n\n");
            String temp;
            while((temp = br.readLine()) != null){
                html.append(temp);
            }
            out.print(html);
        } catch (FileNotFoundException e) {
            // 404 NotFound
            responseNotFoundPage(out);
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

    private void responseNotFoundPage(PrintWriter out){
        StringBuilder html = new StringBuilder();
        html.append("HTTP/1.1 404 NotFound\n");
        html.append("Content-Type:text/html;charset=utf-8\n\n");
        html.append("<html>");
        html.append("<head>");
        html.append("<title>404-??????</title>");
        html.append("<meta content='text/html;charset=utf-8' />");
        html.append("</head>");
        html.append("<body>");
        html.append("<H1 style='color:red'>404??????</H1>");
        html.append("</body>");
        html.append("</html>");
        out.print(html);
    }
}
