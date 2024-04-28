package com.belhard.bookstore_balash.controller;

import com.belhard.bookstore_balash.service.UserService;
import com.belhard.bookstore_balash.service.dto.UserDto;
import com.belhard.bookstore_balash.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Long id = Long.parseLong(idStr);
        UserDto user = userService.getById(id);
        PrintWriter out = resp.getWriter();

        out.println("<h1>User</h1>");
        out.println("<p>" + user.getLogin() + "</p>" );
        out.println("<p>" + user.getEmail() + "</p>" );
    }
}

