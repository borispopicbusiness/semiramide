package com.deavensoft.operationsportal.core.exception;

public class EmailAlreadyExistsException extends OperationsPortalException {

  public EmailAlreadyExistsException(String m) {
    super(ErrorCode.EMAIL_ALREADY_EXISTS, m);
  }
}
