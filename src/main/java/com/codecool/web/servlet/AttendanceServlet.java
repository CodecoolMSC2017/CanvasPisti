package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.RegisterService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())){
            User tempUser = (User) req.getSession().getAttribute("logged");
            UserDao userDao = new DatabaseUserDao(connection);
            req.setAttribute("allusers", userDao.findAll());
            User currentUser = userDao.findByEmail(tempUser.getEmail());
            if (currentUser.getRole().equalsIgnoreCase("mentor")) {
                req.getRequestDispatcher("attendance.jsp").forward(req, resp);

            } else {
                req.getRequestDispatcher("registeredList.jsp").forward(req, resp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }




    }
}
