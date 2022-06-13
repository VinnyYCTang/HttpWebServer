package javax.servlet;

import java.io.PrintWriter;

/*
 * encapsulate a response object.
 * @author Sun Corp.
 * @version  1.0
 * @since    1.0
 * */
public interface ServletResponse {

    void setWriter(PrintWriter out);

    PrintWriter getWriter();
}
