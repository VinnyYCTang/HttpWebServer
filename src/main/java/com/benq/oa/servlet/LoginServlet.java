package com.benq.oa.servlet;

import com.example.httpwebserver.core.RequestObject;
import com.example.httpwebserver.core.ResponseObject;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/*
* This class should be developed by webApp developer, and managed by webServer.
* The process of this class handles webApp login procedure.
* @author webApp developer
* @version 1.0
* @since 1.0
*
* */
public class LoginServlet implements Servlet {

    public void service(ServletRequest request, ServletResponse response){

        PrintWriter out = response.getWriter();
        out.print("HTTP/1.1 200 OK\n");
        out.print("Content-Type:text/html;charset=utf-8\n\n");
        out.print("processing...");
        out.flush();
    }

}
