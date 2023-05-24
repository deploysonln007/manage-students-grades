package com.example.quanlysv.servlet.controller.home_admin;

import com.example.quanlysv.servlet.service.*;
import com.example.quanlysv.servlet.service.impl.HomeAdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin/home")
public class HomeAdminController extends HttpServlet {

    private IHomeAdminService homeAdminService;

    public HomeAdminController() {
        this.homeAdminService = new HomeAdminServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teacherDashboard", homeAdminService.getTotalTeacher());
        request.setAttribute("lopDashboard", homeAdminService.getTotalClass());
        request.setAttribute("subjectDashboard", homeAdminService.getTotalSubject());
        request.setAttribute("studentDashboard", homeAdminService.getTotalStudent());
        request.getRequestDispatcher("/home/admin/home_admin/home_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teacherDashboard", homeAdminService.getTotalTeacher());
        request.setAttribute("lopDashboard", homeAdminService.getTotalClass());
        request.setAttribute("subjectDashboard", homeAdminService.getTotalSubject());
        request.setAttribute("studentDashboard", homeAdminService.getTotalStudent());
        request.getRequestDispatcher("/home/admin/home_admin/home_admin.jsp").forward(request, response);
    }
}
