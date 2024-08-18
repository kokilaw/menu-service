package com.au.eatclub.menu.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ErrorResponse {
    private int httpCode;
    private String message;
    private Map<String, String> parameters;
    private String requestId;
}
