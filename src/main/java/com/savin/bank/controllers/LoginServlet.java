package com.savin.bank.controllers;

import com.savin.bank.dao.DaoFactory;
import com.savin.bank.dao.GenericDao;
import com.savin.bank.dao.MySqlDaoFactory;
import com.savin.bank.dao.PersistException;
import com.savin.bank.src.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "LoginController", urlPatterns={"/profile"})
public class LoginServlet extends javax.servlet.http.HttpServlet {
    private Map<String, Client> clientMap;
    private DaoFactory daoFactory;
    private Connection connection;
    private GenericDao clientDao;

    @Override
    public void init() throws ServletException {
        daoFactory = new MySqlDaoFactory();
        connection = daoFactory.getConnection();
        clientDao = daoFactory.getClientDao(connection);
        try {
            clientMap = clientDao.getAll();
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Client client = clientMap.get(login);
        if (client!=null && (client.getPassword().equals(password))) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setAttribute("client",client);
            httpSession.setMaxInactiveInterval(5*60);
            Cookie userName = new Cookie("user", login);
            userName.setMaxAge(5*60);
            response.addCookie(userName);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.setContentType("text/html");
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter writer = response.getWriter();
            writer.write("<font color=red>sorry, incorrect pass</font>");
            requestDispatcher.include(request, response);
        }
    }
}
