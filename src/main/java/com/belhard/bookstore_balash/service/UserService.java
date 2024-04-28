package com.belhard.bookstore_balash.service;

import com.belhard.bookstore_balash.service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getById(long id);

    UserDto getByEmail(String email);

    List<UserDto> getByLastName(String lastName);

    UserDto getByLogin(String login);

    List<UserDto> getAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    boolean delete(long id);

    long getCountAll();

    UserDto login(String login, String password);
}
