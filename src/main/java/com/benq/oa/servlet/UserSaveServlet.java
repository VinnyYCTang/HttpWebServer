package com.benq.oa.servlet;

import com.example.httpwebserver.core.RequestObject;

import javax.servlet.Servlet;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

public class UserSaveServlet implements Servlet {

    @Override
    public void service(RequestObject requestObject, ServletResponse response) {

        String username = requestObject.getParameterValue("username");
        String gender = requestObject.getParameterValue("gender");
        String[] interest = requestObject.getParameterValues("interest");
        StringBuilder interests = new StringBuilder();
        for(String interestValue : interest){
            interests.append(interestValue).append(" ");
        }

        // get printWriter
        PrintWriter out = response.getWriter();
        out.print("HTTP/1.1 200 OK\n");
        out.print("Content-Type:text/html;charset=utf-8\n\n");
        out.print("<html>");
        out.print("<head>");
        out.print("<title>User's Info</title>");
        out.print("<meta content='text/html;charset=utf-8'/>");;
        out.print("</head>");
        out.print("<body>");
        out.print("User's name : " + username + "<br>");
        out.print("Gender : " + gender + "<br>");
        out.print("Interests : " + interests + "<br>");
        out.print("</body>");
        out.print("</html>");

    }
}
