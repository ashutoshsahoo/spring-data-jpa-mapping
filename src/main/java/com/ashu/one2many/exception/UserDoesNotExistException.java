package com.ashu.one2many.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserDoesNotExistException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -8200503702525879996L;

	public UserDoesNotExistException() {
		super("User not found");
	}

	public UserDoesNotExistException(Long id) {
		super("User not found for requested id = " + id);
	}

}
