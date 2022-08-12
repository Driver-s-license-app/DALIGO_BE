package com.daligo.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GeneralResponse {
    private int code;
    private String message;

    public static GeneralResponse of(HttpStatus httpStatus, String message) {
        return new GeneralResponse(httpStatus.value(), message);
    }
}
