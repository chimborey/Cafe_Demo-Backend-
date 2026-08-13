package com.Authentication.Cafe_Demo.Authentication.Exception;

import com.Authentication.Cafe_Demo.Authentication.dto.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        MessageResponse response = new MessageResponse(null, false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // គ្រប់គ្រងករណី JSON Body ខុសទម្រង់ ឬអានមិនកើត (HttpMessageNotReadableException)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResponse> handleJsonParseException(HttpMessageNotReadableException ex) {
        MessageResponse response = new MessageResponse(null, false, "Invalid JSON format or missing request body!");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // គ្រប់គ្រងករណី Validation (,@Valid) មិនឆ្លងកាត់
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // error ទីមួយដែលកើតឡើង
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        MessageResponse response = new MessageResponse(null, false, errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // គ្រប់គ្រង Errors ទូទៅផ្សេងៗទៀត (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException ex) {
        MessageResponse response = new MessageResponse(null, false, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}