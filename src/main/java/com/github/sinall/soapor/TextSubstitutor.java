package com.github.sinall.soapor;

import java.util.Map;

public class TextSubstitutor {
    public String substitute(String content, Map<String, Object> params) {
        for (Map.Entry<String, Object> param : params.entrySet()) {
            Object value = param.getValue();
            String placeholder = String.format("${%s}", param.getKey());
            String replacement = "null";
            if (value != null) {
                replacement = value.toString();
            }
            content = content.replace(placeholder, replacement);
        }
        return content;
    }
}
