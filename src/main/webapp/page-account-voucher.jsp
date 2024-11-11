<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.VoucherResponse" %>
<%@ page import="model.Account" %>
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
    String accountName =  ((Account)session.getAttribute("account")).getName();
    String msg = (String)request.getSession().getAttribute("msg");
    request.getSession().removeAttribute("msg");
    if(msg==null) msg="";
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
    <div class="page-content pt-150 pb-150">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 m-auto">
                    <div class="row">
                        <div class="col-md-3">
                            <jsp:include page="account-menu.jsp">
                                <jsp:param name="page" value="voucher-tab"/>
                            </jsp:include>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content account dashboard-content pl-50">
                                <div class="tab-pane fade active show" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                                    <div class="card">
                                        <div class="card-header">
                                            <h3 class="mb-0">Mã giảm giá</h3>
                                            <h5 style="color: red"><%=msg%></h5>
                                            <form action="LoadVoucher" method="post">
                                                <input required class="form-control" name="voucherID" type="number" placeholder="Thêm mã giảm giá mới" min="1"/>
                                                <button type="submit" class="btn btn-fill-out submit font-weight-bold">Thêm</button>
                                            </form>
                                            <dev>
                                                <select id="searchType" class="form-control bg-white">
                                                    <option value="0">ID</option>
                                                    <option value="1">Giảm</option>
                                                </select>
                                                <input type="text" id="searchID" placeholder="Tìm kiếm" class="form-control mt-3" onkeyup="myFunction()"/>
                                            </dev>

                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th><a href="LoadVoucher?sort=0">ID</a></th>
                                                        <th><a href="LoadVoucher?sort=1">Giảm</a></th>
                                                        <th><a href="LoadVoucher?sort=2">Bắt đầu</a></th>
                                                        <th><a href="LoadVoucher?sort=3">Hết hạn</a></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%
                                                        List<VoucherResponse> vouchers = (List<VoucherResponse>)request.getAttribute("vouchers");
                                                        if(vouchers!=null)
                                                        for(VoucherResponse voucher : vouchers){
                                                    %>
                                                    <tr>
                                                        <th><%=voucher.getDiscountID()%></th>
                                                        <th><%=voucher.getDiscountPercent()%>%</th>
                                                        <th><%=voucher.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))%></th>
                                                        <th><%=voucher.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))%></th>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                    </tbody>
                                                </table>
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
    </div>
    </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
<script>
    function myFunction() {
        // Declare variables
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("searchID");
        filter = input.value.toUpperCase();
        table = document.querySelector(".table tbody");
        tr = table.getElementsByTagName("tr");
        searchType = document.getElementById("searchType").value;

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("th")[searchType]; // Assuming the ID is in the first column
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
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

