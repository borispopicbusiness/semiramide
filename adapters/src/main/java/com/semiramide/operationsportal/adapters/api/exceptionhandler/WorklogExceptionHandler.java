package com.semiramide.operationsportal.adapters.api.exceptionhandler;

import com.semiramide.operationsportal.adapters.api.response.ErrorResponse;
import com.semiramide.operationsportal.core.exception.WorklogDateInFutureException;
import com.semiramide.operationsportal.core.exception.WorklogDateLockedException;
import com.semiramide.operationsportal.core.exception.WorklogNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WorklogExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WorklogNotFoundException.class)
    public ErrorResponse handleWorklogNotFoundException(WorklogNotFoundException exception) {
        return ErrorResponse.getInstance(exception);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(WorklogDateLockedException.class)
    public ErrorResponse handleWorklogDateLockedException(WorklogDateLockedException exception) {
        return ErrorResponse.getInstance(exception);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(WorklogDateInFutureException.class)
    public ErrorResponse handleWorklogDateInFutureException(WorklogDateInFutureException exception) {
        return ErrorResponse.getInstance(exception);
    }

}
