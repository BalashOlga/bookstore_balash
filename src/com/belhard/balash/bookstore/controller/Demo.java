package com.belhard.balash.bookstore.controller;

import com.belhard.balash.bookstore.data.entity.Book;
import com.belhard.balash.bookstore.service.BookService;
import com.belhard.balash.bookstore.service.dto.BookDto;
import com.belhard.balash.bookstore.service.impl.BookServiceImpl;

public class Demo {
    public static void main(String[] args) {

//        BookDao bookDao = new BookDaoImpl();
        BookService bookService = new BookServiceImpl();

//       как правильно делать в случае если книги по условию не найдены? null же не правильно возвращать? нужен объект?

        System.out.println("findById");
        if (bookService.getById(35).getId() == null){
            System.out.println("книга не найдена");
        }

        System.out.println("findALl");
        if (bookService.getAll().isEmpty()){
            System.out.println("книги не найдена");
        }

        System.out.println("findByAuthor");
        if (bookService.getByAuthor("a17").isEmpty()){
            System.out.println("книги указанного автора не найдены");
        }

        System.out.println("findByIsbn");
        if (bookService.getByIsbn("978-0-124456-47-2").getIsbn() == null){
            System.out.println("книги с  таким Isbn не найдены");
        }

        System.out.println("delete");
        if (bookService.delete(50)){
            System.out.println("книга удалена");
        } else {
            System.out.println("книга c таким id не была удалена, так как не была найдена");
        }

        System.out.println("countAll");
        System.out.println(bookService.getcountAll());

        System.out.println("update");
        BookDto book1 = new BookDto(36L,"dhfsgh", "978-0-444556-57-2",2023,null);
        bookService.update(book1);

        System.out.println("create");
        BookDto book2 = new BookDto(null,"dhfsgh", "978-0-44457-57-2",2023,null);
        bookService.create(book2);

    }
}
