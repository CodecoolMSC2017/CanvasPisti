package com.codecool.web.service;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public final class LoginService {

    public String getUnLog() {
        return "Please give correct inputs!";
    }


    public void checkingLogin(HttpServletRequest req, HttpServletResponse resp, RegisterService myData) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tempUser;
        HttpSession session = req.getSession();
        if (myData.getUserList().size() == 0) {
            req.setAttribute("loginServlet", getUnLog());
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            for (User user : myData.getUserList()) {
                if (user.getEmail().equals(req.getParameter("email"))) {
                    tempUser = user;
                    db.getUserMap().put(session.getId(),tempUser);
                    session.setAttribute("logged",tempUser);
                    req.getRequestDispatcher("main.jsp").forward(req, resp);
                }
            }
            req.setAttribute("loginServlet", getUnLog());
            req.getRequestDispatcher("login.jsp").forward(req, resp);

        }
    }
}
