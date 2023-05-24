package com.example.quanlysv.servlet.service;

import com.example.quanlysv.servlet.dto.request.BaseRequest;
import com.example.quanlysv.servlet.dto.request.kyhoc.CreateOrEditSemesterDTO;
import com.example.quanlysv.servlet.dto.request.kyhoc.SemesterDTO;
import com.example.quanlysv.servlet.dto.response.BaseResponse;
import com.example.quanlysv.servlet.entity.HocKyEntity;

import java.util.List;

public interface IHocKyService {

     List<HocKyEntity> getAllHocKy();
     BaseResponse<?> createOrEdit(CreateOrEditSemesterDTO semesterDTO);

     BaseResponse<?> findSemester(BaseRequest request);

     BaseResponse<?> findSemesterById(String id);

     BaseResponse<?> deleteSemester(String id);
}
