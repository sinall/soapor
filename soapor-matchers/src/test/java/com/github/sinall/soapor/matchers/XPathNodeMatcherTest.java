package com.github.sinall.soapor.matchers;

import static com.github.sinall.soapor.matchers.SOAPMessageMatchers.hasNodeInXPath;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.junit.Test;

import com.github.sinall.soapor.util.SOAPMessageFactory;

public class XPathNodeMatcherTest {
    @Test
    public void should_match_value_in_xpath() throws SOAPException {
        SOAPMessage soapMessage = SOAPMessageFactory.create("com/github/sinall/soapor/payload/example.xml");

        XPathNodeMatcher matcher = new XPathNodeMatcher("/Envelope/Body/doubleAnInteger/param1");

        assertTrue(matcher.matchesSafely(soapMessage));
        assertThat(soapMessage, hasNodeInXPath("/Envelope/Body/doubleAnInteger/param1"));
        assertThat(soapMessage, not(hasNodeInXPath("/Envelope/Body/nonExistingNode")));
    }
}
