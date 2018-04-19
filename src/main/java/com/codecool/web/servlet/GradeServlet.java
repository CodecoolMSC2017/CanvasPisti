package com.codecool.web.servlet;


import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/grades")
public class GradeServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Connection connection = getConnection(req.getServletContext())){
            PageDao pageDao = new DatabasePageDao(connection);
            User tempUser = (User) req.getSession().getAttribute("logged");
            Map<User,ArrayList<AssignmentPage>> myMap = pageDao.getSubmissionList();
            for (Map.Entry<User, ArrayList<AssignmentPage>> entry : myMap.entrySet()) {
                if (entry.getKey().getEmail().equals(tempUser.getEmail())){
                    ArrayList<AssignmentPage> pagez = myMap.get(entry.getKey());
                    req.setAttribute("username",entry.getKey().getEmail() );
                    req.setAttribute("userassigns", pagez);

                }
            }
            req.getRequestDispatcher("grades.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
