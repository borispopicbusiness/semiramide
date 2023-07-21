package com.semiramide.operationsportal.core.exception;

public class NoProjectFoundException extends OperationsPortalException {
  public NoProjectFoundException(String m) {
    super(ErrorCode.NO_PROJECT_FOUND, m);
  }
}
