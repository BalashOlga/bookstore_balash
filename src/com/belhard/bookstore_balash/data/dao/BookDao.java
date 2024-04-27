package com.belhard.bookstore_balash.data.dao;

import com.belhard.bookstore_balash.data.entity.Book;

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
}
