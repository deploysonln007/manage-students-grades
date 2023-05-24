package com.example.quanlysv.servlet.service;

import com.example.quanlysv.servlet.entity.HocKyEntity;
import com.example.quanlysv.servlet.entity.LopEntity;
import com.example.quanlysv.servlet.entity.SubjectEntity;
import com.example.quanlysv.servlet.entity.TeacherEntity;

import java.util.List;

public interface IAddGradeService {
    List<HocKyEntity> getAllHocKy();
    List<LopEntity> getAllLop();
    List<SubjectEntity>getAllMonHoc();
    List<TeacherEntity>getAllGiangVien();

}
