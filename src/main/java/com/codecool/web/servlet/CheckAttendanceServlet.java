package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/check")
public class CheckAttendanceServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       UserService userService = new UserService();
       String[] presentEmails = userService.checkAttendance(req);
        try(Connection connection = getConnection(req.getServletContext())){
           UserDao userDao = new DatabaseUserDao(connection);
            for (int i = 0; i < presentEmails.length; i++) {

                userDao.checkAttendance(req.getParameter("datepicker"),presentEmails[i]);
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }

        req.getRequestDispatcher("curriculumAtt.jsp").forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tmpUser = (User) req.getSession().getAttribute("logged");
        String role = tmpUser.getRole();
        req.setAttribute("role",role);
        for (Map.Entry<String, Map<User, String>> entry : db.getAttend().entrySet()) {
            if (req.getSession().getAttribute("datepicker2").equals(entry.getKey())) {
                req.setAttribute("dateandname", entry.getValue());
                req.getRequestDispatcher("attendancelist.jsp").forward(req, resp);
            }
        }

    }
}