package org.codeboy.william.utils;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class FixMessageConverter {

    static final String delimiter = "\u0001";
    static final String dic = "FIX44.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(dic));
        Map<String, String> tagFieldMap = getTagFieldMap(document);

        String message = "8=FIX.4.4\u00019=58\u000135=0\u000149=BuySide\u000156=SellSide\u000134=3\u000152=20190605-12:29:20.259\u000110=172\u0001";
        for (String s : Arrays.asList(message.split(delimiter))) {
            String[] split = s.split("=");
            String tag = split[0];
            String value = split[1];
            String fieldName = tagFieldMap.get(tag);
            System.out.println(String.format("(%s)%s=%s", tag, fieldName, value));
        }

    }

    private static Map<String, String> getTagFieldMap(Document document) {
        Element root = document.getDocumentElement();
        NodeList fieldsList = root.getElementsByTagName("fields");
        Node fields = fieldsList.item(0);
        NodeList childNodes = fields.getChildNodes();
        int length = childNodes.getLength();

        Map<String, String> tagFieldMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Node item = childNodes.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) item;
                String number = element.getAttribute("number");
                String name = element.getAttribute("name");
                tagFieldMap.put(number, name);
            }
        }
        return tagFieldMap;
    }
}
