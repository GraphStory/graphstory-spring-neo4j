package com.graphstory.controller.rest;

import com.graphstory.util.model.*;
import com.graphstory.util.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            Exception.class,
    })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), new Error("Exception", ex.getLocalizedMessage())), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({
            EntityNotFoundException.class, NoContentFoundException.class, DataValidationException.class
    })
    public ResponseEntity<Object> handleEntityNotFound(RuntimeException re, WebRequest request) {

        if (re instanceof EntityNotFoundException) {
            EntityNotFoundException ex = (EntityNotFoundException) re;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return handleExceptionInternal(ex, new ApiError(HttpStatus.NOT_FOUND, ex.getError(), new Error("EntityNotFoundException", ex.getError())), headers, HttpStatus.NOT_FOUND, request);
        } else if (re instanceof DataValidationException) {
            DataValidationException ex = (DataValidationException) re;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return handleExceptionInternal(ex, new ApiError(HttpStatus.BAD_REQUEST, ex.getError(), new Error("DataValidationException", ex.getError())), headers, HttpStatus.BAD_REQUEST, request);
        } else {
            NoContentFoundException ex = (NoContentFoundException) re;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return handleExceptionInternal(ex, new ApiError(HttpStatus.NOT_FOUND, ex.getError(), new Error("NoContentFoundException", ex.getError())), headers, HttpStatus.NOT_FOUND, request);
        }

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = new ArrayList<Error>();
        Error gserror;

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            gserror = new Error(error.getField(), error.getDefaultMessage());
            errors.add(gserror);
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            gserror = new Error(error.getObjectName(), error.getDefaultMessage());
            errors.add(gserror);
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

}
