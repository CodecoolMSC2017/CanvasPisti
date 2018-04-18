package com.codecool.web.servlet;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/question")
public class QuestionServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Singletondb db = Singletondb.getInstance();
        try (Connection connection = getConnection(req.getServletContext())) {
            PageDao pageDao =new DatabasePageDao(connection);
            User tempUser = (User) req.getSession().getAttribute("logged");
            String userRole = tempUser.getRole();
            int number = 0;
            UserService us = new UserService();
            us.handleQuestion(req, db, tempUser, userRole, number);
            req.getRequestDispatcher("question.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
