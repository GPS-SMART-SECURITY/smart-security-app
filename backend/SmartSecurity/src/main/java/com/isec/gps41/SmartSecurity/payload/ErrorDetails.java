package com.isec.gps41.SmartSecurity.payload;

import com.isec.gps41.SmartSecurity.payload.enums.ErrorEnum;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private ErrorEnum errorCode;


    public ErrorDetails(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public void setErrorCode(ErrorEnum errorCode) {
        this.errorCode = errorCode;
    }
}
