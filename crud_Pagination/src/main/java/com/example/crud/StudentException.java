package com.example.crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public StudentException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s Not Found %s %s", resourceName, fieldName, fieldValue ));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
