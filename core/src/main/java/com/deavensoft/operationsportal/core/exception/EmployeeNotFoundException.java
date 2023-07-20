package com.deavensoft.operationsportal.core.exception;

public class EmployeeNotFoundException extends OperationsPortalException {

  public EmployeeNotFoundException(String m) {
    super(ErrorCode.USER_NOT_FOUND, m);
  }
}
