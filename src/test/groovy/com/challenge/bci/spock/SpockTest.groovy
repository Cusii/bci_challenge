package com.challenge.bci.spock

import com.challenge.bci.dto.LoginDTO
import com.challenge.bci.dto.LoginResponseDTO
import com.challenge.bci.dto.UserDTO
import com.challenge.bci.dto.PhoneDTO
import com.challenge.bci.dto.UserResponseDTO
import com.challenge.bci.entity.PhoneEntity
import com.challenge.bci.entity.UserEntity
import com.challenge.bci.exception.RequestExceptions
import com.challenge.bci.repository.UserRepository
import com.challenge.bci.serviceImpl.UserServiceImpl
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import java.util.stream.Collectors

class SpockTest extends Specification{

    @Subject
    private UserServiceImpl userService

    private UserRepository userRepository = Mock()

    UserEntity user;
    UserDTO userRequest;
    LoginDTO loginDTO;



    def setup() {
        userService = new UserServiceImpl(userRepository)
        PhoneEntity phones = new PhoneEntity();


        phones.setCityCode(123);
        phones.setCountryCode("ABC");
        phones.setId(1L);
        phones.setNumber(1234);

        user = UserEntity.builder().id("string").email("global@logic.co").password("globaLLaog14").name("global")
                .token("123").phones(Collections.singleton(phones))
                .lastLogin(new Date()).created(new Date()).isActive(true).build();

        userRequest = UserDTO.builder().email("global@logic.com").password("globaLLaog14").name("global")
                .phones(user.getPhones()
                        .stream().map(PhoneDTO::mapping).collect(Collectors.toSet())).build();

        loginDTO = LoginDTO.builder().name("global").password("globaLLaog14").build();
    }


    def "if user is null or not"() {
        given: //lo q va a ser mockeado
        userRepository.save(_ as UserEntity) >> user

        when:
        def result = userService.registerUser(userRequest)

        then:
        result != null

    }

    def "Login"() {
        given: //lo q va a ser mockeado
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXZpZCIsImV4cCI6MTY4NzM2NDk5OX0.YUq-ZIJ-_abVAiZbFxw_nplAvOIRFrlAyvKMynH9eF4"
        userRepository.findByName(_ as String) >> Optional.of(user)

        when:
        def result = userService.loginUser(token)

        then:
        result != null

    }

}