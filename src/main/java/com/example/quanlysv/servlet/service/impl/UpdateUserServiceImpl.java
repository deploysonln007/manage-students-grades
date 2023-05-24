package com.example.quanlysv.servlet.service.impl;

import com.example.quanlysv.servlet.dto.request.student.CreateOrEditStudentDTO;
import com.example.quanlysv.servlet.dto.request.student.StudentDTO;
import com.example.quanlysv.servlet.dto.response.BaseResponse;
import com.example.quanlysv.servlet.entity.AccountEntity;
import com.example.quanlysv.servlet.entity.StudentEntity;
import com.example.quanlysv.servlet.service.IStudentService;
import com.example.quanlysv.servlet.service.IUpdateUserService;

public class UpdateUserServiceImpl implements IUpdateUserService {

    private final IStudentService service;

    public UpdateUserServiceImpl() {
        this.service = new StudentServiceImpl();
    }

    @Override
    public boolean updateUser(AccountEntity account, String phone, String email) {
        StudentDTO student = (StudentDTO) service.getStudentById(account.getUsername()).getData();

        CreateOrEditStudentDTO createOrEditStudentDTO = new CreateOrEditStudentDTO();
        createOrEditStudentDTO.setStatus(1);
        createOrEditStudentDTO.setEmailSv(email);
        createOrEditStudentDTO.setPhoneSv(phone);
        createOrEditStudentDTO.setIdSv(student.getIdSv());
        createOrEditStudentDTO.setDobSv(student.getDobSv());
        createOrEditStudentDTO.setTenSv(student.getTenSv());
        createOrEditStudentDTO.setGenderSv(student.getGenderSv());
        createOrEditStudentDTO.setLopHanhChinhSv(student.getLopHanhChinhSv());

        BaseResponse<?> baseResponse = service.createOrUpdateStudent(createOrEditStudentDTO);
        return baseResponse.getStatus() == 200;

    }

}
