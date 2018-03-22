package com.codecool.web.servlet;

import com.codecool.web.model.Page;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.CurriculumService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/curriculum")
public class CurriculumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tempUser = (User) req.getSession().getAttribute("logged");
        String userRole = tempUser.getRole();
        req.setAttribute("allpages", db.getPageList());
        req.setAttribute("userrole", userRole);
        req.getRequestDispatcher("curriculum.jsp").forward(req, resp);
    }
}
