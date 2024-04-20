package com.belhard.balash.bookstore.data.dao.impl;

import com.belhard.balash.bookstore.data.dao.BookDao;

import com.belhard.balash.bookstore.data.connection.DBConnection;
import com.belhard.balash.bookstore.data.entity.Book;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.getLong;
import static java.time.temporal.TemporalAdjusters.next;

public class BookDaoImpl implements BookDao {

    // все sout здесь для удобства поиска ошибок и чтобы в Main меньше выводов было, понятно, что они здесь лишние
// где лучше проверить чтобы параметр value в методе ниже были не null ?
// или лучше вообще запретить сюда ходить с null, как лучше?


    private ResultSet getResultSet(String sql, String value) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore_bh", "postgres", "123")) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString (1, value);

            return statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(" BookDaoImpl:private ResultSet getStatement(String sql, String value) ");
        }

    }

    private ResultSet getResultSet(String sql, long value) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore_bh", "postgres", "123")) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, value);

            return statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: ");
        }
    }

    @Override
    public Book findById(long id) {

        try {

            ResultSet books = getResultSet("SELECT id, author, isbn, year, cost FROM books WHERE id = ?", id);

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
            ResultSet books = getResultSet("SELECT id, author, isbn, year, cost FROM books WHERE isbn = ?", isbn);

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
            throw new RuntimeException("BookDaoImpl: public Book findByIsbn(String isbn)");
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            ResultSet books = getResultSet("SELECT id, author, isbn, year, cost FROM books WHERE 1 = ?", 1);

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
            ResultSet books = getResultSet("SELECT id, author, isbn, year, cost FROM books WHERE author = ?", author);

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
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore_bh", "postgres", "123")) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO books (author, isbn, year,  cost)  VALUES (?, ?,  ?, ?)", Statement.RETURN_GENERATED_KEYS);
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
                return new Book(); // говорило, что нет return, пришлось добавлять
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public boolean delete(long id)");
        }

    }

    @Override
    public Book update(Book book) {

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore_bh", "postgres", "123")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE books SET author = ?, isbn = ?, year = ?,  cost = ? WHERE id = ?; commit;");
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

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bookstore_bh", "postgres", "123")) {

        PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id = ?");
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
            ResultSet books = getResultSet("SELECT count(*) FROM books WHERE 1 = ?;", 1);

            books.next();
            long count = books.getLong(1);

            return count;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BookDaoImpl: public long countAll()");
        }

    }

    @Override
    public void printTableInfo() {

    }
}
