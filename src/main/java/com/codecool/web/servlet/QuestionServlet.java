package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tempUser = (User) req.getSession().getAttribute("logged");
        String userRole = tempUser.getRole();
        int number = 0;
        UserService us = new UserService();
        us.handleQuestion(req, db, tempUser, userRole, number);
        req.getRequestDispatcher("question.jsp").forward(req, resp);
    }
}
