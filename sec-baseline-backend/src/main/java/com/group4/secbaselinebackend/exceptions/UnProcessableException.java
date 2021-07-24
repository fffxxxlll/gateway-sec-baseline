package com.group4.secbaselinebackend.exceptions;

import com.group4.secbaselinebackend.valueObjects.ErrorCode;
import com.group4.secbaselinebackend.valueObjects.ErrorMessage;
import org.springframework.stereotype.Service;


//自定义的异常
@Service
public class UnProcessableException extends BusinessException {
    private ErrorCode errorCode;
    private ErrorMessage errorMessage;
    private String customMessage;


    public UnProcessableException() {
        super();
    }

    public UnProcessableException(ErrorMessage errorMessage) {
        this.errorCode = ErrorCode.UNPROCESSABLE_ENTITY;
        this.errorMessage = errorMessage;
    }

    public UnProcessableException(String message) {
        this.errorCode = ErrorCode.UNPROCESSABLE_ENTITY;
        this.customMessage = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode.getCode();
    }

    @Override
    public String getErrorMessage() {
        if (customMessage == null) {
            return errorMessage.getMessage();
        }
        return customMessage;
    }
}