package com.deavensoft.operationsportal.adapters.api.exceptionhandler;

import com.deavensoft.operationsportal.adapters.api.response.ErrorResponse;
import com.deavensoft.operationsportal.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGeneralException(Exception exception) {
        exception.printStackTrace(); // TODO: once logging is properly done remove this line
        return ErrorResponse.builder()
                .errorCode(ErrorCode.GENERAL_EXCEPTION)
                .errorMessage("Something went wrong. Contact your administrator!")
                .build();
    }

}
