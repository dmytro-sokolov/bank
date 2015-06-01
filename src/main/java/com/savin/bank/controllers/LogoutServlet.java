package com.savin.bank.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Denis on 6/1/2015.
 */
@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        if (session!=null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}
