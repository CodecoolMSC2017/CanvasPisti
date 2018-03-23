package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        for (int i = 0; i <db.getPageList().size() ; i++) {
            if(req.getParameter("title").equals(db.getPageList().get(i).getTitle())) {
                req.setAttribute("textcontent", db.getPageList().get(i));
                User tempUser = (User) req.getSession().getAttribute("logged");
                String userRole = tempUser.getRole();
                req.setAttribute("userrole", userRole);
                req.getRequestDispatcher("question.jsp").forward(req, resp);
            }
        }

    }
}
