package com.semiramide.operationsportal.core.exception;

public class FreeDaysLeftException extends OperationsPortalException {
  public FreeDaysLeftException(String m) {
    super(ErrorCode.LESS_FREE_DAYS_LEFT_THAN_REQUESTED, m);
  }
}
