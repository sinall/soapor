package com.github.sinall.soapor.matchers;

import com.github.sinall.soapor.SOAPMessageEvaluator;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.w3c.dom.Node;

import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPathConstants;

import java.text.MessageFormat;

public class XPathNodeMatcher extends TypeSafeMatcher<SOAPMessage> {

    private String xpath;

    public XPathNodeMatcher(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public boolean matchesSafely(SOAPMessage soapMessage) {
        SOAPMessageEvaluator evaluator = new SOAPMessageEvaluator(soapMessage);
        Node node = (Node) evaluator.evaluate(xpath, XPathConstants.NODE);
        return node != null;
    }

    public void describeTo(Description description) {
        description.appendText(MessageFormat.format("a SOAPMessage that has node in XPath \"{0}\"", xpath));
    }

    @Override
    public void describeMismatchSafely(SOAPMessage item, org.hamcrest.Description mismatchDescription) {
        mismatchDescription.appendText("does not have");
    }
}
