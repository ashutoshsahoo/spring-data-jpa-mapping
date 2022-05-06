package com.ashu.one2one.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4026768534867475430L;

    public EmployeeNotFoundException() {
        super("Employee not found.");
    }

    public EmployeeNotFoundException(Long id) {
        super("Employee not found for requested id = " + id);
    }

}
