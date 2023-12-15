package com.aceleracion.ecommercegyl.rest;

import com.aceleracion.ecommercegyl.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleNoExistDBException(MyException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        errorMap.put("code", e.getCode());

        return new ResponseEntity<>(errorMap, e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", e.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Map<String, List<String>>>> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult objBindingResult = e.getBindingResult();
        Map<String, Map<String, List<String>>> errorMap = new HashMap<>();
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError error : objBindingResult.getFieldErrors()) {
            String key = error.getField();
            String value = error.getDefaultMessage();
            List<String> values = new ArrayList<>();

            if (errors.containsKey(key)) {
                values = errors.get(key);
            }
            values.add(value);
            errors.put(key, values);
        }
        errorMap.put("Error", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

}
