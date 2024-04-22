package com.belhard.balash.bookstore.controller;

import com.belhard.balash.bookstore.service.BookService;
import com.belhard.balash.bookstore.service.dto.BookDto;
import com.belhard.balash.bookstore.service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner stroka = new Scanner(System.in);
        int action;

        System.out.println("1 - найти книгу по id");
        System.out.println("2 - найти все книги");
        System.out.println("3 - найти книги по автору");
        System.out.println("4 - найти по книгу по isbn");
        System.out.println("5 - удалить книгу");
        System.out.println("6 - посчитать количество книг");
        System.out.println("7 - отредактировать книгу");
        System.out.println("8 - создать книгу");
        System.out.println("9 - посчитать стоимость книг указанного автора");
        System.out.println("10 - выйти из программы");

        do {
            System.out.println();
            System.out.print("Введите номер действия: ");
            action = stroka.nextInt();
            stroka.nextLine();

            BookService bookService = new BookServiceImpl();
            bookService.createTable();

//       как правильно делать в случае если книги по условию не найдены? null же не правильно возвращать? нужен объект?

            switch (action) {
                case 1:  {
                    System.out.println("findById");
                    if (bookService.getById(39).getId() == null) {
                        System.out.println("книга не найдена");
                    }
                    break;
                }

                case 2: {
                    System.out.println("findALl");
                    if (bookService.getAll().isEmpty()) {
                        System.out.println("книги не найдены");
                    }
                    break;
                }

                case 3: {
                    System.out.println("findByAuthor");
                    if (bookService.getByAuthor("a17").isEmpty()) {
                        System.out.println("книги указанного автора не найдены");
                    }
                    break;
                }

                case 4: {
                    System.out.println("findByIsbn");
                    if (bookService.getByIsbn("978-0-124456-47-2").getIsbn() == null) {
                        System.out.println("книги с  таким Isbn не найдены");
                    }
                    break;
                }

                case 5: {
                    System.out.println("delete");
                    if (bookService.delete(50)) {
                        System.out.println("книга удалена");
                    } else {
                        System.out.println("книга c таким id не была удалена, так как не была найдена");
                    }
                    break;
                }

                case 6: {
                    System.out.println("countAll");
                    System.out.println(bookService.getcountAll());
                    break;
                }

                case 7: {
                    System.out.println("update");
                    BookDto book1 = new BookDto(36L, "dhfsgh", "978-0-444556-57-2", 2023, null);
                    if(bookService.update(book1).getId() == null){
                        System.out.println("книга не была обновлена");
                    }
                    break;
                }

                case 8: {
                    System.out.println("create");
                    BookDto book2 = new BookDto(null, "a17", "777-0-16957-87-2", 2023, null);
                    bookService.create(book2);
                    break;
                }

                case 9: {
                    System.out.println("getCostByAuthor");
                    BigDecimal costByAuthor = bookService.getCostByAuthor("a17");
                    if (costByAuthor == null) {
                        System.out.println("книги указанного автора не найдены");
                    } else {
                        System.out.println("costByAuthor = " + costByAuthor);
                    }
                    break;
                }

                case 10: {
                    System.out.print("Завершаем программу");
                    break;
                }

                default: {
                    System.out.print("Нет такой команды");
                }
            }
        } while (action != 10);
    }
}
