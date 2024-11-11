
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/8/2024
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<script>
    function selectVoucher( discountPercent, startTime, endTime, quantity) {
        // Update the form fields with the product data

        document.getElementById('discount_percent').value = discountPercent || '';
        document.getElementById('voucher_start_date').value = startTime || '';
        document.getElementById('voucher_end_date').value = endTime || '';
        document.getElementById('voucher_quantity').value = quantity || '';

    }
</script>
<script type="text/javascript">
    function doDelete(productID, discountID) {
        if (confirm("Bạn có muốn xóa phiếu giảm giá với sản phẩm này không ?")) {
            window.location = "deleteDiscount?productID=" + productID + "&discountID=" + discountID;
        }
    }
</script>
<script>
    function validateForm() {
        const mfgDate = new Date(document.getElementById("voucher_start_date").value);
        const expDate = new Date(document.getElementById("voucher_end_date").value);

        if (expDate < mfgDate) {
            alert("Ngày hết hạn không được trước ngày tạo!");
            return false;
        }
        return true;
    }
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");

        form.addEventListener("submit", function(event) {
            if (!validateForm()) {
                event.preventDefault();
            }
        });
    });
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Nest Dashboard</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta property="og:title" content=""/>
    <meta property="og:type" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:image" content=""/>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg"/>
    <!-- Template CSS -->
    <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="voucher"/>
    <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Phiếu giảm giá sản phẩm</h2>
                <p>Thêm,sửa và thêm mã giảm giá cho khách hàng</p>
            </div>



        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <form method="post" action="voucher">
                            <%--                            <div class="mb-4">--%>
                            <%--                                <label for="product_name" class="form-label">Name</label>--%>
                            <%--                                <input type="text" placeholder="Type here" class="form-control" id="product_name" />--%>
                            <%--                            </div>--%>


                            <div class="mb-4">
                                <label for="discount_percent" class="form-label">Phần trăm giảm giá(%)</label>
                                <input type="number" class="form-control" id="discount_percent" name="discountPercent"
                                       min="1" max="100" required=""/>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">Ngày bắt đầu</label>
                                <input type="datetime-local" class="form-control" id="voucher_start_date"
                                       name="startDate"  required=""/>
                            </div>
                            <div class="mb-4">
                                <label class="form-label">Ngày kết thúc</label>
                                <input type="datetime-local" class="form-control" id="voucher_end_date" name="endDate"  required=""/>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">Số lượng</label>
                                <input type="number" class="form-control" id="voucher_quantity" name="quantity" min="1" required=""/>
                            </div>

                            <div class="d-grid">
                                <button class="btn btn-primary" type="submit">Tạo phiếu giảm giá</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Số thứ tự</th>
                                    <th>Phần trăm giảm giá</th>
                                    <th>Ngày bắt đầu</th>
                                    <th>Ngày kết thúc</th>
                                    <th>Số lượng</th>
                                    <th>Còn lại</th>
                                    <th  style="text-align: center;">Hoạt động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="count" value="${startCount}"/>
                                <c:forEach var="v" items="${voucher}">

                                    <tr onclick="selectVoucher( '${v.discountPercent}', '${v.startTime}', '${v.endTime}','${v.quantity}')">
                                        <td>${count}</td>
                                        <c:set var="count" value="${count + 1}"/>
                                        <td>${v.discountPercent} %</td>
                                        <td>${v.startTime}</td>
                                        <td>${v.endTime}</td>
                                        <td>${v.quantity}</td>
                                        <td>${v.inventory}</td>
                                        <td>
                                            <div class="d-flex justify-content-center">
                                                <!-- Button Edit -->
                                                <a href="editvoucher?id=${v.voucherID}" class="btn btn-sm font-sm btn-light rounded mx-2">
                                                    <i class="material-icons md-edit_forever"></i> Chỉnh sửa
                                                </a>

                                                <!-- Button Add Customer -->
                                                <a href="customervoucher?id=${v.voucherID}" class="btn btn-sm font-sm btn-light rounded mx-2">
                                                    <i class="material-icons md-add_circle"></i> Thêm khách hàng
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- .col// -->
                </div>
                <!-- .row // -->
            </div>
            <!-- card body .// -->
        </div>
        <div class="pagination-area mt-15 mb-50">
            <nav aria-label="Page navigation example">


                <ul class="pagination justify-content-start">
                    <c:forEach begin="01" end="${endPage}" var="i">
                        <li class="${index==i?"page-item active":""} "><a class="page-link" href="voucher?index=${i}">${i}</a></li>
                    </c:forEach>
                </ul>


            </nav>
        </div>
        <!-- card .// -->
    </section>
    <!-- content-main end// -->
    <footer class="main-footer font-xs">
        <div class="row pb-30 pt-15">
            <div class="col-sm-6">
                <script>
                    document.write(new Date().getFullYear());
                </script>
            </div>
            <div class="col-sm-6">
            </div>
        </div>
    </footer>
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

