package com.group4.secbaselinebackend.exceptions;


import com.group4.secbaselinebackend.valueObjects.ErrorCode;
import com.group4.secbaselinebackend.valueObjects.ErrorMessage;

//自定义的异常
public class InvalidObjectSelectionException extends BusinessException {

    public InvalidObjectSelectionException(){
        this.errorCode = ErrorCode.INVALID_OBJECT_SELECTION;
        this.errorMessage = ErrorMessage.INVALID_OBJECT_SELECTION;
    }

    public InvalidObjectSelectionException(String select){
        this();
        this.select = select;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    private String select;
}
