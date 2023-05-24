package com.example.quanlysv.servlet.controller.lop.api;

import com.example.quanlysv.servlet.dto.request.lop.CreateOrEditLopDTO;
import com.example.quanlysv.servlet.dto.request.lop.LopDTO;
import com.example.quanlysv.servlet.dto.request.student.CreateOrEditStudentDTO;
import com.example.quanlysv.servlet.dto.response.BaseResponse;
import com.example.quanlysv.servlet.service.ILopService;
import com.example.quanlysv.servlet.service.impl.LopServiceImpl;
import com.example.quanlysv.servlet.util.HttpUtil;
import com.example.quanlysv.servlet.util.ResponseUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/api/admin/home/class")

public class CreateOrEditClass extends HttpServlet {
    private ILopService service;

    public CreateOrEditClass() {
        this.service = new LopServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        CreateOrEditLopDTO baseRequest = HttpUtil.of(req.getReader()).toModel(CreateOrEditLopDTO.class);
        BaseResponse<?> baseResponse = service.createOrUpdateLop(baseRequest);
        ResponseUtils.responseApi(req, resp, baseResponse);
    }
}
