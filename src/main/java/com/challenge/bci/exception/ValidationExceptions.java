package com.challenge.bci.exception;

import lombok.Data;
import java.util.Date;

@Data
public class ValidationExceptions extends RuntimeException {
    private Integer codigo;
    private Date timestamp;
    private String primerError;

    public ValidationExceptions(Date timestamp, Integer codigo, String detalle) {
        super(detalle);
        this.timestamp = timestamp;
        this.codigo = codigo;
    }
}

