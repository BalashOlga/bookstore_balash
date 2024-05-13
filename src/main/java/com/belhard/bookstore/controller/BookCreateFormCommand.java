package com.belhard.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

public class BookCreateFormCommand implements Command{
    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/book/bookCreateForm.jsp";
    }
}

