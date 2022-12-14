package com.isec.gps41.SmartSecurity.exception;

import com.isec.gps41.SmartSecurity.payload.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private String message;
    private HttpStatus status;
    private ErrorEnum errorCode;

    public ApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
