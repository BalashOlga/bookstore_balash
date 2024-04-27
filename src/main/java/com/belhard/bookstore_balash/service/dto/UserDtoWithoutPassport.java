package com.belhard.bookstore_balash.service.dto;

import java.math.BigDecimal;

public class UserDtoWithoutPassport extends UserDto{

    public UserDtoWithoutPassport() {
        super();
    }

    public UserDtoWithoutPassport(Long id, String login, String password, String firstName, String lastName, String email, Long rolesId) {
        super(id, login, password, firstName, lastName, email, rolesId);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(null);
    }
}