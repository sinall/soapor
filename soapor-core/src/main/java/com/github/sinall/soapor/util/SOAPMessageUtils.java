package com.github.sinall.soapor.util;

import org.apache.commons.io.IOUtils;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class SOAPMessageUtils {
    public static String toXML(SOAPMessage soapMessage) {
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
}
