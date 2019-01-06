package com.graphstory.util.model;

import lombok.Getter;

@SuppressWarnings("serial")
public class NoContentFoundException extends RuntimeException {
	@Getter
    private String error;

	public NoContentFoundException(String error) {
		super();
		this.error = error;
	}
}
