package com.codecool.web.servlet;

import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.exceptions.ServiceException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())) {
            RegisterService myData = new RegisterService();
            User user1 = new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("role"));
            myData.checkRegisterFields(req, resp, user1,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        doGet(req,resp);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registry.jsp").forward(req,resp);
    }

}
