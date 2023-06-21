package com.challenge.bci.serviceImpl;

import com.challenge.bci.dto.*;
import com.challenge.bci.entity.PhoneEntity;
import com.challenge.bci.entity.UserEntity;
import com.challenge.bci.exception.RequestExceptions;
import com.challenge.bci.repository.UserRepository;
import com.challenge.bci.services.IUserService;
import com.challenge.bci.utils.JwtUtils;
import org.mindrot.jbcrypt.BCrypt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public LoginResponseDTO registerUser(UserDTO user) {

        String newToken = JwtUtils.generateToken(user.getName());

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        UserEntity userEntity = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(hashedPassword)
                .created(new Date())
                .lastLogin(new Date())
                .token(newToken)
                .isActive(true)
                .phones(user.getPhones().stream().map(PhoneEntity::fromDtoToEntity).collect(Collectors.toSet()))
                .build();
        userRepository.save(userEntity);

        return LoginResponseDTO.builder()
                .id(userEntity.getId())
                .token(userEntity.getToken())
                .created(userEntity.getCreated())
                .lastLogin(userEntity.getLastLogin())
                .isActive(userEntity.getIsActive())
                .build();
    }
    @Override
    public UserResponseDTO loginUser(String token){

        String userName = JwtUtils.extractNameFromToken(token);

        Optional<UserEntity> userEntity = userRepository.findByName(userName);
        if (userEntity.isPresent()) {
            UserEntity getUser = userEntity.get();
            return UserResponseDTO.builder()
                    .id(getUser.getId())
                    .token(getUser.getToken())
                    .name(getUser.getName())
                    .email(getUser.getEmail())
                    .password(getUser.getPassword())
                    .phones(getUser.getPhones().stream().map(PhoneDTO::mapping).collect(Collectors.toSet()))
                    .created(getUser.getCreated())
                    .lastLogin(getUser.getLastLogin())
                    .isActive(getUser.getIsActive())
                    .build();
        }else{
            throw new RequestExceptions(new Date(),401,"El token es invalido");
        }
    }
}