package com.codecool.web.service;

import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Page;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.exceptions.ServiceException;

import java.sql.SQLException;

public interface CurriculumServiceInt {
    Page addPage(TextPage page) throws ServiceException, SQLException;

    Page addAssPage(AssignmentPage page) throws ServiceException, SQLException;
}
