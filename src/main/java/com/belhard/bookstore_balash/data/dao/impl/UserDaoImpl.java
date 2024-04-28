package com.belhard.bookstore_balash.data.dao.impl;

import com.belhard.bookstore_balash.data.dao.UserDao;
import com.belhard.bookstore_balash.data.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getFormatterLogger(UserDaoImpl.class);
    public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String USER = "postgres";
    public static final String PASSWOORD = "123";
    private static final String FIND_BY_ID = "SELECT id, login, password, first_name, last_name, email, roles_id FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL = "SELECT id, login, password, first_name, last_name, email, roles_id FROM users WHERE email = ?";
    private static final String FIND_BY_LAST_NAME = "SELECT id, login, password, first_name, last_name, email, roles_id FROM users WHERE last_name  = ?";
    private static final String FIND_BY_LOGIN = "SELECT id, login, password, first_name, last_name, email, roles_id FROM users WHERE login = ?";
    private static final String FIND_ALL = "SELECT id, login, password, first_name, last_name, email, roles_id FROM users WHERE 1 = ?";
    private static final String CREATE = "INSERT INTO users (login, password, first_name, last_name, email, roles_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE users SET login = ?, password = ?, first_name = ?,  last_name = ?, email = ?, roles_id = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private static final String COUNT_ALL = "SELECT count(*) FROM users WHERE 1 = ?;";

    private ResultSet getResultSet(String sql, String value) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {
            log.info("Connection get successfully");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(" UserDaoImpl :private ResultSet getStatement(String sql, String value) ");
        }
    }

    private ResultSet getResultSet(String sql, long value) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {
            log.info("Connection get successfully");

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, value);

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl : ");
        }
    }

    @Override
    public User findById(long id) {
        try {
            ResultSet users = getResultSet(FIND_BY_ID, id);
            log.debug("Select FIND_BY_ID has been completed");

            User user = new User();

            if (users.next()) {
                user.setId(users.getLong("id"));
                user.setLogin(users.getString("login"));
                user.setPassword(users.getString("password"));
                user.setFirstName(users.getString("first_name"));
                user.setLastName(users.getString("last_name"));
                user.setEmail(users.getString("email"));
                user.setRolesId(users.getLong("roles_id"));
                System.out.println(user);
            }
            return user;

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl: public User findById(long id)");
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            ResultSet users = getResultSet(FIND_BY_EMAIL, email);
            log.debug("Select FIND_BY_EMAIL has been completed");

            User user = new User();

            if (users.next()) {
                user.setId(users.getLong("id"));
                user.setLogin(users.getString("login"));
                user.setPassword(users.getString("password"));
                user.setFirstName(users.getString("first_name"));
                user.setLastName(users.getString("last_name"));
                user.setEmail(users.getString("email"));
                user.setRolesId(users.getLong("roles_id"));
                System.out.println(user);
            }
            return user;

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl: public User findByEmail(String email) ");
        }
    }

    @Override
    public List<User> findAll() {
        try {
            ResultSet users = getResultSet(FIND_ALL, 1);
            log.debug("Select FIND_ALL has been comleted");

            List<User> listUser = new ArrayList<>();

            while (users.next()) {
                User user = new User();
                user.setId(users.getLong("id"));
                user.setLogin(users.getString("login"));
                user.setPassword(users.getString("password"));
                user.setFirstName(users.getString("first_name"));
                user.setLastName(users.getString("last_name"));
                user.setEmail(users.getString("email"));
                user.setRolesId(users.getLong("roles_id"));

                listUser.add(user);

                System.out.println(user);
            }
            return listUser;

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl: public List<User> findAll()");
        }
    }

    @Override
    public User findByLogin(String login) {
        try {
            ResultSet users = getResultSet(FIND_BY_LOGIN, login);
            log.debug("Select FIND_BY_LOGIN has been completed");

            User user = new User();

            if (users.next()) {
                user.setId(users.getLong("id"));
                user.setLogin(users.getString("login"));
                user.setPassword(users.getString("password"));
                user.setFirstName(users.getString("first_name"));
                user.setLastName(users.getString("last_name"));
                user.setEmail(users.getString("email"));
                user.setRolesId(users.getLong("roles_id"));

                System.out.println(user);
            }
            return user;

        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl: public User findByLogin(String login)");
        }
    }

    @Override
    public User create(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {
            log.info("Connection get successfully");

            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setLong(6, user.getRolesId());

            statement.executeUpdate();
            log.debug("Update CREATE has been completed");

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                return findById(id);
            } else {
                return new User();
            }

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl:  public User create(User user)");
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {
            log.info("Connection get successfully");

            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setLong(6, user.getRolesId());
            statement.setLong(7, user.getId());

            statement.executeUpdate();
            log.debug("Update UPDATE has been completed");

            return findById(user.getId());

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl: public boolean delete(long id)");
        }
    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {
            log.info("Connection get successfully");

            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            int i = statement.executeUpdate();
            log.debug("Update DELETE has been completed");

            return (i > 0);

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl:public boolean delete(long id)");
        }
    }

    @Override
    public long countAll() {
        try {
            ResultSet users = getResultSet(COUNT_ALL, 1);
            log.debug("Select COUNT_ALL has been completed ");

            users.next();
            long count = users.getLong(1);

            return count;

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl:public long countAll()");
        }
    }

    @Override
    public List<User> findByLastName(String lastName) {
        try {
            ResultSet users = getResultSet(FIND_BY_LAST_NAME, lastName);
            log.debug("Select FIND_BY_LAST_NAME has been completed");

            List<User> listUser = new ArrayList<>();

            while (users.next()) {
                User user = new User();
                user.setId(users.getLong("id"));
                user.setLogin(users.getString("login"));
                user.setPassword(users.getString("password"));
                user.setFirstName(users.getString("first_name"));
                user.setLastName(users.getString("last_name"));
                user.setEmail(users.getString("email"));
                user.setRolesId(users.getLong("roles_id"));

                listUser.add(user);

                System.out.println(user);
            }
            return listUser;

        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("UserDaoImpl: public List<User> findByLastName(String lastName)");
        }
    }
}
