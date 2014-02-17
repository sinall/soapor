package com.github.sinall.soapor.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SOAPMessageFactory {

    public static SOAPMessage create(String payloadName) throws SOAPException {
        return create(payloadName, new HashMap<String, Object>());
    }

    public static SOAPMessage create(String payloadName, Object obj) throws SOAPException {
        try {
            Map<String, String> params = BeanUtils.describe(obj);
            return create(payloadName, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static SOAPMessage create(String payloadName, Map params) throws SOAPException {
        String content = load(payloadName);
        content = new TextSubstitutor().substitute(content, params);

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
