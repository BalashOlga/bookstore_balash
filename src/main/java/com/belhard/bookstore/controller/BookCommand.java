package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.dto.UserDtoWithoutPassword;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class BookCommand implements  Command {

    private final BookService service;

    @Override
    public String execute(HttpServletRequest req) {
        String idStr = req.getParameter("id");
        Long id = Long.parseLong(idStr);
        BookDto book = service.getById(id);
        req.setAttribute("book", book);
        return "jsp/book/book.jsp";
    }
}

