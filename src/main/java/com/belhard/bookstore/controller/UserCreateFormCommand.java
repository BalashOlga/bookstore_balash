package com.belhard.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

public class UserCreateFormCommand implements Command{
    @Override
    public String execute(HttpServletRequest req) {

        return "jsp/user/userCreateForm.jsp";
    }
}
