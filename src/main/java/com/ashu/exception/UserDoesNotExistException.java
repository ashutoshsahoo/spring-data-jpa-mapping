package com.ashu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserDoesNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserDoesNotExistException() {
	super("User not found");
    }

    public UserDoesNotExistException(Long id) {
	super("User not found for requested id = " + id);
    }

}
