<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../menu/user_menu_header.jsp" %>
    <link rel="stylesheet" href="../../../assets/css/user/watch_grade.css">
    <title>Xem điểm</title>
</head>
<body>

<%@include file="../menu/user_menu.jsp" %>
<div class="manHinhChinh">






    <h1 class = "tieuDeTrang">Bảng điểm học phần</h1>

    <table>
        <tr>
            <th>STT</th>
            <th>Tên môn</th>
            <th>TC</th>
            <th>%CC</th>
            <th>%BT</th>
            <th>%KT</th>
            <th>%Thi</th>
            <th>Điểm CC</th>
            <th>Điểm BT</th>
            <th>Điểm KT</th>
            <th>Điểm Thi</th>
            <th>Điểm TB(Thang 10)</th>
            <th>Điểm TB(dạng chữ)</th>
            <th>Điểm TB(Thang 4)</th>
            <th>Trạng thái</th>
        </tr>



            <c:forEach items="${grades}" var="grade">
                <tr>
                    <td>${grade.stt}</td>
                    <td>${grade.tenMonHoc}</td>
                    <td>${grade.soTinChi}</td>
                    <td>${grade.ptCC}</td>
                    <td>${grade.ptBT}</td>
                    <td>${grade.ptKT}</td>
                    <td>${grade.ptThi}</td>
                    <td>${grade.diemCC}</td>
                    <td>${grade.diemBT}</td>
                    <td>${grade.diemKT}</td>
                    <td>${grade.diemThi}</td>
                    <td>${grade.diemTBthang10}</td>
                    <td>${grade.diemTBdangChu}</td>
                    <td>${grade.diemTBthang4}</td>
                    <td>${grade.trangThai}</td>
                </tr>
            </c:forEach>


    </table>

</div>
</body>
<script src="../../../assets/js/menu.js"></script>
</html>