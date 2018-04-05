package com.codecool.web.servlet;

import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;

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

    Map<String, Map<User, String>> studentAttend;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        Map<User, String> attMap = new HashMap<>();
        Singletondb db = Singletondb.getInstance();
        studentAttend = db.getAttend();
        String time = req.getParameter("datepicker");
        req.getSession().setAttribute("datepicker2", time);
        List<User> attList = ((RegisterService) sc.getAttribute("myDatabase")).getUserList();
        String[] presentStudents = req.getParameterValues("Was Here");
        if(presentStudents!=null) {
            for (int i = 0; i < attList.size(); i++) {
                for (int j = 0; j < presentStudents.length; j++) {
                    if (attList.get(i).getName().equals(presentStudents[j])) {
                        attMap.put(attList.get(i), "was here");
                        break;
                    } else {
                        attMap.put(attList.get(i), "wasnt here");
                    }
                }

            }
            if (presentStudents.length != 0) {
                studentAttend.put(time, attMap);
                req.setAttribute("att", studentAttend);
            }

        }else {
            for (int i = 0; i < attList.size(); i++) {
                attMap.put(attList.get(i), "wasnt here");

            }if (presentStudents == null) {
                studentAttend.put(time, attMap);
                req.setAttribute("att", studentAttend);
            }
        }
        req.getSession().setAttribute("attMapIstvan",studentAttend);
        req.getRequestDispatcher("curriculumAtt.jsp").forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tmpUser = (User) req.getSession().getAttribute("logged");
        String role = tmpUser.getRole();
        req.setAttribute("role",role);
        for (Map.Entry<String, Map<User, String>> entry : db.getAttend().entrySet()) {
            if (req.getSession().getAttribute("datepicker2").equals(entry.getKey())) {
                req.setAttribute("dateandname", entry.getValue());
                req.getRequestDispatcher("attendancelist.jsp").forward(req, resp);
            }
        }

    }
}