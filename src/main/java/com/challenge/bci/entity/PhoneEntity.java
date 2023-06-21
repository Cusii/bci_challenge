package com.challenge.bci.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.challenge.bci.dto.PhoneDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phones")
public class PhoneEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Long number;
    
   @Column(name = "city_code")
    private Integer cityCode;
    
    @Column(name = "country_code")
    private String countryCode;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    public static PhoneEntity fromDtoToEntity(PhoneDTO dto) {
        return PhoneEntity.builder()
                .number(dto.getNumber())
                .cityCode(dto.getCityCode())
                .countryCode(dto.getCountryCode())
                .build();
    }
}
