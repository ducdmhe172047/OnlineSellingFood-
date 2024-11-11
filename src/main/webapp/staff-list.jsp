<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Nest Dashboard</title>
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <meta property="og:title" content="" />
  <meta property="og:type" content="" />
  <meta property="og:url" content="" />
  <meta property="og:image" content="" />
  <!-- Favicon -->
  <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg" />
  <!-- Template CSS -->
  <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
  <jsp:param name="page" value="staff"/>
  <jsp:param name="menu" value="account"/>
</jsp:include>
<main class="main-wrap">
  <jsp:include page="header-staff.jsp"></jsp:include>
  <section class="content-main">
    <div class="content-header">
      <h2 class="content-title">Tài khoản</h2>
      <div>
        <a href="registerstaff" class="btn btn-primary"><i class="material-icons md-plus"></i> Tạo mới </a>
      </div>
    </div>
    <div class="card mb-4">
      <header class="card-header">
        <div class="row gx-3">
          <div class="col-lg-4 col-md-6 me-auto">
            <form action="staffList" method="GET" style="display: flex;">
              <input type="text" name="searchName" placeholder="Tìm kiếm theo tên..." class="form-control"
                    value="${searchName}" style="flex: 1;" />
              <button type="submit" class="btn btn-primary" style="margin-left: 10px;">Tìm kiếm</button>
            </form>
          </div>
        </div>
      </header>
      <!-- card-header end// -->
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th>Nhân viên</th>
              <th>Email</th>
              <th>Trạng thái</th>
              <th>Ngày đăng ký</th>
              <th class="text-end">Hoạt động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${staffList}"  var="sl">
            <tr>
              <td width="40%">
                <a href="#" class="itemside">
                  <div class="left">
                    <img src="nest-backend/assets/imgs/people/avatar-1.png" class="img-sm img-avatar" alt="Userpic" />
                  </div>
                  <div class="info pl-3">
                    <h6 class="mb-0 title">${sl.name}</h6>
                  </div>
                </a>
              </td>
              <input value="${sl.accountID}" name="accountID" type="hidden">
              <td>${sl.email}</td>
              <td><span class="badge rounded-pill alert-success">${sl.detail}</span></td>
              <td>${sl.time}</td>
              <td class="text-end">
                <a href="staffListDetail?accountID=${sl.accountID}" class="btn btn-sm btn-brand rounded font-sm mt-15">Xem chi tiết</a>
              </td>
            </tr>
            </c:forEach>

                        </tbody>
                    </table>
                    <!-- table-responsive.// -->
                </div>
            </div>
            <!-- card-body end// -->
        </div>
        <!-- card end// -->
        <div class="pagination-area mt-15 mb-50">
            <nav aria-label="Page navigation example">


                    <ul class="pagination justify-content-start">
                        <c:forEach begin="01" end="${endPage}" var="i">
                        <li class="${index==i?"page-item active":""} "><a class="page-link" href="staffList?index=${i}&searchName=${searchName}">${i}</a></li>
                        </c:forEach>
                    </ul>


            </nav>
        </div>
    </section>
    <!-- content-main end// -->
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>
