package com.github.sinall.soapor;

import java.util.HashMap;
import java.util.Map;

public class SOAPParameters {

    private Map<String, Object> params = new HashMap<String, Object>();

    public SOAPParameters put(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public SOAPParameters putAll(Map<String, ? extends Object> params) {
        for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public Map<String, Object> getMap() {
        return params;
    }

}
