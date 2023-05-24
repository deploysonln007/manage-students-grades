package com.example.quanlysv.servlet.controller.lop.api.direct;

import com.example.quanlysv.servlet.common.Constant;
import com.example.quanlysv.servlet.entity.HocKyEntity;
import com.example.quanlysv.servlet.entity.LopEntity;
import com.example.quanlysv.servlet.entity.SubjectEntity;
import com.example.quanlysv.servlet.service.ILopService;
import com.example.quanlysv.servlet.service.impl.LopServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/class-direct")
public class ClassController extends HttpServlet {
    private ILopService service;

    public ClassController() {
        this.service = new LopServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=======================ClassController=========================");

        req.setCharacterEncoding("UTF-8");
        List<LopEntity> list=service.getAllLop();
        for(LopEntity tmp:list){
            System.out.println(tmp.toString());
        }
        try{
            if(!list.isEmpty()){
                req.setAttribute("listLop", list);
                req.getRequestDispatcher("/home/admin/grade/add_grade.jsp").forward(req, resp);
            }
        }catch (Exception e){
            String errorMessage = Constant.ERROR_LOGIN;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/auth/page_404.jsp").forward(req, resp);
        }
    }
}
