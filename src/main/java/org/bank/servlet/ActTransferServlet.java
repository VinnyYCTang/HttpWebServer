package org.bank.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.sql.*;

public class ActTransferServlet implements Servlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) {

        String actFrom = request.getParameterValue("actFrom");
        double balance = Double.parseDouble(request.getParameterValue("balance"));
        String actTo = request.getParameterValue("actTo");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/vinny";
            conn = DriverManager.getConnection(url, "root", "123");
            String sql_from = "update bank set balance = balance - ? where actno = ?";
            ps = conn.prepareStatement(sql_from);
            ps.setDouble(1, balance);
            ps.setString(2, actFrom);
            count = ps.executeUpdate();

            String sql_to = "update bank set balance = balance + ? where actno = ?";
            ps = conn.prepareStatement(sql_to);
            ps.setDouble(1, balance);
            ps.setString(2, actTo);
            count = count + ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        PrintWriter out = response.getWriter();
        if(count == 2){
            out.print("<html>");
            out.print("<head>");
            out.print("<title>銀行帳戶 - 轉帳結果</title>");
            out.print("<meta content='text/html;charset=utf-8'/>");
            out.print("</head>");
            out.print("<body>");
            out.print("<center><font size='35px' color='green'>恭喜您，轉帳成功！</font></center>");
            out.print("</body>");
            out.print("</html>");
        } else {
            out.print("<html>");
            out.print("<head>");
            out.print("<title>銀行轉帳 - 轉帳結果</title>");
            out.print("<meta content='text/html;charset=utf-8'/>");
            out.print("</head>");
            out.print("<body>");
            out.print("<center><font size='35px' color='red'>對不起，轉帳失敗！</font></center>");
            out.print("</body>");
            out.print("</html>");
        }
    }
}
