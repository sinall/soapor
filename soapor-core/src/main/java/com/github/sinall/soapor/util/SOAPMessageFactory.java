package com.github.sinall.soapor.util;

import com.github.sinall.soapor.SOAPParameters;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.util.Map;

public class SOAPMessageFactory {

    static {
        Velocity.init();
    }

    public static SOAPMessage create(String payloadName) throws SOAPException {
        return create(payloadName, new SOAPParameters());
    }

    @Deprecated
    public static SOAPMessage create(String payloadName, Object obj) throws SOAPException {
        try {
            Map<String, String> params = BeanUtils.describe(obj);
            return create(payloadName, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static SOAPMessage create(String payloadName, Map params) throws SOAPException {
        SOAPParameters parameters = new SOAPParameters();
        parameters.putAll(params);

        return create(payloadName, parameters);
    }

    public static SOAPMessage create(String payloadName, SOAPParameters context) throws SOAPException {
        String template = loadTemplate(payloadName);
        String soapContent = instantiate(template, context);

        MessageFactory messageFactory = MessageFactory.newInstance();
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(soapContent.getBytes(Charsets.UTF_8));
            SOAPMessage soapMessage = messageFactory.createMessage(new MimeHeaders(), inputStream);
            return soapMessage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private static String loadTemplate(String payloadName) {
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

    private static String instantiate(String template, SOAPParameters context) {
        StringReader reader = new StringReader(template);
        StringWriter writer = new StringWriter();
        String soapContent;
        try {
            VelocityContext velocityContext = new VelocityContext();
            Map<String, Object> params = context.getMap();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                velocityContext.put(entry.getKey(), entry.getValue());
            }
            Velocity.evaluate(velocityContext, writer, "", reader);
            writer.flush();
            soapContent = writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(writer);
        }

        return soapContent;
    }
}
