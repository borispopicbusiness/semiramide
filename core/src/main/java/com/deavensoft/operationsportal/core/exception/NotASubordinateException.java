package com.deavensoft.operationsportal.core.exception;

public class NotASubordinateException extends OperationsPortalException {

  public NotASubordinateException(String m) {
    super(ErrorCode.NOT_A_SUBORDINATE, m);
  }
}
