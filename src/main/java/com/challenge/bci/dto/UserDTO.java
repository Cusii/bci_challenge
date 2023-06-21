package com.challenge.bci.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.*;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    
    private String name;
    @Email(message = "Erro en el formto del email")
    @NotEmpty(message = "El mail no puede ir vacio")
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Pattern(regexp = "((?=.*[A-Z]).*)((\\D*\\d){2,})", message = "" + "Debe contener 2 digitos y una mayuscula")
    @Size(min = 8, max = 12, message = "La contraseña tiene que ser mayor a 8 digitos")
    private String password;
    
    private Set<PhoneDTO> phones;
}
