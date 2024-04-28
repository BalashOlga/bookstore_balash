package com.belhard.bookstore_balash.controller;

import com.belhard.bookstore_balash.service.BookService;
import com.belhard.bookstore_balash.service.UserService;
import com.belhard.bookstore_balash.service.dto.BookDto;
import com.belhard.bookstore_balash.service.dto.UserDto;
import com.belhard.bookstore_balash.service.impl.BookServiceImpl;
import com.belhard.bookstore_balash.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/book")
public class BookController extends HttpServlet {

    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Long id = Long.parseLong(idStr);
        BookDto book = bookService.getById(id);
        PrintWriter out = resp.getWriter();

        out.println("<h1>Book</h1>");
        out.println("<p>" + book.getId() + "</p>" );
        out.println("<p>" + book.getAutor() + "</p>" );
        out.println("<p>" + book.getIsbn() + "</p>" );

    }
}

