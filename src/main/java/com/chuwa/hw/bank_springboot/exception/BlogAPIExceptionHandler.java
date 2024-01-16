package com.chuwa.hw.bank_springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@Setter
@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogAPIExceptionHandler extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public BlogAPIExceptionHandler(String message, HttpStatus httpStatus, String message1){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }




}
