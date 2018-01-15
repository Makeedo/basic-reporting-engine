package com.aquilifer.reporting.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class JsonParser {

    public Map<String, Object> parse(String fileName) {
        File jsonFile = new File(this.getClass().getClassLoader().getResource(fileName).getFile());
        return parse(jsonFile);
    }

    public Map<String, Object> parse(File jsonFile){
        try {
            return new ObjectMapper().readValue(
                    jsonFile,
                    new TypeReference<Map<String, Object>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}
