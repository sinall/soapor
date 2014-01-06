package com.github.sinall.soapor;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

public class SOAPMessageUtils {
    public static String toString(SOAPMessage soapMessage) {
        String content;
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source sourceContent = soapMessage.getSOAPPart().getContent();
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            transformer.transform(sourceContent, streamResult);
            content = stringWriter.toString();
            IOUtils.closeQuietly(stringWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static String getValueByXPath(String xpathText, SOAPElement soapElement) {
        Document doc = toDoc(soapElement);
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            String value = xpath.compile(xpathText).evaluate(doc);
            return value;
        } catch (XPathExpressionException e) {
            return null;
        }
    }

    public static Document toDoc(SOAPElement soapElement) {
        Document doc;
        InputStream inputStream = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter strWriter = new StringWriter();
            transformer.transform(new DOMSource(soapElement), new StreamResult(strWriter));
            String content = strWriter.toString();
            inputStream = new ByteArrayInputStream(content.getBytes());
            doc = factory.newDocumentBuilder().parse(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        return doc;
    }
}
