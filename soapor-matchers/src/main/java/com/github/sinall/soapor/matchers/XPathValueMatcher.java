package com.github.sinall.soapor.matchers;

import com.github.sinall.soapor.SOAPMessageEvaluator;
import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import javax.xml.soap.SOAPMessage;
import java.text.MessageFormat;

public class XPathValueMatcher<T> extends TypeSafeMatcher<SOAPMessage> {

    private String xpath;
    private T expectedValue;
    private String actualValue;

    public XPathValueMatcher(String xpath, T expectedValue) {
        this.xpath = xpath;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean matchesSafely(SOAPMessage soapMessage) {
        SOAPMessageEvaluator evaluator = new SOAPMessageEvaluator(soapMessage);
        actualValue = evaluator.getValueByXPath(xpath);
        return ObjectUtils.equals(expectedValue.toString(), actualValue);
    }

    public void describeTo(Description description) {
        description.appendText(MessageFormat.format("contain \"{0}\" in XPath \"{1}\"", expectedValue, xpath));
    }

    @Override
    public void describeMismatchSafely(SOAPMessage item, org.hamcrest.Description mismatchDescription) {
        mismatchDescription.appendText(MessageFormat.format("contain \"{0}\"", actualValue));
    }
}
