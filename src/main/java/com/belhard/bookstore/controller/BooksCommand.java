package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.PrintWriter;
import java.util.List;


@RequiredArgsConstructor

public class BooksCommand implements Command {

    private final BookService service;

    @Override
    public String execute(HttpServletRequest req) {
        List<BookDto> books = service.getAll();

        return ("<h1>Users</h1>");
    }
}

