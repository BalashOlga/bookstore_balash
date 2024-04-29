package com.belhard.bookstore_balash.controller;

import com.belhard.bookstore_balash.service.UserService;
import com.belhard.bookstore_balash.service.dto.BookDto;
import com.belhard.bookstore_balash.service.dto.UserDto;
import com.belhard.bookstore_balash.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> users = userService.getAll();
        PrintWriter out = resp.getWriter();

        out.println("<h1>Users</h1>");
        for (UserDto user : users) {
            out.println("<p>" + user.getEmail() +
                        "<a href = http://localhost:8080/bookstore_balash-1.0/user?id="+user.getId() +"> User </a> </p>");
        }
    }
}

