package com.belhard.bookstore_balash.data.dao;

import com.belhard.bookstore_balash.data.entity.User;

import java.util.List;

public interface UserDao {

    User findById(long id);

    User findByEmail(String email);

    List<User> findByLastName(String lastName);

    User findByLogin(String login);

    List<User> findAll();

    User create(User user);

    User update(User user);

    boolean delete(long id);

    long countAll();
}
