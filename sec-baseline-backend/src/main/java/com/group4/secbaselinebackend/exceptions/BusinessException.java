package com.group4.secbaselinebackend.exceptions;

import com.group4.secbaselinebackend.valueObjects.ErrorCode;
import com.group4.secbaselinebackend.valueObjects.ErrorMessage;
import org.springframework.stereotype.Service;


//自定义的异常
@Service
public class BusinessException extends RuntimeException {
    protected ErrorCode errorCode;
    protected ErrorMessage errorMessage;

    public BusinessException() {
        super();
    }

    public BusinessException(ErrorCode errorCode, ErrorMessage errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public String getErrorMessage() {
        return errorMessage.getMessage();
    }
}
