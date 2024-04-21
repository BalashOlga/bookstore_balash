package com.belhard.balash.bookstore.data.dao;

import com.belhard.balash.bookstore.data.entity.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public interface BookDao {

    Book findById(long id);

    Book findByIsbn(String isbn);

    List<Book> findAll();

    List<Book> findByAuthor(String author);

    Book create(Book book);

    Book update(Book book);

    boolean delete(long id);

    long countAll();

//    void printTableInfo();


}
