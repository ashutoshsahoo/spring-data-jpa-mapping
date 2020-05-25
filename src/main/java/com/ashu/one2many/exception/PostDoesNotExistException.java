package com.ashu.one2many.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PostDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostDoesNotExistException() {
		super("Post not found");
	}

	public PostDoesNotExistException(String id) {
		super("POst not found for requested id =" + id);
	}

}
