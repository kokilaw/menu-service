package com.au.eatclub.menu.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {

    private Map<String, String> parameters;

    public NotFoundException(String message, Map<String, String> parameters) {
        super(message);
        this.parameters = parameters;
    }

}
