package com.example.quanlysv.servlet.service.impl;

import com.example.quanlysv.servlet.dao.IHocKyDao;
import com.example.quanlysv.servlet.dao.ILopDao;
import com.example.quanlysv.servlet.dao.ISubjectDao;
import com.example.quanlysv.servlet.dao.ITeacherDao;
import com.example.quanlysv.servlet.dao.impl.HocKyDaoImpl;
import com.example.quanlysv.servlet.dao.impl.LopDaoImpl;
import com.example.quanlysv.servlet.dao.impl.SubjectDaoImpl;
import com.example.quanlysv.servlet.dao.impl.TeacherDaoImpl;
import com.example.quanlysv.servlet.entity.HocKyEntity;
import com.example.quanlysv.servlet.entity.LopEntity;
import com.example.quanlysv.servlet.entity.SubjectEntity;
import com.example.quanlysv.servlet.entity.TeacherEntity;
import com.example.quanlysv.servlet.service.IAddGradeService;
import org.apache.log4j.Logger;

import java.util.List;

public class AddGradeServiceImpl implements IAddGradeService {
    private static final org.apache.log4j.Logger log = Logger.getLogger(AddGradeServiceImpl.class.getName());
    private ITeacherDao teacherDao;
    private ISubjectDao subjectDao;
    private IHocKyDao hocKyDao;
    private ILopDao lopDao;

    public AddGradeServiceImpl(){
        teacherDao = new TeacherDaoImpl();
        hocKyDao = new HocKyDaoImpl();
        lopDao = new LopDaoImpl();
        subjectDao = new SubjectDaoImpl();
    }

    @Override
    public List<HocKyEntity> getAllHocKy() {
        return hocKyDao.getAllHocKy();
    }

    @Override
    public List<LopEntity> getAllLop() {
        return lopDao.getAllLop();
    }

    @Override
    public List<SubjectEntity> getAllMonHoc() {
        return subjectDao.getAllMonHoc();
    }

    @Override
    public List<TeacherEntity> getAllGiangVien() {
        return teacherDao.getAllGiangVien();
    }
}
