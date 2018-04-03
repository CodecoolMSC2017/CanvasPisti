package com.codecool.web.servlet;


import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.User;
import com.codecool.web.service.CurriculumService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/scoring")
public class ScoringServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Singletondb db = Singletondb.getInstance();
        CurriculumService cs = new CurriculumService();
        int actualScore = Integer.parseInt(req.getParameter("actualScore"));
        User tmpUser=(User)req.getSession().getAttribute("logged");
        AssignmentPage aPage = (AssignmentPage)req.getAttribute("aPage");
        ArrayList<AssignmentPage> pages= db.getSubmissions().get(tmpUser);
        for (AssignmentPage page:pages) {
            if(page.getTitle().equals(aPage.getTitle())){
                page.setActualScore(actualScore);
            }
        }
        resp.sendRedirect("solutionGrade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();
        User tmpUser=(User)req.getAttribute("student");
        AssignmentPage aPage = (AssignmentPage)req.getAttribute("aPage");
        req.getRequestDispatcher("scoring.jsp").forward(req, resp);
    }
}

/*
package com.codecool.web.servlet;

        import com.codecool.web.model.AssignmentPage;
        import com.codecool.web.model.Singletondb;
        import com.codecool.web.model.User;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.ArrayList;

@WebServlet("/scoring")
public class ScoringServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Singletondb db = Singletondb.getInstance();

        int actualScore = (int)req.getAttribute("actualScore");
        User tmpUser=(User)req.getAttribute("student");
        AssignmentPage aPage = (AssignmentPage)req.getAttribute("aPage");
        ArrayList<AssignmentPage> pages= db.getSubmissions().get(tmpUser);
        for (AssignmentPage page:pages) {
            if(page.getTitle().equals(aPage.getTitle())){
                page.setActualScore(actualScore);
            }
        }
        resp.sendRedirect("solutionGrade");
>>>>>>> Stashed changes
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}*/
