<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@include file="/home/admin/menu/admin_menu_header.jsp" %>
    <link rel="stylesheet" href="../../../assets/css/admin/home_admin.css">

    <title>Trang chủ</title>
</head>
<body>
<%@include file="../menu/admin_menu.jsp" %>
<div class="manHinhChinh">


    <div id="noidung">
        <div class="noidung-header">
            <h2>Dashboard</h2>
        </div>
        <section class="content">
            <div class="container">
                <div class="bon-box">
                    <div class="box1">
                        <div class="small-box1">
                            <div class="benTrong">
                                <h3>${studentDashboard}</h3>
                                <p>Sinh viên</p>
                            </div>
                            <div class="icon i1">
                                <i class="ti-user"></i>
                            </div>
                        </div>
                        <a href="/../../home/admin/student/student.jsp" class="small-box-duoicung f1">Xem thêm</a>
                    </div>
                    <div class="box2">
                        <div class="small-box2">
                            <div class="benTrong">
                                <h3>${teacherDashboard}</h3>
                                <p>Giảng viên</p>
                            </div>
                            <div class="icon i2">
                                <i class="ti-bag"></i>
                            </div>
                        </div>
                        <a href="/../../home/admin/teacher/teacher.jsp" class="small-box-duoicung f2">Xem thêm</a>
                    </div>
                    <div class="box3">
                        <div class="small-box3">
                            <div class="benTrong">
                                <h3>${subjectDashboard}</h3>
                                <p>Môn học</p>
                            </div>
                            <div class="icon i3">
                                <i class="ti-bar-chart"></i>
                            </div>
                        </div>
                        <a href="/../../home/admin/subject/subject.jsp" class="small-box-duoicung f3">Xem thêm</a>
                    </div>
                    <div class="box4">
                        <div class="small-box4">
                            <div class="benTrong">
                                <h3>${lopDashboard}</h3>
                                <p>Lớp học phần</p>
                            </div>
                            <div class="icon i4">
                                <i class="ti-user"></i>
                            </div>
                        </div>
                        <a href="/../../home/admin/specialization/specialization.jsp" class="small-box-duoicung f4">Xem thêm</a>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="../../../assets/js/menu.js"></script>
<script src="../../../assets/js/admin/home_admin.js"></script>
</body>
</html>