package com.example.httpwebserver.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
            // create a reader.
            SAXReader saxReader = new SAXReader();
            // get a document[org.dom4j] object by using reader's read() method.
            Document document = saxReader.read("conf/serverConfig.xml");
            /* get path of connector element from root :
             * [server -> service -> connector]
             * get xpath of connector element from root :
             * [/server/service/connector], [server//connector], [//connector]
             **/
            Element connectorElt = (Element) document.selectSingleNode("//connector");
            // get port by attribute
            port =  Integer.parseInt(connectorElt.attributeValue("port"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return port;
    }
}
