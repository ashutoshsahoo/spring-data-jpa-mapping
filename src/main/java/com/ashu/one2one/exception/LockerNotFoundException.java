package com.ashu.one2one.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LockerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LockerNotFoundException() {
		super("Locker not found.");
	}

	public LockerNotFoundException(Long id) {
		super("Locker not found for requested id = " + id);
	}
}
