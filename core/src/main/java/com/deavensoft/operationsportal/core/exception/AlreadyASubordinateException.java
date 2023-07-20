package com.deavensoft.operationsportal.core.exception;

public class AlreadyASubordinateException extends OperationsPortalException {

  public AlreadyASubordinateException(String m) {
    super(ErrorCode.ALREADY_A_SUBORDINATE, m);
  }
}
