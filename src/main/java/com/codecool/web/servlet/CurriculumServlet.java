package com.codecool.web.servlet;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabasePageDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Page;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.CurriculumService;
import com.codecool.web.service.CurriculumServiceInt;
import com.codecool.web.service.simple.SimpleCurriculumService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/curriculum")
public class CurriculumServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PageDao pageDao = new DatabasePageDao(connection);
            UserDao userDao = new DatabaseUserDao(connection);
            CurriculumServiceInt curriculumServiceInt = new SimpleCurriculumService(pageDao);
           // Singletondb db = Singletondb.getInstance();
            User tempUser = (User) req.getSession().getAttribute("logged");
            User currentUser = userDao.findByEmail(tempUser.getEmail());
            String userRole = tempUser.getRole();
            //System.out.println(userRole);
            ArrayList<Page>allpages = new ArrayList<>();
            allpages.addAll(pageDao.listAllText());
            allpages.addAll(pageDao.listAllAss());
          //  req.getSession().setAttribute("alltxtasspage",allpages);
            req.setAttribute("allpages", allpages);
            req.setAttribute("userrole", currentUser.getRole());
            req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
