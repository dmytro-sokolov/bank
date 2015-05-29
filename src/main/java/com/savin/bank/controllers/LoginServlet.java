package com.savin.bank.controllers;

import com.savin.bank.dao.GenericDao;
import com.savin.bank.dao.DaoFactory;
import com.savin.bank.dao.PersistException;
import com.savin.bank.dao.PostgreSqlDaoFactory;
import com.savin.bank.java.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by denis on 5/24/15.
 */
@WebServlet(name = "MocController", urlPatterns={"/login"})
public class LoginServlet extends javax.servlet.http.HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoFactory dao = new PostgreSqlDaoFactory();
        Connection connection = dao.getConnection();
        GenericDao clientDao = dao.getClientDao(connection);
        List<Client> list = null;
        try {
            list = clientDao.getAll();
        } catch (PersistException e) {
            e.printStackTrace();
        }
        for (Client cl : list) {
            response.getWriter().write(cl.getName() + "\n");
            response.getWriter().write(cl.getSurname() + "\n");
            response.getWriter().write(cl.getSecondName() + "\n");
            response.getWriter().write(cl.getDateOfBirth().toString() + "\n");
            response.getWriter().write(cl.getDateOfRegistration().toString()+"\n\n");
        }
    }
}
