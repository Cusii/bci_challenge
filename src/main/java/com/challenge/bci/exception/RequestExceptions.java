package com.challenge.bci.exception;

import lombok.Data;
import java.util.Date;

@Data
public class RequestExceptions extends RuntimeException{
    private Integer codigo;
    private Date timestamp;
    public RequestExceptions (Date timestamp, Integer code, String detail){
        super(detail);
        this.timestamp = timestamp;
        this.codigo = code;

    }
}
