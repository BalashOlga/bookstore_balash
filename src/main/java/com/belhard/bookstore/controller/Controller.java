package com.belhard.bookstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/controller")
@Log4j2
public class Controller extends HttpServlet {
    private CommandFactory commandFactory = CommandFactory.INSTANCE;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String command = req.getParameter("command");
            Command commandInstance = commandFactory.getCommand(command);
            PrintWriter out = resp.getWriter();
            out.println(commandInstance.execute(req));
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            log.debug(e.getMessage());
            PrintWriter out = resp.getWriter();
            out.println("<h1>Error</h1>");
            out.println("<p> Invalid request </p>");

        } catch (NotFoundException e) {
            resp.setStatus(404);
            log.debug(e.getMessage());
            PrintWriter out = resp.getWriter();
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");

        } catch (Exception e) {
            resp.setStatus(500);
            log.debug(e.getMessage());
            PrintWriter out = resp.getWriter();
            out.println("<h1>Error</h1>");
            out.println("<p> The disaster on the swing </p>");
        }
    }
}
