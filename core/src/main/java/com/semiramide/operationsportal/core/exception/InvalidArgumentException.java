package com.semiramide.operationsportal.core.exception;

public class InvalidArgumentException extends OperationsPortalException {

  public InvalidArgumentException(String m) {
    super(ErrorCode.INVALID_ARGUMENT, m);
  }
}
