package com.codecool.web.servlet;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/check")
public class CheckAttendanceServlet extends HttpServlet {


    UserService us = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        us.checkAttendance(req, resp, sc );
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        for (Map.Entry<String, Map<User, String>> entry : db.getAttend().entrySet()) {
            if (req.getSession().getAttribute("datepicker2").equals(entry.getKey())) {
                req.setAttribute("dateandname", entry.getValue());
                req.getRequestDispatcher("attendancelist.jsp").forward(req, resp);
            }
        }

    }
}