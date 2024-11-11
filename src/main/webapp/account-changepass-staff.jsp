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
  <jsp:param name="page" value=""/>
  <jsp:param name="menu" value="changepass"/>
</jsp:include>
<main class="main-wrap">
  <jsp:include page="header-staff.jsp"></jsp:include>
  <div class="card">
    <div class="card-header">
      <h5>Thay đổi mật khẩu</h5>
    </div>
    <div class="card-body">
      <%
        String msg = (String)request.getAttribute("msg");
        if(msg==null) msg="";
      %>
      <h6 style="color: red"><%=msg%></h6>
      <form  action="changepass" method="post" name="enq">
        <input type="text" hidden value="account-changepass-staff.jsp" name="PageChangePass"/>
        <div class="form-group col-md-5">
          <label>Mật khẩu cũ <span class="required">*</span></label>
          <input required="" class="form-control" name="oldPassword" type="password" minlength="8"/>
        </div>
        <div class="form-group col-md-5">
          <label>Mật khẩu mới<span class="required">*</span></label>
          <input required="" class="form-control" name="newPassword" type="password" minlength="8"/>
        </div>
        <div class="form-group col-md-5">
          <label>Xác nhận lại mật khẩu <span class="required">*</span></label>
          <input required="" class="form-control" name="confirmPassword" type="password" minlength="8"/>
        </div>

        <div class="col-md-12">
          <button style="color: green;" type="submit" class="btn btn-fill-out submit font-weight-bold" name="submit" value="Submit">Lưu thay đổi</button>
        </div>

      </form>
    </div>
  </div>
  <!-- content-main end// -->
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<script src="nest-backend/assets/js/vendors/chart.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
<script src="nest-backend/assets/js/custom-chart.js" type="text/javascript"></script>
</body>
</html>
