package com.semiramide.operationsportal.core.exception;

public class WorklogNotFoundException extends OperationsPortalException {
  public WorklogNotFoundException(String m) {
    super(ErrorCode.WORKLOG_NOT_FOUND, m);
  }
}
