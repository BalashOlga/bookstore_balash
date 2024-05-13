package com.belhard.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

public class HomeCommand implements Command{
    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/home.jsp";
    }
}
