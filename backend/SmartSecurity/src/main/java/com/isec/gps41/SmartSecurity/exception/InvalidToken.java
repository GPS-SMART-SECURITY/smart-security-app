package com.isec.gps41.SmartSecurity.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor

public class InvalidToken extends ApiException{

    public InvalidToken(String message, HttpStatus status) {
        super(message, status);
    }
}
