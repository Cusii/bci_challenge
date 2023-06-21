package com.challenge.bci.repository;

import com.challenge.bci.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNameOrEmail(String name, String email);

    Optional<UserEntity> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

}
