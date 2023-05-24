package com.example.quanlysv.servlet.controller.user;

import com.example.quanlysv.servlet.dto.request.student.StudentDTO;
import com.example.quanlysv.servlet.entity.AccountEntity;
import com.example.quanlysv.servlet.entity.WacthGradeEntity;
import com.example.quanlysv.servlet.service.IStudentService;
import com.example.quanlysv.servlet.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/detail-user")
public class DetailUserController extends HttpServlet {
    private final IStudentService service ;

    public DetailUserController() {
        this.service = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session1 = req.getSession();
        AccountEntity account = (AccountEntity) session1.getAttribute("ACCOUNT");
        String tenTaiKhoan = account.getUsername();

        StudentDTO student = (StudentDTO) service.getStudentById(tenTaiKhoan).getData();
        System.out.println(student.getEmailSv());
        req.setAttribute("student", student);
        req.getRequestDispatcher("/home/user/home_user/home_user.jsp").forward(req, resp);

    }
}
