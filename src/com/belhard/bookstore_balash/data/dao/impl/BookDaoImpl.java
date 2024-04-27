package com.belhard.bookstore_balash.data.dao.impl;

import com.belhard.bookstore_balash.data.dao.BookDao;
import com.belhard.bookstore_balash.data.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_bh";
    public static final String USER = "postgres";
    public static final String PASSWOORD = "123";
    private static final String FIND_BY_ID = "SELECT id, author, isbn, year, cost FROM books WHERE id = ?";
    private static final String FIND_BY_ISBN = "SELECT id, author, isbn, year, cost FROM books WHERE isbn = ?";
    private static final String FIND_BY_AUTHOR = "SELECT id, author, isbn, year, cost FROM books WHERE author = ?";
    private static final String FIND_ALL = "SELECT id, author, isbn, year, cost FROM books WHERE 1 = ?";
    public static final String CREATE = "INSERT INTO books (author, isbn, year,  cost)  VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE books SET author = ?, isbn = ?, year = ?,  cost = ? WHERE id = ?;";
    public static final String DELETE = "DELETE FROM books WHERE id = ?";
    public static final String COUNT_ALL = "SELECT count(*) FROM books WHERE 1 = ?;";

    private ResultSet getResultSet(String sql, String value) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(" BookDaoImpl:private ResultSet getStatement(String sql, String value) ");
        }
    }

    private ResultSet getResultSet(String sql, long value) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, value);

            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: ");
        }
    }

    @Override
    public Book findById(long id) {
        try {
            ResultSet books = getResultSet(FIND_BY_ID , id);

            Book book = new Book();

            if (books.next()) {
                book.setId(books.getLong("id"));
                book.setAuthor(books.getString("author"));
                book.setIsbn(books.getString("isbn"));
                book.setYear(books.getInt("year"));
                book.setCost(books.getBigDecimal("cost"));
                System.out.println(book);
            }
            return book;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public Book findById(long id)");
        }
    }

    @Override
    public Book findByIsbn(String isbn) {
        try {
            ResultSet books = getResultSet(FIND_BY_ISBN, isbn);

            Book book = new Book();

            if (books.next()) {
                book.setId(books.getLong("id"));
                book.setAuthor(books.getString("author"));
                book.setIsbn(books.getString("isbn"));
                book.setYear(books.getInt("year"));
                book.setCost(books.getBigDecimal("cost"));
                System.out.println(book);
            }
            return book;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public Book findByIsbn(String isbn)");
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            ResultSet books = getResultSet(FIND_ALL, 1);

            List<Book> listBook = new ArrayList<>();

            while (books.next()) {

                Book book = new Book();
                book.setId(books.getLong("id"));
                book.setAuthor(books.getString("author"));
                book.setIsbn(books.getString("isbn"));
                book.setYear(books.getInt("year"));
                book.setCost(books.getBigDecimal("cost"));

                listBook.add(book);

                System.out.println(book);
            }
            return listBook;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl:  public List<Book> findAll()");
        }
    }

    @Override
    public List<Book> findByAuthor(String author) {

        try {
            ResultSet books = getResultSet(FIND_BY_AUTHOR, author);

            List<Book> listBook = new ArrayList<>();

            while (books.next()) {
                Book book = new Book();
                book.setId(books.getLong("id"));
                book.setAuthor(books.getString("author"));
                book.setIsbn(books.getString("isbn"));
                book.setYear(books.getInt("year"));
                book.setCost(books.getBigDecimal("cost"));

                listBook.add(book);

                System.out.println(book);
            }
            return listBook;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public List<Book> findByAuthor(String author)");
        }
    }

    @Override
    public Book create(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {


            PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getAutor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getYear());
            statement.setBigDecimal(4, book.getCost());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                return findById(id);
            } else {
                return new Book();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public boolean delete(long id)");
        }
    }

    @Override
    public Book update(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {

            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, book.getAutor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getYear());
            statement.setBigDecimal(4, book.getCost());
            statement.setLong(5, book.getId());

            statement.executeUpdate();

            return findById(book.getId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public boolean delete(long id)");
        }
    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWOORD)) {

            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);

            return (statement.executeUpdate() > 0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public boolean delete(long id)");
        }
    }

    @Override
    public long countAll() {
        try {
            ResultSet books = getResultSet(COUNT_ALL, 1);

            books.next();
            long count = books.getLong(1);

            return count;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public long countAll()");
        }
    }
}
