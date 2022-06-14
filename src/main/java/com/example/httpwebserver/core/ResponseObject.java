package com.example.httpwebserver.core;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/*
* encapsulate a response object.
* @author Vinny.YC.Tang
* @version  1.0
* @since    1.0
*
* */
public class ResponseObject implements ServletResponse {

    private PrintWriter out;

    public void setWriter(PrintWriter out){
        this.out = out;
    }

    public PrintWriter getWriter(){
        return out;
    }

}
