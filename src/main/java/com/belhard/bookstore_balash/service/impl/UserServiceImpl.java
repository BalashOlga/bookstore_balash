package com.belhard.bookstore_balash.service.impl;

import com.belhard.bookstore_balash.data.dao.UserDao;
import com.belhard.bookstore_balash.data.dao.impl.UserDaoImpl;
import com.belhard.bookstore_balash.data.entity.User;
import com.belhard.bookstore_balash.service.UserService;
import com.belhard.bookstore_balash.service.dto.UserDto;
import com.belhard.bookstore_balash.service.dto.UserDtoWithoutPassport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getFormatterLogger(UserServiceImpl.class);
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
        log.debug("Calling getById");
        return toDtoWithoutPassport(userDao.findById(id));
    }

    @Override
    public UserDto getByEmail(String email) {
        log.debug("Calling getByEmail");
        return toDtoWithoutPassport(userDao.findByEmail(email));
    }

    @Override
    public List<UserDto> getByLastName(String lastName) {
        log.debug("Calling getByLastName");
        return userDao.findByLastName(lastName)
                .stream()
                .map(this::toDtoWithoutPassport)
                .toList();
    }

    @Override
    public UserDto getByLogin(String login) {
        log.debug("Calling getByLogin");
        return toDtoWithoutPassport(userDao.findByLogin(login));
    }

    @Override
    public List<UserDto> getAll() {
        log.debug("Calling getAll");
        return userDao.findAll()
                .stream()
                .map(this::toDtoWithoutPassport)
                .toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Calling create");
        return toDto(userDao.create(toUser(userDto)));
    }


    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Calling update");
        return toDto(userDao.update(toUser(userDto)));
    }

    @Override
    public boolean delete(long id) {
        log.debug("Calling delete");
        return userDao.delete(id);
    }

    @Override
    public long getCountAll() {
        log.debug("Calling getCountAll");
        return userDao.countAll();
    }

    @Override
    public UserDto login(String login, String password) {
        log.debug("Calling login");
        UserDto userDto = toDto(userDao.findByLogin(login));

        if (password.equals(userDto.getPassword())) {
            return userDto;
        } else {
            return new UserDto();
        }
    }
}
