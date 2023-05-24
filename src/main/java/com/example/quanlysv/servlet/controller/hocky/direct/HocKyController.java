package com.example.quanlysv.servlet.controller.hocky.direct;

import com.example.quanlysv.servlet.common.Constant;
import com.example.quanlysv.servlet.entity.HocKyEntity;
import com.example.quanlysv.servlet.service.IHocKyService;
import com.example.quanlysv.servlet.service.impl.HocKyServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/home/semester-direct")
public class HocKyController extends HttpServlet {
    private IHocKyService service;
    public HocKyController(){
        this.service = new HocKyServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=======================HocKyController=========================");
        req.setCharacterEncoding("UTF-8");
        List<HocKyEntity>list=service.getAllHocKy();
        for(HocKyEntity tmp:list){
            System.out.println(tmp.toString());
        }
        try{
            if(!list.isEmpty()){
                req.setAttribute("listHocKy", list);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/api/admin/home/subject/display-direct");
                dispatcher.forward(req, resp);
            }
        }catch (Exception e){
            String errorMessage = Constant.ERROR_LOGIN;
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/auth/page_404.jsp").forward(req, resp);
        }
    }
}
