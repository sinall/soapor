package com.github.sinall.soapor.matchers;

public class SOAPMessageMatchers {
    public static <T> XPathValueMatcher hasValueInXPath(String xpath, T expectedValue) {
        return new XPathValueMatcher(xpath, expectedValue);
    }
}
