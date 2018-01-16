package com.aquilifer.reporting.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class JsonParser {

    public Map<String, Object> parse(String fileName) {
        InputStream inputStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(fileName));
        return parse(inputStream);
    }

    public Map<String, Object> parse(InputStream inputStream){
        try {
            return new ObjectMapper().readValue(
                    inputStream,
                    new TypeReference<Map<String, Object>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
