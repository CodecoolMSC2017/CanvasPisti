package com.codecool.web.service;

import com.codecool.web.dao.PageDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Page;
import com.codecool.web.model.Singletondb;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface CurriculumServiceInt {
    Page addPage(TextPage page) throws ServiceException, SQLException;

    Page addAssPage(AssignmentPage page) throws ServiceException, SQLException;

    //void scoring(HttpServletRequest req, PageDao pageDao) throws ServiceException,SQLException;
}
