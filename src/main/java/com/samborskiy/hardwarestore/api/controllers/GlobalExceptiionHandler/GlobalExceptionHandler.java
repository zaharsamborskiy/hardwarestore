package com.samborskiy.hardwarestore.api.controllers.GlobalExceptiionHandler;

import com.samborskiy.hardwarestore.api.exceptions.AppError;
import com.samborskiy.hardwarestore.api.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchBadRequestException(BadRequestException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<AppError> catchPropertyValueException(PropertyValueException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getPropertyName() + " cant`t be a null"),  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchIllegalArgumentsException(IllegalArgumentException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<AppError> catchMissingPathVariableException(MissingPathVariableException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "unknown URI"),HttpStatus.NOT_FOUND);
    }
}
