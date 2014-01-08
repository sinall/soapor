package com.github.sinall.soapor;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class Request {
    private String payloadName;
    private Map params;

    public Request(String payloadName) {
        this(payloadName, new Object());
    }

    public Request(String payloadName, Map params) {
        this.payloadName = payloadName;
        this.params = params;
    }

    public Request(String payloadName, Object obj) {
        this.payloadName = payloadName;
        try {
            this.params = BeanUtils.describe(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPayloadName() {
        return payloadName;
    }

    public Map getParams() {
        return params;
    }
}
