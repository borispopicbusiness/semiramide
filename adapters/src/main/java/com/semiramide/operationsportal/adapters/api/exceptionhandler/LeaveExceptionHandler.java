package com.semiramide.operationsportal.adapters.api.exceptionhandler;

import com.semiramide.operationsportal.adapters.api.response.ErrorResponse;
import com.semiramide.operationsportal.core.exception.FreeDaysLeftException;
import com.semiramide.operationsportal.core.exception.LeaveStartAfterEndException;
import com.semiramide.operationsportal.core.exception.LeaveStartsInThePastException;
import com.semiramide.operationsportal.core.exception.LeavesOverlapException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LeaveExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FreeDaysLeftException.class)
    public ErrorResponse handleFreeDaysLeftException(FreeDaysLeftException exception) {
        return ErrorResponse.getInstance(exception);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(LeavesOverlapException.class)
    public ErrorResponse handleLeavesOverlapException(LeavesOverlapException exception) {
        return ErrorResponse.getInstance(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LeaveStartAfterEndException.class)
    public ErrorResponse handleLeaveStartAfterEndException(LeaveStartAfterEndException exception) {
        return ErrorResponse.getInstance(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LeaveStartsInThePastException.class)
    public ErrorResponse handleLeaveStartsInThePastException(LeaveStartsInThePastException exception) {
        return ErrorResponse.getInstance(exception);
    }
}
