package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDtoWithoutPassword;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class UsersCommand implements Command {
    private final UserService service;

    @Override
    public String execute(HttpServletRequest req) {
        List<UserDtoWithoutPassword> users = service.getAll();
        req.setAttribute("users", users);
        return "jsp/user/users.jsp";
    }
}

