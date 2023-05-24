package com.example.quanlysv.servlet.controller.add_grade;

import com.example.quanlysv.servlet.service.IAddGradeService;
import com.example.quanlysv.servlet.service.impl.AddGradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/admin/home/info-to-add-grade")
public class AddGradeController extends HttpServlet {
    private IAddGradeService service;
    public AddGradeController() {
        this.service = new AddGradeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("listHocKy", service.getAllHocKy());
        req.setAttribute("listLop", service.getAllLop());
        req.setAttribute("listGiangVien", service.getAllGiangVien());
        req.setAttribute("listMonHoc", service.getAllMonHoc());
        req.getRequestDispatcher("/home/admin/grade/add_grade.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("listHocKy", service.getAllHocKy());
        req.setAttribute("listLop", service.getAllLop());
        req.setAttribute("listGiangVien", service.getAllGiangVien());
        req.setAttribute("listMonHoc", service.getAllMonHoc());
        req.getRequestDispatcher("/home/admin/grade/add_grade.jsp").forward(req, resp);

    }
}
