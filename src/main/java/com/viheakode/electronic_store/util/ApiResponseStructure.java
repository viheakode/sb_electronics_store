package com.viheakode.electronic_store.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseStructure {
    public static ResponseEntity<Object> singleResponse(String msg, Object data, HttpStatus status){
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("message", msg);
        objectMap.put("data", data);
        objectMap.put("status", status);
        return new ResponseEntity<>(objectMap, status);
    }
}
