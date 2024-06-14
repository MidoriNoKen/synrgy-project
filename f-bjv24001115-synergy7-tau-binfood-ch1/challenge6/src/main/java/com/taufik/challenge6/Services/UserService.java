package com.taufik.challenge6.Services;

import java.util.List;
import java.util.UUID;

import com.taufik.challenge6.Models.Dtos.User.UserCreateRequestDto;
import com.taufik.challenge6.Models.Dtos.User.UserDto;
import com.taufik.challenge6.Models.Dtos.User.UserUpdateRequestDto;
import com.taufik.challenge6.Models.Entities.Auth.User;

public interface UserService {
    // CREATE
    UserDto create(UserCreateRequestDto userCreateRequestDto);

    void createUserPostLogin(String name, String email);

    // READ
    User getById(UUID id);

    UserDto getDtoById(UUID id);

    User getByUsername(String username);

    User getByEmail(String email);

    UserDto getByUsernameAndPassword(String username, String password);

    List<UserDto> getList();

    // UPDATE
    UserDto update(UUID id, UserUpdateRequestDto userUpdateRequestDto);

    // DELETE
    void safeDeleteById(UUID id);
}
