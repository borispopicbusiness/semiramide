package com.semiramide.operationsportal.core.exception;

public class CycleDetectedException extends OperationsPortalException {

  public CycleDetectedException(String m) {
    super(ErrorCode.CYCLE_DETECTED, m);
  }
}
