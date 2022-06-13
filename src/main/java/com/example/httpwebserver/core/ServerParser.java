package com.example.httpwebserver.core;

import com.example.httpwebserver.util.XmlParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
*  Parsing serverConfig.xml document
*  @author Vinny.YC.Tang
*  @version 1.0
*  @since 1.0
* */
public class ServerParser {

    /*
    * Parsing port number
    * @retrun int port
    * */
    public static int getPort(){

        // set default port value to 8080.
        int port = 8080;
        try {
            NodeList list = XmlParser.getElementByTagName("conf/serverConfig.xml", "connector");
            Element element = (Element) list.item(0);
            port = Integer.parseInt(element.getAttribute("port"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
