package com.example.httpwebserver.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlParser {

    private final static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    private XmlParser(){}

    public static NodeList getElementByTagName(String FilePath, String tagName) throws Exception {
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Document doc = documentBuilderFactory.newDocumentBuilder().parse(new File(FilePath));
        doc.getDocumentElement().normalize();
        NodeList list = doc.getElementsByTagName(tagName);
        return list;
    }
}
