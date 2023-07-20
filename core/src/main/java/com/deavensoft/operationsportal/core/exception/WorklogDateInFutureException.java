package com.deavensoft.operationsportal.core.exception;

public class WorklogDateInFutureException extends OperationsPortalException {

  public WorklogDateInFutureException(String message) {
    super(ErrorCode.WORKLOG_DATE_IN_FUTURE, message);
  }
}
