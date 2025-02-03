package org.example.sotre.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoStockException extends RuntimeException {
    public NoStockException(String message) {
        super(message);
    }
}
