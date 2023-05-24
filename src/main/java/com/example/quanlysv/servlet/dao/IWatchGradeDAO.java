package com.example.quanlysv.servlet.dao;

import com.example.quanlysv.servlet.entity.GradeEntity;
import com.example.quanlysv.servlet.entity.WacthGradeEntity;

import java.util.List;

public interface IWatchGradeDAO {
    List<WacthGradeEntity> getGradeByStudentId(String studentId);
}