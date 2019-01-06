package com.graphstory.util.model;

import lombok.Getter;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {

	@Getter
    private String error;

	public EntityNotFoundException(String error) {
		super();
		this.error = error;
	}
}
