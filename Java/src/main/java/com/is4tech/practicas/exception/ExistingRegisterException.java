package com.is4tech.practicas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistingRegisterException extends  RuntimeException{
    private static final long serialVersionUID = -2265661485352624884L;

    public ExistingRegisterException(String message) {
        super(message);
    }
}
