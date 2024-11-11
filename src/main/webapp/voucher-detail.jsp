<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="dto.StaffDetailRespone" %>
<%@ page import="model.Warehouse" %>
<%@ page import="java.util.List" %>
<%@ page import="dal.AccountDAO" %>
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
<main>
  <jsp:include page="header-staff.jsp"></jsp:include>
  <section class="content-main mt-80 mb-80">
    <div class="card mx-auto card-login">
      <div class="card-body">
        <h4 class="card-title mb-4">Chinh sửa thông tin mã giảm giá</h4>
        <form action="editvoucher" method="post">
          <!-- Hidden field for StaffID and AccountID -->
          <%--                    <input type="hidden" name="staffID" value="${staff.staffID}" />--%>
          <%--                    <input type="hidden" name="accountID" value="${staff.account.accountID}" />--%>



          <div class="mb-3">
            <input class="form-control" name="voucherID" value="${voucher.voucherID}"  type="hidden" readonly="" />
          </div>

          <div class="mb-3">
            <input class="form-control" name="discountID" value="${voucher.discountID}"  type="hidden" readonly="" />
          </div>

          <div class="mb-3">
            <label class="form-label">Phần trăm giảm giá</label>
            <input class="form-control" name="discountPercent" value="${voucher.discountPercent}" type="text" readonly="" />
          </div>

          <div class="mb-3">
            <label class="form-label">Thời gian bắt đầu</label>
            <input class="form-control" name="startTime" value="${voucher.startTime}"  type="text"
                   readonly=""/>
          </div>
          <div class="mb-3">
            <label class="form-label">Thời gian kết thúc</label>
            <input class="form-control" name="endTime" value="${voucher.endTime}"  type="text"
                   readonly=""/>
          </div>

          <div class="mb-3">
            <label class="form-label">Số lượng</label>
            <input class="form-control" name="quantity" value="${voucher.quantity}"  type="number" min="1"
                   />
          </div>

          <div class="mb-4">
            <button type="submit" class="btn btn-primary w-100">Cập nhật</button>
          </div>
          <div class="mb-4">
            <button href="voucher" class="btn btn-primary w-100">Quay trở lại</button>
          </div>

        </form>
      </div>
    </div>

  </section>

</main>
<script>
  datePicker.max = new Date().toISOString().split("T")[0];
</script>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>

