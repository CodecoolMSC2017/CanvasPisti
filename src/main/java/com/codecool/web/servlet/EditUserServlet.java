package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditUserServlet extends AbstractServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            User tempUser = (User)req.getSession().getAttribute("logged");
            userDao.changeRole(req.getParameter("role"), tempUser.getEmail());
            userDao.changeName(req.getParameter("name"), tempUser.getEmail());
            req.getRequestDispatcher("main.jsp").forward(req,resp);

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

    }



}
