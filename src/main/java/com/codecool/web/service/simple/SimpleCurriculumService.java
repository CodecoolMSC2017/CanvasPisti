package com.codecool.web.service.simple;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.*;
import com.codecool.web.service.CurriculumServiceInt;
import com.codecool.web.service.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class SimpleCurriculumService implements CurriculumServiceInt {
    private final PageDao pageDao;

    public SimpleCurriculumService(PageDao pageDao) {
        this.pageDao = pageDao;
    }

    @Override
    public TextPage addPage(TextPage page) throws ServiceException,SQLException {
        try {
            return pageDao.addTextPage(page);
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public Page addAssPage(AssignmentPage page) throws ServiceException, SQLException {
        try {
            return pageDao.addAssignment(page);
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    /*@Override
    public void scoring(HttpServletRequest req,PageDao pageDao,HttpServletResponse resp) throws ServiceException, SQLException, ServletException, IOException {
        for (Map.Entry<User, ArrayList<AssignmentPage>> entry : pageDao.getSubmissionList().entrySet()) {
            if (entry.getKey().getEmail().equals(req.getParameter("student"))) {
                req.setAttribute("student", entry.getKey());
                req.getSession().setAttribute("student", entry.getKey());
                ArrayList<AssignmentPage> pages = pageDao.getSubmissionList().get(entry.getKey());
                for (AssignmentPage asign : pages) {
                    if (asign.getTitle().equals(req.getParameter("item"))) {
                        req.setAttribute("aPage", asign);
                        req.getSession().setAttribute("aPage", asign);
                        req.getRequestDispatcher("scoring.jsp").forward(req, resp);
                    }
                }
            }
        }
    }*/


}
