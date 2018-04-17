package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/userprofile")
public class CurrentUserServlet extends AbstractServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            User tempUser = (User)req.getSession().getAttribute("logged");
            try {
                User currentUser = userDao.findByEmail(tempUser.getEmail());
                req.setAttribute("username", currentUser.getName());
                req.setAttribute("useremail", currentUser.getEmail());
                req.setAttribute("userrole", currentUser.getRole());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            req.getRequestDispatcher("userprofile.jsp").forward(req, resp);

        }
            catch(SQLException e) {
                e.printStackTrace();
            }
    }
}
