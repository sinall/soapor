package com.github.sinall.soapor;

import java.util.HashMap;
import java.util.Map;

public class SOAPParameters {

    private Map<String, Object> params = new HashMap<String, Object>();

    public SOAPParameters put(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public Map<String, Object> getMap() {
        return params;
    }

}
