package com.graphstory.util.model;

import lombok.Getter;

@SuppressWarnings("serial")
public class DataValidationException extends RuntimeException {

	@Getter
    private String error;

	public DataValidationException(String error) {
		super();
		this.error = error;
	}
}
