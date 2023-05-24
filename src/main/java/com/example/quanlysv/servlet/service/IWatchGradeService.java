package com.example.quanlysv.servlet.service;

import com.example.quanlysv.servlet.entity.GradeEntity;
import com.example.quanlysv.servlet.entity.WacthGradeEntity;

import java.util.List;


public interface IWatchGradeService {
    List<WacthGradeEntity> getGradesByStudentId(String studentId);
}