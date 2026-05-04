package com.cohert.advice;

import com.cohert.advice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotfound(ResourceNotFoundException exception){
       ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND)
                                            .messsage("Resource not found")
                                            .build();
       return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
