package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDtoWithoutPassword;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class UserCommand implements Command {
    private final UserService service;

    @Override
    public String execute(HttpServletRequest req) {
                String idStr= req.getParameter("id");
        Long id = Long.parseLong(idStr);
        UserDtoWithoutPassword user = service.getById(id);
        return "<h1>User</h1> <p>" + user.getLogin() + "</p><p>" + user.getEmail() + "</p>";

    }
}

