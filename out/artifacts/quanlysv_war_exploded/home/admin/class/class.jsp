<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.impl.client.HttpClientBuilder" %>
<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <%@include file="../menu/admin_menu_header.jsp" %>
  <title>Danh sách lớp</title>
  <link rel="stylesheet" href="../../../assets/fonts/fontawesome-free-6.3.0-web/css/all.min.css">
  <link rel="stylesheet" href="../../../assets/themify-icons/themify-icons.css">
  <link rel="stylesheet" href="../../../assets/css/admin/class.css">
  <link rel="stylesheet" href="../../../assets/css/admin/add_class_form.css">
  <link rel="stylesheet" href="../../../assets/css/pagination.css">
  <link rel="stylesheet" href="../../../assets/css/page_404.css">
  <link rel="stylesheet" href="../../../assets/css/admin/update_class_form.css">
  <link rel="stylesheet" href="../../../assets/css/admin/confirm_delete_form.css">
</head>

<body>
<%
  HttpClient httpClient = HttpClientBuilder.create().build();

  // get session id
  String value = "JSESSIONID=";
  HttpSession getSession = request.getSession();
  String cookieValue = (String) getSession.getAttribute("cookie_value");

  // variables in request body
  String khoa = request.getParameter("khoa");
  String tenLop = "";
  String sortField = "";
  String sortOrder = "";
  int pageSize = 10;

  int pageIndex = 1;
  if(session.getAttribute("pageIndexGV")!=null) {
    pageIndex = (int) session.getAttribute("pageIndexGV");
  }

  System.out.println(session.getAttribute("pageIndexGV"));
  System.out.println("pageIndex in teacher.jsp: " + pageIndex);
  System.out.println("pageSize in teacher.jsp: " + pageSize);

  // request body for getAll, finding and sorting
  String requestBody ="{"+
          "\"idKhoa\":\"" + khoa + "\","+
          "\"tenLop\":\"" + tenLop + "\","+
          "\"baseRequest\":{"+
          "\"sortField\":\"" + sortField + "\","+
          "\"sortOrder\":\"" + sortOrder + "\","+
          "\"pageIndex\":" + pageIndex + ","+
          "\"pageSize\":" + pageSize +
          "}"+
          "}";

  // get baseUrl
  ServletContext context = request.getServletContext();
  String baseUrl = context.getInitParameter("apiUrl");

  //get all
  String uriGetAll = baseUrl + "/admin/class";

  HttpPost httpPost = new HttpPost(uriGetAll);
  StringEntity entity = new StringEntity(requestBody);
  httpPost.setEntity(entity);

  httpPost.setHeader("Cookie", value + cookieValue);

  // call function, return data
  JSONArray listResp;
%>

<%!
  int totalPages;

  // Hàm Refresh lại trang lấy lớp
  public JSONArray getAllLop(HttpClient httpClient, HttpPost httpPost) throws IOException {
    HttpResponse resp  = httpClient.execute(httpPost);
    String responseBody = EntityUtils.toString(resp.getEntity());
    JSONObject jsonResponse = new JSONObject(responseBody);
    if(jsonResponse.getInt("status")==200) {
      totalPages = jsonResponse.getInt("totalPages");
      return jsonResponse.getJSONArray("data");
    } else {
      return null;
    }
  }
%>

<%
  // tìm kiếm
  tenLop = request.getParameter("nhapTimKiem");
  if(tenLop==null) tenLop = "";
  System.out.println("Ten Lop tim kiem: " + tenLop);
  requestBody = "{"+
          "\"idKhoa\":\"" + khoa + "\","+
          "\"tenLop\":\"" + tenLop + "\","+
          "\"baseRequest\":{"+
          "\"sortField\":\"" + sortField + "\","+
          "\"sortOrder\":\"" + sortOrder + "\","+
          "\"pageIndex\":" + pageIndex + ","+
          "\"pageSize\":" + pageSize +
          "}"+
          "}";
  entity = new StringEntity(requestBody);
  httpPost.setEntity(entity);
  listResp = getAllLop(httpClient, httpPost);

  // return UTF8
  byte[] bytes = tenLop.getBytes(StandardCharsets.ISO_8859_1);
  tenLop = new String(bytes, StandardCharsets.UTF_8);
%>

<div class="lop">
  <header class="phanlop-header">
    <div class="lop-tieuDe">Danh sách các lớp khoa
      <% if(khoa.equals("CNTT")) { %>
        <span id="lop-tieuDe-chiTiet">Công nghệ thông tin</span>
      <% } else if(khoa.equals("ATTT")) { %>
      <span id="lop-tieuDe-chiTiet">An toàn thông tin</span>
      <% } else if(khoa.equals("DTVT")) { %>
      <span id="lop-tieuDe-chiTiet">Điện tử viễn thông</span>
      <% } else if(khoa.equals("DPT")) { %>
      <span id="lop-tieuDe-chiTiet">Đa phương tiện</span>
      <% } else if(khoa.equals("KCB")) { %>
      <span id="lop-tieuDe-chiTiet">Cơ bản</span>
      <% } %>
    </div>

    <div class="phanlop-close js-phanlop-close" class="back" onclick="location.href='../specialization/specialization.jsp'">
      Khoa Chuyên Môn
    </div>
  </header>
  <div class="themVaTimKiem">
    <!-- nut them lop -->
    <button id="nut_them_lop" onclick="showModal('modal_lop')" class="nutThemlop js-nutThemlop" type="button">
      <span class="nutThemlop_tieuDe">Thêm lớp</span>
      <i class="fa-solid fa-plus"></i>
    </button>
    <form class="timKiem" method="post">
      <div class="tieuDeTimKiem">Tìm kiếm lớp: </div>
      <input type="search" id="nhapTimKiem" name="nhapTimKiem" placeholder="Nhập tên lớp" >
      <button class="nutTimKiem" type="submit">
        <span class="nutTimKiem_tieuDe">Tìm</span>
        <i class="fa-solid fa-magnifying-glass"></i>
      </button>
    </form>
  </div>

  <div class="boc-bang">
    <table class="danhSach">
      <thead class="hang1">
      <th data-sort onclick="sortTable(0, this)" class="cot-malop">Mã lớp</th>
      <th data-sort onclick="sortName(1,this)" class="cot-tenlop">Tên lớp</th>
      <th data-sort onclick="sortName(2,this)" class="cot-tenHK">Tên học kỳ</th>
      <th data-sort onclick="sortName(3,this)" class="cot-ngayTao">Ngày tạo</th>
      <th data-sort onclick="sortName(4,this)" class="cot-ngayTao">Ngày cập nhật</th>
      <th data-sort onclick="sortName(5,this)" class="hanh-dong">Action</th>
      </thead>
      <tbody>
      <%--            hiển thị ra màn hình--%>
      <%  if(listResp!=null) {
        for(int i=0;i<listResp.length();i++) {%>
      <% JSONObject lop = listResp.getJSONObject(i); %>
      <tr>
        <td> <%= lop.getString("idLop") %> </td>
        <td><%= lop.getString("tenLop") %></td>
        <td><%= lop.getString("idHk") %></td>
        <td><%= lop.getString("ngayTao") %></td>
        <td><%= lop.getString("ngaySua") %></td>
        <td class="chucNang">
          <div class="hop-hanh-dong">
            <a href="../grade/view-grade.jsp?idLop=<%=lop.getString("idLop")%>&khoa=<%=khoa%>">
              <button class="xem hop-hanh-dong-nut" type="button">
                <span class="sua_tieuDe">Xem điểm</span>
                <i class="fa-solid fa-eye sua_icon"></i>
              </button>
            </a>
          </div>
          <div class="hop-hanh-dong">
            <button class="sua hop-hanh-dong-nut" type="button"
                    onclick="showModalSua('modal_lop_sua', '<%= lop.getString("idLop") %>', '<%= lop.getString("tenLop") %>', '<%= lop.getString("idHk") %>')">
              <span class="sua_tieuDe">Sửa</span>
              <i class="fa-solid fa-pencil sua_icon"></i>
            </button>
          </div>
        </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </div>
  <div class="phanTrang">
    <ul></ul>
  </div>
</div>
<%@include file="add_class_form.jsp" %>
<%@include file="confirm_delete_class.jsp" %>
<%@include file="update_class_form.jsp" %>
<script src="../../../assets/js/admin/pagination_class.js"></script>
<script src="../../../assets/js/admin/add_form.js"></script>
<script src="../../../assets/js/admin/class.js"></script>
<script src="../../../assets/js/admin/text_error_class.js"></script>
<script src="../../../assets/js/admin/update_class.js"></script>
<script src="../../../assets/js/admin/confirm_delete_form.js"></script>

</body>

</html>
