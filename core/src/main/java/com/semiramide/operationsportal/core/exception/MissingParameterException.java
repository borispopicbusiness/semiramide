package com.semiramide.operationsportal.core.exception;

public class MissingParameterException extends OperationsPortalException {

    public MissingParameterException(String message) {
        super(ErrorCode.MISSING_PARAMETER, message);
    }

}
