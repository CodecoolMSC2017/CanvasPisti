package com.codecool.web.servlet;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Page;
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
import java.util.ArrayList;


@WebServlet("/question")
public class QuestionServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            PageDao pageDao = new DatabasePageDao(connection);
            User tempUser = (User) req.getSession().getAttribute("logged");
            User currentUser = userDao.findByEmail(tempUser.getEmail());
            String userRole = currentUser.getRole();
            req.setAttribute("userrole", userRole);
            ArrayList<Page> allList = new ArrayList<Page>();
            allList.addAll(pageDao.listAllText());
            allList.addAll(pageDao.listAllAss());
            for (int i = 0; i < allList.size(); i++) {
                if (req.getParameter("title").equals(allList.get(i).getTitle())) {
                    req.setAttribute("textcontent", allList.get(i));
                    req.getSession().setAttribute("assign",allList.get(i));
                    req.getRequestDispatcher("question.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
