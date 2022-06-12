package com.example.httpwebserver.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

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
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            Document doc = documentBuilderFactory.newDocumentBuilder().parse(new File("conf/serverConfig.xml"));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("connector");
            Element element = (Element) list.item(0);
            port = Integer.parseInt(element.getAttribute("port"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }
}
