package com.challenge.bci.dto;

import com.challenge.bci.entity.PhoneEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    
    private Long number;

    private Integer cityCode;
    
    private String countryCode;
    
    public static PhoneDTO mapping(PhoneEntity entity) {
        return PhoneDTO.builder()
                .cityCode(entity.getCityCode())
                .number(entity.getNumber())
                .countryCode(entity.getCountryCode())
                .build();
    }
}
