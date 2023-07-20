package com.deavensoft.operationsportal.adapters.api.exceptionhandler;

import com.deavensoft.operationsportal.adapters.api.response.ErrorResponse;
import com.deavensoft.operationsportal.core.exception.NoProjectFoundException;
import com.deavensoft.operationsportal.core.exception.ProjectNameAlreadyTakenException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProjectExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProjectNameAlreadyTakenException.class)
    public ErrorResponse handle(ProjectNameAlreadyTakenException exception) {
        return ErrorResponse.getInstance(exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoProjectFoundException.class)
    public ErrorResponse handleNoProjectFoundException(NoProjectFoundException exception) {
        return ErrorResponse.getInstance(exception);
    }
}
