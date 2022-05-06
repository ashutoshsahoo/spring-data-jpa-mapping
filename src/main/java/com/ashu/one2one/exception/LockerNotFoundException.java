package com.ashu.one2one.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LockerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6593664910337396267L;

    public LockerNotFoundException() {
        super("Locker not found.");
    }

    public LockerNotFoundException(Long id) {
        super("Locker not found for requested id = " + id);
    }
}
