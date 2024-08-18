package com.au.eatclub.menu.api.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class OutputObject<T> {
    private T result;
    private String requestId;
    private boolean isSuccess;
}
