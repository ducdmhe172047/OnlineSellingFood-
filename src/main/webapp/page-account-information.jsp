<%@ page import="model.Account" %>
<%@ page import="model.Customer" %>
<%@ page import="dal.CustomerDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/19/2024
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8" />
    <title>Thương mại điện tử</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta property="og:title" content="" />
    <meta property="og:type" content="" />
    <meta property="og:url" content="" />
    <meta property="og:image" content="" />
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-frontend/assets/imgs/theme/favicon.svg" />
    <!-- Template CSS -->
    <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0" />
</head>

<body>
<%
    Account account = (Account)session.getAttribute("account");
    String accountName = account.getName();
    Customer customer = new CustomerDAO().getCustomerByAccountID(account.getAccountID());
    String birth = account.getBirth().format(DateTimeFormatter.ISO_LOCAL_DATE);
    int genderID = account.getGenderID();
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
                <span></span> Trang <span></span> Tài khoản
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
                                <jsp:param name="page" value="account-detail-tab"/>
                            </jsp:include>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content account dashboard-content pl-50">
                                <div class="tab-pane fade active show" id="account-detail" role="tabpanel" aria-labelledby="account-detail-tab">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5>Thông tin tài khoản</h5>
                                        </div>
                                        <div class="card-body">
                                            <h6>Cấp:&nbsp<%=customer.getLevel()%></h6>
                                            <h6>Điểm:&nbsp<%=customer.getPoint()%></h6>
                                            <form action="profile1" method="post" name="enq">
                                                <div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label>Tên <span class="required">*</span></label>
                                                        <input required="" class="form-control" name="name" type="text"
                                                               value="${account.name}"/>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label>Email <span class="required">*</span></label>
                                                        <input required="" class="form-control" name="email" type="email"
                                                               value="${account.email}"readonly="" />
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label>Ngày tháng năm sinh<span class="required">*</span></label>
                                                        <input class="form-control" name="birth" value="<%=birth%>" type="date"/>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label>Giới tính</label>
                                                        <select class="form-control" name="gender" required>
                                                            <option <%if(genderID==1){out.print("selected");}%> value="1">Nam</option>
                                                            <option <%if(genderID==2){out.print("selected");}%> value="2">Nữ</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-12">
                                                        <button type="submit" class="btn btn-fill-out submit font-weight-bold" name="submit" value="Submit">Lưu thay đổi</button>
                                                    </div>
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

