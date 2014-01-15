package com.github.sinall.soapor.util;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

public class DOMUtils {
    public static Document createUnawareNamespaceDoc(Node node) {
        Document doc;
        InputStream inputStream = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter strWriter = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(strWriter));
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
