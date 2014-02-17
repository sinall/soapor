package com.github.sinall.soapor.matchers;

public class SOAPMessageMatchers {

    public static <T> XPathValueMatcher<T> hasValueInXPath(String xpath, T expectedValue) {
        return new XPathValueMatcher<T>(xpath, expectedValue);
    }

    public static <T> XPathNodeMatcher hasNodeInXPath(String xpath) {
        return new XPathNodeMatcher(xpath);
    }

}
