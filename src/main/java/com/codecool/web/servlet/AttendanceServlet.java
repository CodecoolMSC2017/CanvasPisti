package com.codecool.web.servlet;

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
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();

        List<User> allUsers = ((RegisterService) sc.getAttribute("myDatabase")).getUserList();
        req.setAttribute("allusers", allUsers);
        req.setAttribute("loginServlet", LoginService.getCurrentUser());
        if(LoginService.getCurrentUser().getRole().equalsIgnoreCase("mentor")) {
            req.getRequestDispatcher("attendance.jsp").forward(req, resp);

        }else{
            req.getRequestDispatcher("registeredList.jsp").forward(req, resp);
        }




    }
}
