package com.example.pdfgenerator.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HashingUtil {
    
    private final ObjectMapper objectMapper;
    
    public HashingUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    /**
     * Generate a unique hash ID based on the object content
     * 
     * @param object The object to hash
     * @return A SHA-256 hash of the object's JSON representation
     */
    public String generateHashId(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            return DigestUtils.sha256Hex(json);
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate hash for object", e);
        }
    }
}
