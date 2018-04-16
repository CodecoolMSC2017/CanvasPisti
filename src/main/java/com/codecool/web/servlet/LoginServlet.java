package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.LoginserviceInt;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.exceptions.ServiceException;
import com.codecool.web.service.simple.SimpleLoginService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            LoginserviceInt loginService = new SimpleLoginService(userDao);

            String email = req.getParameter("email");

            User user = loginService.loginUser(email);

            req.getSession().setAttribute("logged", user);
            resp.sendRedirect("main.jsp");
        } catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
