package com.belhard.bookstore_balash.controller;

import com.belhard.bookstore_balash.service.BookService;
import com.belhard.bookstore_balash.service.dto.BookDto;
import com.belhard.bookstore_balash.service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner stroka = new Scanner(System.in);
        int action;

        System.out.println("1 - find book by id");
        System.out.println("2 - find all books");
        System.out.println("3 - find book by author");
        System.out.println("4 - find book by isbn");
        System.out.println("5 - delete book");
        System.out.println("6 - count of  books");
        System.out.println("7 - update book");
        System.out.println("8 - create book");
        System.out.println("9 - cost of books");
        System.out.println("10 - exit");

        do {
            System.out.println();
            System.out.print("Make o choose: ");
            action = stroka.nextInt();
            stroka.nextLine();

            BookService bookService = new BookServiceImpl();

            switch (action) {
                case 1:  {
                    System.out.println("findById");
                    if (bookService.getById(39).getId() == null) {
                        System.out.println("The book was not found");
                    }
                    break;
                }

                case 2: {
                    System.out.println("findALl");
                    if (bookService.getAll().isEmpty()) {
                        System.out.println("The book was not found");
                    }
                    break;
                }

                case 3: {
                    System.out.println("findByAuthor");
                    if (bookService.getByAuthor("a17").isEmpty()) {
                        System.out.println("The book was not found");
                    }
                    break;
                }

                case 4: {
                    System.out.println("findByIsbn");
                    if (bookService.getByIsbn("978-0-124456-47-2").getIsbn() == null) {
                        System.out.println("The book was not found");
                    }
                    break;
                }

                case 5: {
                    System.out.println("delete");
                    if (bookService.delete(50)) {
                        System.out.println("The book has been deleted");
                    } else {
                        System.out.println("The book with this id was not deleted because it was not found");
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
                    BookDto book1 = new BookDto(7L, "daafsgh", "978-0-444556-57-2", 2023, null);
                    if(bookService.update(book1).getId() == null){
                        System.out.println("The book has not been updated");
                    }
                    break;
                }

                case 8: {
                    System.out.println("create");
                    BookDto book2 = new BookDto(null, "a28", "707-0-16057-87-2", 2023, BigDecimal.valueOf(75));
                    bookService.create(book2);
                    break;
                }

                case 9: {
                    System.out.println("getCostByAuthor");
                    BigDecimal costByAuthor = bookService.getCostByAuthor("a28");
                    if (costByAuthor == null) {
                        System.out.println("The book was not found");
                    } else {
                        System.out.println("costByAuthor = " + costByAuthor);
                    }
                    break;
                }

                case 10: {
                    System.out.print("Completing the program");
                    break;
                }

                default: {
                    System.out.print("There is no such command");
                }
            }
        } while (action != 10);
    }
}
