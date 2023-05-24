package com.example.quanlysv.servlet.service;

import com.example.quanlysv.servlet.dto.request.BaseRequest;
import com.example.quanlysv.servlet.dto.request.subject.CreateOrEditSubjectDTO;
import com.example.quanlysv.servlet.dto.request.subject.SubjectDTO;
import com.example.quanlysv.servlet.dto.request.subject.SubjectFilter;
import com.example.quanlysv.servlet.dto.response.BaseResponse;
import com.example.quanlysv.servlet.entity.SubjectEntity;

import java.util.List;

public interface ISubjectService {
    List<SubjectEntity>getAllMonHoc();
    BaseResponse<?> findSubject(SubjectFilter baseRequest);

    BaseResponse<?> createOrUpdateSubject(CreateOrEditSubjectDTO dto);

    BaseResponse<?> deleteSubject(String id);
}
