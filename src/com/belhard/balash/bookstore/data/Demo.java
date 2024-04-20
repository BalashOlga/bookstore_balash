package com.belhard.balash.bookstore;

import com.belhard.balash.bookstore.data.dao.BookDao;
import com.belhard.balash.bookstore.data.dao.impl.BookDaoImpl;
import com.belhard.balash.bookstore.data.entity.Book;

import java.sql.*;

public class Demo {
    public static void main(String[] args) {

        BookDao bookDao = new BookDaoImpl();

    //       как правильно делать в случае если книги по условию не найдены? null не правильно возвращать?

        System.out.println("findById");
        if (bookDao.findById(35).getId() == null){
            System.out.println("книга не найдена");
        }


        System.out.println("findALl");
        if (bookDao.findAll().isEmpty()){
            System.out.println("книги не найдена");
        }

        System.out.println("findByAuthor");
        if (bookDao.findByAuthor("a17").isEmpty()){
            System.out.println("книги указанного автора не найдены");
        }

        System.out.println("findByIsbn");
        if (bookDao.findByIsbn("978-0-124456-47-2").getIsbn() == null){
            System.out.println("книги с  таким Isbn не найдены");
        }

        System.out.println("delete");
        if (bookDao.delete(50)){
            System.out.println("книга удалена");
        } else {
            System.out.println("книга c таким id не была удалена, так как не была найдена");
        }

        System.out.println("countAll");
        System.out.println(bookDao.countAll());

        System.out.println("update");
        Book book1 = new Book(36L,"dhfsgh", "978-0-444556-57-2",2023,null);
        bookDao.update(book1);

        System.out.println("create");
        Book book2 = new Book(null,"dhfsgh", "978-0-444457-57-2",2023,null);
        bookDao.create(book2);

    }

}
