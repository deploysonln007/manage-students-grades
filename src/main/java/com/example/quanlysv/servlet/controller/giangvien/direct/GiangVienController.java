package com.example.quanlysv.servlet.controller.giangvien.direct;

import com.example.quanlysv.servlet.common.Constant;
import com.example.quanlysv.servlet.entity.HocKyEntity;
import com.example.quanlysv.servlet.entity.LopEntity;
import com.example.quanlysv.servlet.entity.SubjectEntity;
import com.example.quanlysv.servlet.entity.TeacherEntity;
import com.example.quanlysv.servlet.service.ITeacherService;
import com.example.quanlysv.servlet.service.impl.TeacherServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/home/teacher-direct")
public class GiangVienController extends HttpServlet {
    private ITeacherService service;
    public GiangVienController() {
        this.service = new TeacherServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=======================GiangVienController=========================");

        req.setCharacterEncoding("UTF-8");
        List<TeacherEntity> list=service.getAllGiangVien();
        for(TeacherEntity tmp:list){
            System.out.println(tmp.toString());
        }
        try{
            if(!list.isEmpty()){
                req.setAttribute("listGiangVien", list);
//                req.getRequestDispatcher("/home/admin/grade/add_grade.jsp").forward(req, resp);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/api/admin/class-direct");
                dispatcher.forward(req, resp);

            }
        }catch (Exception e){
            String errorMessage = Constant.ERROR_LOGIN;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/auth/page_404.jsp").forward(req, resp);
        }

    }
}
