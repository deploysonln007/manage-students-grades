package com.example.quanlysv.servlet.dao;

import com.example.quanlysv.servlet.dto.request.BaseRequest;
import com.example.quanlysv.servlet.dto.request.lop.LopFilter;
import com.example.quanlysv.servlet.dto.request.teacher.TeacherFilter;
import com.example.quanlysv.servlet.entity.LopEntity;

import java.util.List;

public interface ILopDao extends IGenericDao<LopEntity> {

    List<LopEntity>getAllLop();
    void createOrUpdateLop(LopEntity lopEntity);

    List<LopEntity>findLop(LopFilter request);

    void deleteLopById(String id);

    List<LopEntity> getListLopByIdKhoa(String idKhoa);

    Integer countTotalRecords(LopFilter request);

    boolean existedByIdAndTenLop(String id, String tenLop);



}