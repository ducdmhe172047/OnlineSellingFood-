<%@ page import="model.Account" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/19/2024
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html class="no-js">
<head>
    <meta charset="utf-8"/>
    <title>Nest - Multipurpose eCommerce HTML Template</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta property="og:title" content=""/>
    <meta property="og:type" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:image" content=""/>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-frontend/assets/imgs/theme/favicon.svg"/>
    <!-- Template CSS -->
    <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0"/>
</head>

<body>
<%
    String accountName = ((Account) session.getAttribute("account")).getName();
%>
<jsp:include page="header.jsp">
    <jsp:param name="accountName" value="<%=accountName%>"/>
</jsp:include>
<!--End header-->
<main class="main pages">
    <div class="page-header breadcrumb-wrap">
        <div class="container">
            <div class="breadcrumb">
                <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Trang chủ</a>
                <span></span> Trang <span></span> Tài khoản của tôi
            </div>
        </div>
    </div>
    <div class="page-content pt-150 pb-150">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 m-auto">
                    <div class="row">
                        <div class="col-md-3">
                            <jsp:include page="account-menu.jsp">
                                <jsp:param name="page" value="change-password-tab"/>
                            </jsp:include>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content account dashboard-content pl-50">
                                <div class="tab-pane fade active show" id="change-password" role="tabpanel"
                                     aria-labelledby="change-password-tab">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Thay đổi mật khẩu</h5>
                                        </div>
                                        <div class="card-body">
                                            <%
                                                String msg = (String) request.getAttribute("msg");
                                                if (msg == null) msg = "";
                                            %>
                                            <h6 style="color: red"><%=msg%>
                                            </h6>
                                            <form action="changepass" method="post" name="enq">
                                                <input type="text" hidden value="page-account-changepass.jsp"
                                                       name="PageChangePass"/>
                                                <div class="form-group col-md-12">
                                                    <label>Mật khẩu cũ <span class="required">*</span></label>
                                                    <input required="" class="form-control" name="oldPassword"
                                                           type="password" minlength="8"/>
                                                </div>
                                                <div class="form-group col-md-12">
                                                    <label>Mật khẩu mới <span class="required">*</span></label>
                                                    <input required="" class="form-control" name="newPassword"
                                                           type="password" minlength="8"/>
                                                </div>
                                                <div class="form-group col-md-12">
                                                    <label>Xác nhận mật khẩu <span class="required">*</span></label>
                                                    <input required="" class="form-control" name="confirmPassword"
                                                           type="password" minlength="8"/>
                                                </div>

                                                <div class="col-md-12">
                                                    <button type="submit"
                                                            class="btn btn-fill-out submit font-weight-bold"
                                                            name="submit" value="Submit">Lưu thay đổi
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
<!-- Vendor JS-->
<script src="nest-frontend/assets/js/vendor/modernizr-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/jquery-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="nest-frontend/assets/js/plugins/slick.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.syotimer.min.js"></script>
<script src="nest-frontend/assets/js/plugins/wow.js"></script>
<script src="nest-frontend/assets/js/plugins/perfect-scrollbar.js"></script>
<script src="nest-frontend/assets/js/plugins/magnific-popup.js"></script>
<script src="nest-frontend/assets/js/plugins/select2.min.js"></script>
<script src="nest-frontend/assets/js/plugins/waypoints.js"></script>
<script src="nest-frontend/assets/js/plugins/counterup.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.countdown.min.js"></script>
<script src="nest-frontend/assets/js/plugins/images-loaded.js"></script>
<script src="nest-frontend/assets/js/plugins/isotope.js"></script>
<script src="nest-frontend/assets/js/plugins/scrollup.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.vticker-min.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.theia.sticky.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.elevatezoom.js"></script>
<!-- Template  JS -->
<script src="nest-frontend/assets/js/main.js?v=4.0"></script>
<script src="nest-frontend/assets/js/shop.js?v=4.0"></script>
</body>
</html>

