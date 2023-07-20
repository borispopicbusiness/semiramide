package com.deavensoft.operationsportal.core.exception;

public class NotReachableNodeException extends OperationsPortalException {
  public NotReachableNodeException(String m) {
    super(ErrorCode.NODES_ARE_NOT_CONNECTED, m);
  }
}
