package com.is4tech.practicas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SyntaxErrorException extends RuntimeException {
    private static final long serialVersionUID = -2265661485352624884L;

    public SyntaxErrorException(String message) {
        super(message);
    }
}
