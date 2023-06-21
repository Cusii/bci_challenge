package com.challenge.bci.dto;

import com.challenge.bci.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends UserDTO {

    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;
    
    private Boolean isActive;

    public static UserResponseDTO fromUserEntity(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .token(user.getToken())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(user.getPhones().stream().map(PhoneDTO::mapping).collect(Collectors.toSet()))
                .created(user.getCreated())
                .lastLogin(user.getLastLogin())
                .isActive(user.getIsActive())
                .build();
    }
}
