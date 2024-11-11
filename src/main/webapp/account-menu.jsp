<%
  String pageActive = request.getParameter("page");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="dashboard-menu">
  <ul class="nav flex-column" role="tablist">
    <li class="nav-item">
      <a class="nav-link" id="account-detail-tab" href="page-account-information.jsp"><i class="fi-rs-user mr-10"></i>Tài khoản</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="orders-tab" href="orderHistory"><i class="fi-rs-shopping-cart-check mr-10"></i>Đơn hàng</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="voucher-tab" href="LoadVoucher"><i class="fi-rs-shopping-cart-check mr-10"></i>Mã giảm giá</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="contact-tab" href="AccountContact"><i class="fi-rs-user mr-10"></i>Thông tin liên hệ</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="change-password-tab" href="page-account-changepass.jsp"><i class="fi-rs-user mr-10"></i>Đổi mật khẩu</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="logout"><i class="fi-rs-sign-out mr-10"></i>Đăng xuất</a>
    </li>
  </ul>
</div>
<script>
  document.getElementById("<%=pageActive%>").classList.add("active");
</script>