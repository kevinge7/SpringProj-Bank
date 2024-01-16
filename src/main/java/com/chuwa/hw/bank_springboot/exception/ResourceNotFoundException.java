package com.chuwa.hw.bank_springboot.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fileName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fileName, long fieldValue){
        super(String.format("%s not found with %s : %s", resourceName, fileName, fieldValue));
        this.resourceName = resourceName;
        this.fileName = fileName;
        this.fieldValue = fieldValue;
    }
}
