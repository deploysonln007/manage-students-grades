<%@ page import="java.util.Random" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.quanlysv.servlet.entity.HocKyEntity" %>
<%@ page import="com.example.quanlysv.servlet.entity.TeacherEntity" %>
<%@ page import="com.example.quanlysv.servlet.entity.SubjectEntity" %>
<%@ page import="com.example.quanlysv.servlet.entity.LopEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../menu/admin_menu_header.jsp" %>
    <link rel="stylesheet" href="../../../assets/css/admin/add_grade.css">
    <link rel="stylesheet" href="../../../assets/themify-icons/themify-icons.css">
    <title>Nhập điểm</title>
</head>
<body>
    <%@include file="../menu/admin_menu.jsp" %>
    <div class="manHinhChinh">

        <%
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            int length = 8; // Change this to set the length of the generated ID
            StringBuilder idBuilder = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                idBuilder.append(randomChar);
            }

            String randomID = "Diem_"+idBuilder.toString();
        %>

        <!-- Use the generated ID wherever you need it -->
        <input type="hidden" name="idDiem" value="<%= randomID %>" />

        <% boolean showDialog = false; %>

        <%
            // Get the "showDialog" attribute from the request
            Boolean showDialogAttribute = (Boolean) request.getAttribute("showDialog");
            if (showDialogAttribute != null && showDialogAttribute.booleanValue()) {
                // Set the "showDialog" variable to true if the attribute is set to true
                showDialog = true;
            }
        %>
        <%
            List<Integer>listSoTP=new ArrayList<>();
            for(int i=0;i<=9;i++){
                listSoTP.add(i*10);
            }
        %>
        <h1 class="tieuDeTrang">Nhập điểm</h1>
        <%
            String malop = (String) request.getAttribute("idLop");
        %>
        <div id="my-dialog">
            <p>Nhập điểm thành công</p>
            <a href="/home/admin/grade/view-grade-after-add.jsp?idLop=<%=malop%>">
                <button class="xem hop-hanh-dong-nut" type="button">Xem điểm vừa nhập</button>
            </a>
            <button class="nutDong" onclick="closeDialog()">Đóng</button>
        </div>
        <div class="khuVucNhapDiem">
            <div class="khuVucNhapDiem-dau">
                <div class="khuVucNhapDiem-dau-tieuDe">Thông tin nhập điểm</div>
            </div>
            <div class="khuVucNhapDiem-phanThan">
                <form method="post" action="/api/admin/grade/create-or-edit" enctype="multipart/form-data">
                    <input type="hidden" name="idDiem" value="<%= randomID %>" />
                    <div class="phanThongTin">
                        <div class="cot">
                            <div class="hang">
                                <%
                                    List<HocKyEntity>listHK =(List<HocKyEntity>) request.getAttribute("listHocKy");
                                %>
                                <label for="" class="nhanModal">Học kỳ</label>
                                <select name="idHk" class="nhapModal" required>
                                    <option value="">Please select</option>
<%--                                    <option value="Class A">Học kì 1 năm 2020-2021</option>--%>
<%--                                    <option value="Class B">Học kì 2 năm 2020-2021</option>--%>
                                    <%
                                        if(listHK!=null){
                                        for(HocKyEntity hocKyEachRow : listHK){
                                    %>
                                    <option value=<%=hocKyEachRow.getIdHk()%>><%=hocKyEachRow.getTenHocKy()%></option>
                                <%--                                    <option value="Class A">Trần Quang Minh</option>--%>
                                    <%--                                    <option value="Class B">Trịnh Minh Tuấn</option>--%>
                                    <% }}
                                    %>
                                </select>
                            </div>

                            <div class="hang">
                                <%
                                    List<TeacherEntity>listGv =(List<TeacherEntity>) request.getAttribute("listGiangVien");
                                %>
                                <label for="" class="nhanModal">Giảng viên</label>

                                <select name="idGv" class="nhapModal" required>
                                    <option value="">Please select</option>
                                    <%
                                        if(listGv!=null){
                                        for(TeacherEntity giangVienEachRow : listGv){
                                    %>
                                    <option value=<%=giangVienEachRow.getIdGv()%>><%=giangVienEachRow.getTenGv()%></option>
                                    <%--                                    <option value="Class A">Trần Quang Minh</option>--%>
                                    <%--                                    <option value="Class B">Trịnh Minh Tuấn</option>--%>
                                    <% }}
                                    %>
                                </select>

                            </div>

                            <div class="hang">
                                <label for="" class="nhanModal">Chuyên cần</label>

                                <select name="chuyen_can" class="nhapModal" required>
                                    <option value="">Please select</option>
                                    <% for(Integer heso:listSoTP){ %>
                                    <option value=<%=heso%>><%=heso+"%"%></option>
                                    <% }
                                    %>
                                </select>

                            </div>

                            <div class="hang">
                                <label for="" class="nhanModal" >Bài tập</label>
                                <select name="bai_tap" class="nhapModal" required>
                                    <option value="">Please select</option>
                                    <% for(Integer heso:listSoTP){ %>
                                    <option value=<%=heso%>><%=heso+"%"%></option>
                                    <% }
                                    %>
                                </select>
                            </div>

                        </div>

                        <div class="cot">
                            <div class="hang">
                                <%
                                    List<SubjectEntity>listMh =(List<SubjectEntity>) request.getAttribute("listMonHoc");
                                %>
                                <label for="" class="nhanModal">Môn học</label>
                                <select  name="idMh" class="nhapModal" required>
                                    <option value="">Please select</option>
<%--                                    <option value="Class A">Lập trình Web</option>--%>
<%--                                    <option value="Class B">Cơ sở dữ liệu phân tán</option>--%>
                                    <%
                                        if(listMh!=null){
                                        for(SubjectEntity monHocEachRow : listMh){
                                    %>
                                    <option value=<%=monHocEachRow.getIdMh()%>><%=monHocEachRow.getTenMonHoc()%></option>
                                    <%--                                    <option value="Class A">Trần Quang Minh</option>--%>
                                    <%--                                    <option value="Class B">Trịnh Minh Tuấn</option>--%>
                                    <% }}
                                    %>
                                </select>
                            </div>

                            <div class="hang">
                                <%
                                    List<LopEntity>listLop =(List<LopEntity>) request.getAttribute("listLop");
                                %>
                                <label for="" class="nhanModal">Lớp</label>
                                <select  name="idLop" class="nhapModal" required>
                                    <option value="">Please select</option>
                                    <%--                                    <option value="Class A">Lập trình Web</option>--%>
                                    <%--                                    <option value="Class B">Cơ sở dữ liệu phân tán</option>--%>
                                    <%
                                        if(listLop!=null){
                                        for(LopEntity lopEachRow : listLop){
                                    %>
                                    <option value=<%=lopEachRow.getIdLop()%>><%=lopEachRow.getTenLop()%></option>
                                    <%--                                    <option value="Class A">Trần Quang Minh</option>--%>
                                    <%--                                    <option value="Class B">Trịnh Minh Tuấn</option>--%>
                                    <% }}
                                    %>
                                </select>
                            </div>

                            <div class="hang">
                                <label for="" class="nhanModal" >Điểm thi</label>
                                <select name="thi" class="nhapModal" required>
                                    <option value="">Please select</option>
                                    <% for(Integer heso:listSoTP){ %>
                                    <option value=<%=heso%>><%=heso+"%"%></option>
                                    <% }
                                    %>
                                </select>

                            </div>
                            <div class="hang">
                                <label for="" class="nhanModal" >Kiểm tra</label>
                                <select name="kiem_tra" class="nhapModal" required>
                                    <option value="">Please select</option>
                                    <% for(Integer heso:listSoTP){ %>
                                    <option value=<%=heso%>><%=heso+"%"%></option>
                                    <% }
                                    %>
                                </select>
                            </div>


<%--                            <div class="hang" style="display: none;">--%>
<%--                                <label for="" class="nhanModal">Mã điểm</label>--%>
<%--                                <input type="text" name="idDiem" class="nhapModal">--%>
<%--                            </div>--%>
                        </div>
                    </div>

                    <div class="cacNut">
                        <input class="nutChonFile" type="file" name="file" size="60" required accept=".xlsx, .xls"/>
                        <input class="nutNhapDiem" type="submit" value="Nhập điểm" />
                    </div>
                </form>

            </div>
        </div>
    </div>
</body>

<script>
    console.log(<%= showDialog %>);
    const dialog = document.getElementById("my-dialog");
    function closeDialog() {
        dialog.classList.remove('hien-thi-modal');
    }

    if (<%= showDialog %>) {
        dialog.classList.add('hien-thi-modal');
    }
</script>
<script src="../../../assets/js/menu.js"></script>
</html>
