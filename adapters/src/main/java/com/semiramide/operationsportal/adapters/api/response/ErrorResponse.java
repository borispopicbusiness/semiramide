package com.semiramide.operationsportal.adapters.api.response;

import com.semiramide.operationsportal.core.exception.OperationsPortalException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    public static ErrorResponse getInstance(OperationsPortalException exception) {
        return ErrorResponse.builder()
                .errorCode(exception.getCode())
                .errorMessage(exception.getMessage())
                .build();
    }

}
