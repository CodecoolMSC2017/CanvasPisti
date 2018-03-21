package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService us = new UserService();
        us.changeUserAttr(req, resp);
    }



}
