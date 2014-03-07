package com.github.sinall.soapor;

import com.github.sinall.soapor.util.SOAPMessageFactory;
import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SOAPMessageFactoryTest {
    @Test
    public void should_create_SOAPMessage_from_map() throws SOAPException {
        String payloadName = "com/github/sinall/soapor/payload/example.xml";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("param1", 123);

        SOAPMessage soapMessage = SOAPMessageFactory.create(payloadName, params);

        SOAPMessageEvaluator evaluator = new SOAPMessageEvaluator(soapMessage);
        assertThat(evaluator.getValueByXPath("/Envelope/Body/doubleAnInteger/param1"), is("123"));
        assertThat(evaluator.getValueByXPath("//param1"), is("123"));
    }

    @Test
    public void should_create_SOAPMessage_from_SOAPContext() throws SOAPException {
        String payloadName = "com/github/sinall/soapor/payload/example.xml";
        SOAPParameters context = new SOAPParameters();
        context.put("param1", 123);

        SOAPMessage soapMessage = SOAPMessageFactory.create(payloadName, context);

        SOAPMessageEvaluator evaluator = new SOAPMessageEvaluator(soapMessage);
        assertThat(evaluator.getValueByXPath("/Envelope/Body/doubleAnInteger/param1"), is("123"));
        assertThat(evaluator.getValueByXPath("//param1"), is("123"));
    }
}
