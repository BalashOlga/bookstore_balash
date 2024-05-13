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
    private CommandFactory commandFactory;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String command = req.getParameter("command");
            Command commandInstance = commandFactory.getCommand(command);
            String page = commandInstance.execute(req);
            req.getRequestDispatcher(page).forward(req,resp);
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            log.error(e.getMessage());
            PrintWriter out = resp.getWriter();
            out.println("<h1>Error</h1>");
            out.println("<p> Invalid request </p>");

        } catch (NotFoundException e) {
            resp.setStatus(404);
            log.error(e.getMessage());
            PrintWriter out = resp.getWriter();
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + " </p>");

        } catch (Exception e) {
            resp.setStatus(500);
            log.error(e.getMessage());
            PrintWriter out = resp.getWriter();
            out.println("<h1>Error</h1>");
            out.println("<p> The disaster on the swing </p>");
        }
    }

    @Override
    public void destroy() {
        if (commandFactory != null) {
            commandFactory.shutdown();
        }
    }

    @Override
    public void init() throws ServletException {
        commandFactory = CommandFactory.getInstance();
        log.info("Сontroller is running");
    }
}
