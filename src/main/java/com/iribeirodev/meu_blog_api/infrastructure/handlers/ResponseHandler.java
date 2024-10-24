package com.iribeirodev.meu_blog_api.infrastructure.handlers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    
    public static ResponseEntity<Map<String, Object>> generateResponse(HttpStatus status, Object responseObj) {
        Map<String, Object> response = new HashMap<>();
        
        // Verifica se o objeto de resposta é vazio
        if (responseObj instanceof Collection<?> && ((Collection<?>) responseObj).isEmpty()) {
            response.put("message", "No content found");
            response.put("status", HttpStatus.NO_CONTENT.value());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        
        // Prepara a resposta padrão
        response.put("status", status.value());
        response.put("data", responseObj);
        
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Map<String, Object>> generateNotFound(String message) {
        return generateResponse(HttpStatus.NOT_FOUND, Map.of("message", message));
    }

    public static ResponseEntity<Map<String, Object>> generateError(String message) {
        return generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("message", message != null ? message : "Unexpected error"));
    }

    public static ResponseEntity<Map<String, Object>> generateError() {
        return generateError("Unexpected error");
    }
}