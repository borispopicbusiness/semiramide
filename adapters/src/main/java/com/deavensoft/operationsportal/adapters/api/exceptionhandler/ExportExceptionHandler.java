package com.deavensoft.operationsportal.adapters.api.exceptionhandler;

import com.deavensoft.operationsportal.adapters.api.response.ErrorResponse;
import com.deavensoft.operationsportal.core.exception.MissingParameterException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExportExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingParameterException.class)
    public ErrorResponse handleEmailAlreadyExistsException(MissingParameterException exception) {
        return ErrorResponse.getInstance(exception);
    }

}
