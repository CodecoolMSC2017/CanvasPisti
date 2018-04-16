package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.exceptions.ServiceException;
import com.codecool.web.service.simple.SimpleRegisterService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class RegisterService {

    public String getReg(){return "You successfully registered";}

    public String getAllReg(){return "That person already registered";}

    public String getMiss(){return "You need a correct email address form";}

    public String getEmptyReg(){return"You need to choose student or teacher!";}

    private List<User> userList = new ArrayList<>();


    public List<User> getUserList() {
        return userList;
    }

    public void checkRegisterFields(HttpServletRequest req, HttpServletResponse resp,User user1,Connection connection) throws ServletException, IOException, SQLException, ServiceException {
        UserDao userDao = new DatabaseUserDao(connection);
        RegisterServiceInt registerServiceInt =new SimpleRegisterService(userDao) ;
        userList = userDao.findAll();
        if(user1.getRole()==null){
            req.setAttribute("register", this.getEmptyReg());
            req.getRequestDispatcher("registry.jsp").forward(req, resp);
        }
        else if(!user1.getEmail().contains(".")&& !user1.getEmail().contains("@")) {
            req.setAttribute("register", this.getMiss());
            req.getRequestDispatcher("registry.jsp").forward(req, resp);
        }else{
            for (User user : this.getUserList()) {
                if (user.getEmail().equalsIgnoreCase(user1.getEmail())) {
                    req.setAttribute("register", this.getAllReg());
                    req.getRequestDispatcher("registry.jsp").forward(req, resp);
                }
            }
            req.setAttribute("register", this.getReg());
            user1 = registerServiceInt.addUser(user1);
            req.getRequestDispatcher("registry.jsp").forward(req, resp);
        }
    }

}
