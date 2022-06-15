package com.benq.oa.servlet;

import com.example.httpwebserver.core.RequestObject;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;

public class UserSaveServlet implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) {

        String username = request.getParameterValue("username") != null ? request.getParameterValue("username") : "";
        String gender = request.getParameterValue("gender") != null ? request.getParameterValue("gender") : "";
        String[] interest = request.getParameterValues("interest");
        StringBuilder interests = new StringBuilder();
        if(interest != null) {
            for (String interestValue : interest) {
                interests.append(interestValue).append(" ");
            }
        }

        // get printWriter
        PrintWriter out = response.getWriter();
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
