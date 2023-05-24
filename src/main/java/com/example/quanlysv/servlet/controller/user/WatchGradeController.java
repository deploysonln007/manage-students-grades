package com.example.quanlysv.servlet.controller.user;

import com.example.quanlysv.servlet.entity.AccountEntity;
import com.example.quanlysv.servlet.entity.GradeEntity;
import com.example.quanlysv.servlet.entity.WacthGradeEntity;
import com.example.quanlysv.servlet.service.IWatchGradeService;
import com.example.quanlysv.servlet.service.impl.HomeAdminServiceImpl;
import com.example.quanlysv.servlet.service.impl.WatchGradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/grade")
public class WatchGradeController extends HttpServlet {
    private IWatchGradeService watchGradeService;

    public WatchGradeController() {
        this.watchGradeService = new WatchGradeServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session1 = request.getSession();
        AccountEntity account = (AccountEntity) session1.getAttribute("ACCOUNT");
        String tenTaiKhoan = account.getUsername();
        System.out.println(tenTaiKhoan);
        List<WacthGradeEntity> grades = watchGradeService.getGradesByStudentId(tenTaiKhoan);
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("/home/user/grade/watch_grade.jsp").forward(request, response);
    }
}

