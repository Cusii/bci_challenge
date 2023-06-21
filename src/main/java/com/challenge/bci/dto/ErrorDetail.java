package com.challenge.bci.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ErrorDetail {
    private Set<ErrorDTO> errors;
}
