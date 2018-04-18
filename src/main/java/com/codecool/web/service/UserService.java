package com.codecool.web.service;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Page;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public final class UserService {


    public void changeUserAttr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User tempUser = (User) req.getSession().getAttribute("logged");
        String userName = tempUser.getName();
        String userRole = tempUser.getRole();
        if (req.getParameter("role") == null) {
            if (req.getParameter("name").equals(userName)) {
                req.getRequestDispatcher("main.jsp").forward(req, resp);

            } else if (!req.getParameter("name").equals(userName)) {
                tempUser.setName(req.getParameter("name"));
                req.setAttribute("name", tempUser);
                req.getRequestDispatcher("main.jsp").forward(req, resp);

            }
        } else if (req.getParameter("role").equalsIgnoreCase(userRole)) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } else if (!req.getParameter("role").equalsIgnoreCase(userRole) && req.getParameter("name").equals(userName)) {

            tempUser.setRole(req.getParameter("role"));
            req.setAttribute("role", tempUser);
            req.getRequestDispatcher("main.jsp").forward(req, resp);

        } else if (!req.getParameter("role").equals(userRole) && !req.getParameter("name").equals(userName)) {
            tempUser.setRole(req.getParameter("role"));
            req.setAttribute("role", tempUser);
            tempUser.setName(req.getParameter("name"));
            req.setAttribute("name", tempUser);
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }
    }

    public void modifyAttendance(HttpServletRequest req, Singletondb db, String[] arr) {
        if (arr != null) {
            for (Map.Entry<String, Map<User, String>> entry : db.getAttend().entrySet()) {
                if (entry.getKey().equals(req.getSession().getAttribute("datepicker2"))) {
                    for (Map.Entry<User, String> entry2 : db.getAttend().get(entry.getKey()).entrySet()) {
                        if (Arrays.asList(arr).contains(entry2.getKey().getEmail())) {
                            if (entry2.getValue().equals("was here")) {
                                entry2.setValue("wasnt here");
                            } else if (entry2.getValue().equals("wasnt here")) {
                                entry2.setValue("was here");
                            }
                        }
                    }
                }
            }

        }
    }

    public void publishTasks(Singletondb db, String[] arr) {
        if(arr != null) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < db.getPageList().size(); j++) {

                    if (arr[i].equals(db.getPageList().get(j).getTitle())) {
                        if (!db.getPageList().get(j).isPublished()) {
                            db.getPageList().get(j).setPublished(true);
                        } else {
                            db.getPageList().get(j).setPublished(false);
                        }
                    }
                }
            }
        }
    }

    public String[] checkAttendance(HttpServletRequest req) {
        String[] presentStudents = req.getParameterValues("Was Here");
        return presentStudents;
    }

    public void handleQuestion(HttpServletRequest req, ArrayList<Page> pageList , User tempUser, String userRole, int number) {
        for (int i = 0; i <pageList.size() ; i++) {
            if(req.getParameter("title").equals(pageList.get(i).getTitle())) {
                req.setAttribute("textcontent", pageList.get(i));
                req.getSession().setAttribute("assign",pageList.get(i));
                ArrayList<AssignmentPage> pagez = db.getSubmissions().get(tempUser);
                if(pagez != null) {
                    for (AssignmentPage page : pagez) {
                        if (page.getTitle().equals(db.getPageList().get(i).getTitle())) {
                            number++;
                        }
                    }
                }
                req.setAttribute("num",number);
                req.setAttribute("userrole", userRole);

            }
        }
    }

}

