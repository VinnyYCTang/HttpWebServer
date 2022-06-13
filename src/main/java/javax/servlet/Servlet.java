package javax.servlet;

import com.example.httpwebserver.core.ResponseObject;

/*
*
* @author Sun Corp.
* @version  1.0
* @since    1.0
* */
public interface Servlet {

    void service(ServletResponse response);
}
