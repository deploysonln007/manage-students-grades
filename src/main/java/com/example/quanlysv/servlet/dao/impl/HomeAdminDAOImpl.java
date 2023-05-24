package com.example.quanlysv.servlet.dao.impl;

import com.example.quanlysv.servlet.dao.IHomeAdminDAO;

public class HomeAdminDAOImpl extends AbstractDao<Object> implements IHomeAdminDAO {
    @Override
    public Integer getTotalStudent() {
        String query = "select count(*) from sinhvien where trang_thai = ?";
        Integer totalRecords = countTotalRecords(query, 1);
        return totalRecords;
    }

    @Override
    public Integer getTotalTeacher() {
        String query = "select count(*) from giangvien where trang_thai = ?";
        Integer totalRecords = countTotalRecords(query, 1);
        return totalRecords;
    }

    @Override
    public Integer getTotalSubject() {
        String query = "select count(*) from monhoc where trang_thai = ?";
        Integer totalRecords = countTotalRecords(query, 1);
        return totalRecords;
    }

    @Override
    public Integer getTotalClass() {
        String query = "select count(*) from lop where 1 = ?";
        Integer totalRecords = countTotalRecords(query, 1);
        return totalRecords;
    }
}
