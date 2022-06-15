package javax.servlet;
/*
 * encapsulate a request object.
 * @author Sun Corp.
 * @version  1.0
 * @since    1.0
 * */
public interface ServletRequest {

    String getParameterValue(String key);

    String[] getParameterValues(String key);

}
