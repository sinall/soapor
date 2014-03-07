package com.github.sinall.soapor;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SOAPClient {
    private final String endpoint;
    private final SOAPConnection soapConnection;

    public SOAPClient(String endpoint) throws SOAPException {
        this.endpoint = endpoint;
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        soapConnection = soapConnectionFactory.createConnection();
    }

    public SOAPResponse send(SOAPRequest request) throws SOAPException {
        SOAPMessage responseMessage = soapConnection.call(request.getSOAPMessage(), endpoint);
        return new SOAPResponse(responseMessage);
    }

    public String getEndPoint() {
        return endpoint;
    }
}
