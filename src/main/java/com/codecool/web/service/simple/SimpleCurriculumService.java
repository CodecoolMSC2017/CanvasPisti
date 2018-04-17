package com.codecool.web.service.simple;

import com.codecool.web.dao.PageDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.AssignmentPage;
import com.codecool.web.model.Page;
import com.codecool.web.model.TextPage;
import com.codecool.web.service.CurriculumServiceInt;
import com.codecool.web.service.exceptions.ServiceException;

import java.sql.SQLException;

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
}
