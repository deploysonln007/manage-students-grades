package com.example.quanlysv.servlet.controller.user;

import com.example.quanlysv.servlet.dto.request.student.StudentDTO;
import com.example.quanlysv.servlet.entity.AccountEntity;
import com.example.quanlysv.servlet.service.IStudentService;
import com.example.quanlysv.servlet.service.IUpdateUserService;
import com.example.quanlysv.servlet.service.impl.StudentServiceImpl;
import com.example.quanlysv.servlet.service.impl.UpdateUserServiceImpl;
import com.example.quanlysv.servlet.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update-user")
public class UpdateUserController extends HttpServlet {
    private final IUpdateUserService service ;

    public UpdateUserController() {
        this.service = new UpdateUserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneNumber = req.getParameter("phone");
        String email = req.getParameter("email");
        AccountEntity account = (AccountEntity) SessionUtils.getInstance().getValue(req,"ACCOUNT");
        if(service.updateUser(account, phoneNumber, email)){
            resp.sendRedirect("/user/detail-user");
            return;
        }
        req.setAttribute("error", "Cập nhật thông tin thất bại");
        req.getRequestDispatcher("/home/user/home_user/home_user.jsp").forward(req, resp);
    }
}
