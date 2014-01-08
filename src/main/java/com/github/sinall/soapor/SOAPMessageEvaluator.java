package com.github.sinall.soapor;

import org.w3c.dom.Document;

import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class SOAPMessageEvaluator {

    private final XPath xpath;
    private final Document doc;

    public SOAPMessageEvaluator(SOAPMessage soapMessage) {
        this.xpath = XPathFactory.newInstance().newXPath();
        this.doc = DOMUtils.createUnawareNamespaceDoc(soapMessage.getSOAPPart());
    }

    public String getValueByXPath(String xpathText) {
        try {
            String value = xpath.compile(xpathText).evaluate(doc);
            return value;
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

}
