package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import com.belhard.bookstore.service.dto.UserDtoLogin;
import com.belhard.bookstore.service.dto.UserDtoWithoutPassword;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEditFormCommand implements Command {
    private final UserService service;

    @Override
    public String execute(HttpServletRequest req) {
        String idStr = req.getParameter("id");
        Long id = Long.parseLong(idStr);
        UserDtoWithoutPassword user = service.getById(id);
        req.setAttribute("user", user);
        return "jsp/user/userEdit.jsp";
    }

}
