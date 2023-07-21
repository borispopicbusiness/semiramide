package com.semiramide.operationsportal.core.exception;

import lombok.Getter;

@Getter
public class OperationsPortalException extends RuntimeException {

  private final String code;
  private final String message;

  public OperationsPortalException(String c, String m) {
    super(m);
    this.code = c;
    this.message = m;
  }
}
