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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        LoginService ls = new LoginService();
        User unLogUser = ls.getUnLog();
        RegisterService myData = (RegisterService) sc.getAttribute("myDatabase");
        for(User user:myData.getUserList()){
            if(user.getEmail().equals(req.getParameter("email"))){
                req.setAttribute("loginServlet",user);
                req.getRequestDispatcher("main.jsp").forward(req,resp);
            }else{
                req.setAttribute("loginServlet",unLogUser);
                req.getRequestDispatcher("login.html").forward(req,resp);
            }
        }



    }

}
