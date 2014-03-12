package com.github.sinall.soapor;

import com.github.sinall.soapor.util.SOAPMessageFactory;
import com.github.sinall.soapor.util.SOAPMessageUtils;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.util.HashMap;
import java.util.Map;

public class SOAPRequest {
    private final SOAPMessage soapMessage;

    public static SOAPRequest getInstance(String payloadName) throws SOAPException {
        return new SOAPRequest(SOAPMessageFactory.create(payloadName, new SOAPParameters()));
    }

    public static SOAPRequest getInstance(String payloadName, SOAPParameters params) throws SOAPException {
        return new SOAPRequest(SOAPMessageFactory.create(payloadName, params));
    }

    @Deprecated
    public static SOAPRequest getInstance(String payloadName, Map<String, ? extends Object> params) throws SOAPException {
        return new SOAPRequest(SOAPMessageFactory.create(payloadName, params));
    }

    @Deprecated
    public static SOAPRequest getInstance(String payloadName, Object obj) throws SOAPException {
        return new SOAPRequest(SOAPMessageFactory.create(payloadName, obj));
    }

    public SOAPRequest(SOAPMessage soapMessage) {
        this.soapMessage = soapMessage;
    }

    public SOAPMessage getSOAPMessage() {
        return this.soapMessage;
    }

    @Override
    public String toString() {
        return SOAPMessageUtils.toXML(soapMessage);
    }
}
