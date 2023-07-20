package com.deavensoft.operationsportal.core.exception;

public class LeavesOverlapException extends OperationsPortalException {

  public LeavesOverlapException(String message) {
    super(ErrorCode.LEAVES_OVERLAP, message);
  }
}
