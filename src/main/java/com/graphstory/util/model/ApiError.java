package com.graphstory.util.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

	@Getter
    @Setter
    private HttpStatus status;
	@Getter
    @Setter
    private String message;
	@Getter
    @Setter
    private List<Error> errors;
 
    public ApiError(HttpStatus status, String message, List<Error> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
 
    public ApiError(HttpStatus status, String message, Error error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}