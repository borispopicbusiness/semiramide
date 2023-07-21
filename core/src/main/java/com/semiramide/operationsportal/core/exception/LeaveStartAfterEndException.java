package com.semiramide.operationsportal.core.exception;

public class LeaveStartAfterEndException extends OperationsPortalException {

  public LeaveStartAfterEndException(String message) {
    super(ErrorCode.LEAVE_START_AFTER_END, message);
  }
}
