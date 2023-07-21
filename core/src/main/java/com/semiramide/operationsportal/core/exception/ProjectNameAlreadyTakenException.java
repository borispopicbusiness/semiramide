package com.semiramide.operationsportal.core.exception;

public class ProjectNameAlreadyTakenException extends OperationsPortalException {

  public ProjectNameAlreadyTakenException(String m) {
    super(ErrorCode.NAME_ALREADY_TAKEN, m);
  }
}
