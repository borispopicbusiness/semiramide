package com.semiramide.operationsportal.core.exception;

public class WorklogDateLockedException extends OperationsPortalException {

  public WorklogDateLockedException(String message) {
    super(ErrorCode.WORKLOG_DATE_LOCKED, message);
  }
}
