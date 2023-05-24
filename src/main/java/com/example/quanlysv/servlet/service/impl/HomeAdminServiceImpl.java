package com.example.quanlysv.servlet.service.impl;

import com.example.quanlysv.servlet.dao.IHomeAdminDAO;
import com.example.quanlysv.servlet.dao.impl.HomeAdminDAOImpl;
import com.example.quanlysv.servlet.service.IHomeAdminService;

public class HomeAdminServiceImpl implements IHomeAdminService {

    private IHomeAdminDAO homeAdminDAO;

    public HomeAdminServiceImpl() {
        this.homeAdminDAO = new HomeAdminDAOImpl();
    }

    @Override
    public Integer getTotalStudent() {
        return homeAdminDAO.getTotalStudent();
    }

    @Override
    public Integer getTotalTeacher() {
        return homeAdminDAO.getTotalTeacher();
    }

    @Override
    public Integer getTotalSubject() {
        return homeAdminDAO.getTotalSubject();
    }

    @Override
    public Integer getTotalClass() {
        return homeAdminDAO.getTotalClass();
    }
}
