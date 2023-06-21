package com.challenge.bci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    private Date timestamp;
    private Integer codigo;
    private String detail;
}
