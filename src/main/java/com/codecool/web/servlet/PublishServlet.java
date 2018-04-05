package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/publish")
public class PublishServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        String[] arr= req.getParameterValues("name");
        UserService us = new UserService();
        us.publishTasks(db, arr);
        resp.sendRedirect("curriculum");
    }
}
