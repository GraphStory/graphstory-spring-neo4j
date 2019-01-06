package com.graphstory.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Error {

	@Getter
    @Setter
    private String name;
	@Getter
    @Setter
    private String message;
}
