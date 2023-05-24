package com.example.quanlysv.servlet.service.impl;

import com.example.quanlysv.servlet.dao.IWatchGradeDAO;
import com.example.quanlysv.servlet.dao.impl.WatchGradeDaoImpl;
import com.example.quanlysv.servlet.entity.WacthGradeEntity;
import com.example.quanlysv.servlet.service.IWatchGradeService;

import java.util.List;


public class WatchGradeServiceImpl implements IWatchGradeService {
    private IWatchGradeDAO watchGradeDAO;

    public WatchGradeServiceImpl() {
        this.watchGradeDAO = new WatchGradeDaoImpl();
    }


    @Override
    public List<WacthGradeEntity> getGradesByStudentId(String studentId) {
        return watchGradeDAO.getGradeByStudentId(studentId);
    }
}