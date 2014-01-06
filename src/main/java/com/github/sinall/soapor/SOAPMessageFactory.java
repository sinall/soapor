package com.github.sinall.soapor;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SOAPMessageFactory {
    public static SOAPMessage create(Request request) throws SOAPException {
        String content = load(request.getPayloadName());
        content = new TextSubstitutor().substitute(content, request.getParams());

        MessageFactory messageFactory = MessageFactory.newInstance();
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(content.getBytes(Charsets.UTF_8));
            SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), inputStream);
            return soapMessage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private static String load(String payloadName) {
        String content;
        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemResourceAsStream(payloadName);
            content = IOUtils.toString(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return content;
    }
}
