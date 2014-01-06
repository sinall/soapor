package com.github.sinall.soapor;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SOAPClient {
    private String endpoint;
    private SOAPConnection soapConnection;

    public SOAPClient(String endpoint) throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        soapConnection = soapConnectionFactory.createConnection();
    }

    public SOAPMessage send(Request request) throws SOAPException {
        SOAPMessage requestMessage = SOAPMessageFactory.create(request);
        SOAPMessage responseMessage = soapConnection.call(requestMessage, endpoint);
        return responseMessage;
    }
}
