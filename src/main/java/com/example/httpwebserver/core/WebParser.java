package com.example.httpwebserver.core;

import com.example.httpwebserver.util.XmlParser;
import org.dom4j.DocumentException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
* analyze and parse web.xml document in every webApp.
* @author Vinny.YC.Tang
* @version  1.0
* @since    1.0
*
* */
public class WebParser {

    public static Map<String, Map<String, String>> servletsMaps = new HashMap<>();

    /*
    * parse all of the Applictions' xml in this server.
    * @param webAppNames All webApps' name in this server.
    * */
    public static void parser(String[] webAppNames) throws DocumentException {
        for(String webAppName : webAppNames){
            Map<String, String> servletMap = parser(webAppName);
            servletsMaps.put(webAppName, servletMap);
        }
    }

    /*
    * parse a given Application's web.xml
    * @param webAppName Web Application's name
    * @return servletMap<String, String>
    * */
    private static Map<String, String> parser(String webAppName) throws DocumentException {

        String webPath = webAppName + "/WEB-INF/web.xml";
        Map<String, String> servletMap = null;
        try {
            NodeList servletList = XmlParser.getElementByTagName(webPath, "servlet");
            NodeList servletMappingList = XmlParser.getElementByTagName(webPath, "servlet-mapping");
            Map<String, String> servletInfoMap = new HashMap<>();
            Map<String, String> servletMappingInfoMap = new HashMap<>();
            servletMap = new HashMap<>();

            for(int i = 0; i < servletList.getLength(); i++){
                Element element = (Element) servletList.item(i);
                String servletName = element.getElementsByTagName("servlet-name").item(0).getTextContent();
                String servletClass = element.getElementsByTagName("servlet-class").item(0).getTextContent();
                servletInfoMap.put(servletName, servletClass);
            }
            for(int i = 0; i < servletMappingList.getLength(); i++){
                Element element = (Element) servletMappingList.item(i);
                String servletName = element.getElementsByTagName("servlet-name").item(0).getTextContent();
                String urlPattern = element.getElementsByTagName("url-pattern").item(0).getTextContent();
                servletMappingInfoMap.put(servletName, urlPattern);
            }
            Set<String> servletNames = servletInfoMap.keySet();
            for(String servletName : servletNames){
                String urlPattern = servletMappingInfoMap.get(servletName);
                String servletClassName = servletInfoMap.get(servletName);
                servletMap.put(urlPattern, servletClassName);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return servletMap;
    }

}
