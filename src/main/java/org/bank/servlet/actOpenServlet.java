package org.bank.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class actOpenServlet implements Servlet {
    @Override
    public void service(ServletRequest request, ServletResponse response)  {

        String act = request.getParameterValue("actno");
        double balance = Double.parseDouble(request.getParameterValue("balance"));

        String sqlUrl = "jdbc:mysql://localhost:3306/vinny";
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(sqlUrl, "root", "123");
            String sql = "insert into bank(actno, balance) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, act);
            ps.setDouble(2, balance);
            count = ps.executeUpdate();
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
        if(count == 1){
            out.print("<html>");
            out.print("<head>");
            out.print("<title>銀行帳戶 - 開戶結果</title>");
            out.print("<meta content='text/html;charset=utf-8'/>");
            out.print("</head>");
            out.print("<body>");
            out.print("<center><font size='35px' color='green'>恭喜您，開戶成功！</font></center>");
            out.print("</body>");
            out.print("</html>");
        } else {
            out.print("<html>");
            out.print("<head>");
            out.print("<title>銀行帳戶 - 開戶結果</title>");
            out.print("<meta content='text/html;charset=utf-8'/>");
            out.print("</head>");
            out.print("<body>");
            out.print("<center><font size='35px' color='green'>對不起，開戶失敗！</font></center>");
            out.print("</body>");
            out.print("</html>");
        }
    }
}
