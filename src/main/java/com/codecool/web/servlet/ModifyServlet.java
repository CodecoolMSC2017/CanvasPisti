package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/modify")
public class ModifyServlet extends AbstractServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            List<String> emails = Arrays.asList(req.getParameterValues("email"));
            for(String email : emails){
                userDao.deleteFromAttendance(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("check");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            String date = req.getParameter("date");
            HttpSession session = req.getSession();
            User tmpUser = (User) session.getAttribute("logged");
            User currentUser = userDao.findByEmail(tmpUser.getEmail());
            req.setAttribute("role", currentUser.getRole());
            req.setAttribute("users", userDao.listAttendance(date));
            for(User user : userDao.listAttendance(date)) {
                System.out.println(user.getName());
            }
            req.getRequestDispatcher("attendancelist.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
