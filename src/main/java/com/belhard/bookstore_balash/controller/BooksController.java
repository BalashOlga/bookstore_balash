package com.belhard.bookstore_balash.controller;

import com.belhard.bookstore_balash.service.BookService;
import com.belhard.bookstore_balash.service.dto.BookDto;
import com.belhard.bookstore_balash.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/books")
public class BooksController extends HttpServlet {

    private final BookService bookService = new BookServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDto> books = bookService.getAll();
        PrintWriter out = resp.getWriter();

        out.println("<h1>Books</h1>");

        for (BookDto book : books) {
            out.println("<p>" + book.getIsbn() + "</p>");
        }
    }
}

