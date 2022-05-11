package org.jannal.aggregation.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.jannal.aggregation.common.ApiResponse;
import org.jannal.common.exception.ValidateParamsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ValidateParamsException.class})
    public ApiResponse<?> validateParamsExceptionHandler(Exception e) {
        ValidateParamsException paramsException = (ValidateParamsException) e;
        log.warn(paramsException.getMsg(), e);
        return ApiResponse.valueOf(paramsException.getStatus(), paramsException.getMsg());
    }

}
