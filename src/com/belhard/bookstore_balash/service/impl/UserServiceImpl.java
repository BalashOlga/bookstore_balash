package com.belhard.bookstore_balash.service.impl;

import com.belhard.bookstore_balash.data.dao.UserDao;
import com.belhard.bookstore_balash.data.dao.impl.UserDaoImpl;
import com.belhard.bookstore_balash.data.entity.User;
import com.belhard.bookstore_balash.service.UserService;
import com.belhard.bookstore_balash.service.dto.UserDto;
import com.belhard.bookstore_balash.service.dto.UserDtoWithoutPassport;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRolesId(user.getRolesId());

        return userDto;
    }

    private UserDto toDtoWithoutPassport(User user) {
        UserDtoWithoutPassport  userDto = new UserDtoWithoutPassport();

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRolesId(user.getRolesId());

        return userDto;
    }

    private User toUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setRolesId(userDto.getRolesId());

        return user;
    }

    @Override
    public UserDto getById(long id) {
        return toDtoWithoutPassport(userDao.findById(id));
    }

    @Override
    public UserDto getByEmail(String email) {
        return toDtoWithoutPassport(userDao.findByEmail(email));
    }

    @Override
    public List<UserDto> getByLastName(String lastName) {
        return userDao.findByLastName(lastName)
                .stream()
                .map(this::toDtoWithoutPassport)
                .toList();
    }

    @Override
    public UserDto getByLogin(String login) {
        return toDtoWithoutPassport(userDao.findByLogin(login));
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.findAll()
                .stream()
                .map(this::toDtoWithoutPassport)
                .toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        return toDto(userDao.create(toUser(userDto)));
    }


    @Override
    public UserDto update(UserDto userDto) {
        return toDto(userDao.update(toUser(userDto)));
    }

    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
    }

    @Override
    public long getcountAll() {
        return userDao.countAll();
    }
}
