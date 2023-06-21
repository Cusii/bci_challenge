package com.challenge.bci.services;

import com.challenge.bci.dto.LoginResponseDTO;
import com.challenge.bci.dto.UserDTO;
import com.challenge.bci.dto.UserResponseDTO;

public interface IUserService {

     public LoginResponseDTO registerUser(UserDTO user) ;
     
     public UserResponseDTO loginUser(String token) ;
}
