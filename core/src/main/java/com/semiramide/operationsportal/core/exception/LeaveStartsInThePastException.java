package com.semiramide.operationsportal.core.exception;

public class LeaveStartsInThePastException extends OperationsPortalException {

  public LeaveStartsInThePastException(String message) {
    super(ErrorCode.LEAVE_START_IN_PAST, message);
  }
}
