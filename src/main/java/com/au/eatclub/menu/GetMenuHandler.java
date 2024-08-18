package com.au.eatclub.menu;

import com.au.eatclub.menu.api.request.InputObject;
import com.au.eatclub.menu.api.response.ErrorResponse;
import com.au.eatclub.menu.api.response.OutputObject;
import com.au.eatclub.menu.exception.NotFoundException;
import com.au.eatclub.menu.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named("getMenuHandler")
public class GetMenuHandler implements RequestHandler<InputObject, OutputObject<?>> {

    @Inject
    MenuService menuService;

    @Inject
    ObjectMapper objectMapper;

    @Override
    public OutputObject<?> handleRequest(InputObject input, Context context) {
        String awsRequestId = context.getAwsRequestId();
        try {
            log.info("Request received to fetch the menu - {}", input);
            return menuService.getMenu(input.getRestaurantId())
                    .setRequestId(awsRequestId)
                    .setSuccess(true);
        } catch (Exception e) {
            log.error("Error occurred while fetching menu - {} -", input, e);
            String message = getExceptionMessage(e, awsRequestId);
            throw new RuntimeException(message, e);
        }
    }

    @SneakyThrows
    private String getExceptionMessage(Exception e, String awsRequestId) {
        if (e instanceof NotFoundException exception) {
            ErrorResponse notFoundError = ErrorResponse.builder()
                    .httpCode(404)
                    .message(exception.getMessage())
                    .parameters(exception.getParameters())
                    .requestId(awsRequestId)
                    .build();
            return objectMapper.writeValueAsString(notFoundError);
        } else {
            ErrorResponse genericError = ErrorResponse.builder()
                    .httpCode(500)
                    .message("Internal Server Error")
                    .requestId(awsRequestId)
                    .build();
            return objectMapper.writeValueAsString(genericError);
        }
    }

}
