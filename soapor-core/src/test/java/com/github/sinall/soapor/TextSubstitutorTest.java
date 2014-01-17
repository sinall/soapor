package com.github.sinall.soapor;

import com.github.sinall.soapor.util.TextSubstitutor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TextSubstitutorTest {

    private TextSubstitutor textSubstitutor = new TextSubstitutor();

    @Test
    public void should_substitute_string() {
        String template = "${username}@gmail.com";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", "Jack");

        String email = textSubstitutor.substitute(template, params);

        assertThat(email, is("Jack@gmail.com"));
    }
}
