package com.github.sinall.soapor;

import com.github.sinall.soapor.util.SOAPMessageUtils;

import javax.xml.soap.SOAPMessage;

public class SOAPResponse {
    private final SOAPMessage soapMessage;

    public SOAPResponse(SOAPMessage responseMessage) {
        this.soapMessage = responseMessage;
    }

    public SOAPMessage getSOAPMessage() {
        return soapMessage;
    }

    @Override
    public String toString() {
        return SOAPMessageUtils.toXML(soapMessage);
    }
}
