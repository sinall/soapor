package com.github.sinall.soapor;

import org.junit.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SOAPMessageFactoryTest {
    @Test
    public void should_create_SOAPMessage_from_request() throws SOAPException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "Jack");
        Request request = new Request("com/github/sinall/soapor/payload/example.xml");

        SOAPMessage soapMessage = SOAPMessageFactory.create(request);

        SOAPMessageEvaluator evaluator = new SOAPMessageEvaluator(soapMessage);
        assertThat(evaluator.getValueByXPath("/Envelope/Body/doubleAnInteger/param1"), is("123"));
        assertThat(evaluator.getValueByXPath("//param1"), is("123"));
    }
}
