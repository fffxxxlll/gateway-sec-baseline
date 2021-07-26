package com.group4.secbaselinebackend.exceptions;


import com.group4.secbaselinebackend.valueObjects.ErrorCode;
import com.group4.secbaselinebackend.valueObjects.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;


//自定义的异常
public class ConflictException extends DataIntegrityViolationException {

    private ErrorCode errorCode;
    private ErrorMessage errorMessage;

    private String argument;

    public ConflictException(ErrorCode errorCode, ErrorMessage errorMessage) {
        super("");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ConflictException(ErrorCode errorCode, ErrorMessage errorMessage, String argument) {
        super("");
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.argument = argument;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public String getErrorMessage() {
        if (argument != null) {
            return argument + errorMessage.getMessage();
        }
        return errorMessage.getMessage();
    }
}
