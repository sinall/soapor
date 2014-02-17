package com.github.sinall.soapor.matchers;

import static com.github.sinall.soapor.matchers.SOAPMessageMatchers.hasValueInXPath;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.junit.Test;

import com.github.sinall.soapor.util.SOAPMessageFactory;

public class SOAPMessageMatchersTest {
    @Test
    public void should_match_value_in_xpath() throws SOAPException {
        SOAPMessage soapMessage = SOAPMessageFactory.create("com/github/sinall/soapor/payload/example.xml");

        assertThat(soapMessage, hasValueInXPath("/Envelope/Body/doubleAnInteger/param1", 123));
    }
}
