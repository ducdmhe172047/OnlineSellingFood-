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
    function selectProduct(productID, name, discountPercent, startTime, endTime) {
        // Update the form fields with the product data
        document.getElementById('product_id').value=productID || '';
        document.getElementById('product_discount').value = discountPercent || '';
        document.getElementById('start_date').value = startTime || '';
        document.getElementById('end_date').value = endTime || '';
    }
</script>
<script type="text/javascript">
    function doDelete(productID,discountID) {
        if (confirm("Bạn có muốn xóa mã giảm giá của sản phẩm này không?")) {
            window.location = "deleteDiscount?productID=" + productID + "&discountID=" +discountID;
        }
    }
</script>


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
    <jsp:param name="page" value="discount"/>
    <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Giảm giá sản phẩm</h2>
                <p>Thêm,sửa và xóa giảm giá</p>
                <h4 style="color: red">${msg}</h4>
            </div>

            <div>
                <form action="discount" method="get" >
                    <input type="text" placeholder="Tìm kiếm theo tên" name="searchName"  class="form-control bg-white"  value="${searchName}" style="flex: 1;" />
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>

            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <form id="create" method="post" action="discount" onsubmit="return validateForm()">
<%--                            <div class="mb-4">--%>
<%--                                <label for="product_name" class="form-label">Name</label>--%>
<%--                                <input type="text" placeholder="Type here" class="form-control" id="product_name" />--%>
<%--                            </div>--%>
                            <div class="mb-4">
                                <label for="product_discount" class="form-label">Phần trăm giảm giá(%)</label>
                                <input type="number"  class="form-control" id="product_discount" name="discountPercent" min="1" max="100" required=""/>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">Ngày bắt đầu</label>
                                <input type="datetime-local"  class="form-control" id="start_date" name="startDate" required="" />
                            </div>
                            <div class="mb-4">
                                <label class="form-label">Ngày kết thúc</label>
                                <input type="datetime-local"  class="form-control" id="end_date" name="endDate" required=""/>
                            </div>

                            <div class="mb-4">
                                <label class="form-label">ID sản phẩm</label>
                                <input type="number"  class="form-control" id="product_id" name="productID" readonly=""/>
                            </div>
                            <div class="d-grid">
                                <button class="btn btn-primary" type="submit">Tạo giảm giá</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>

                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th> Phân loại</th>
                                    <th>Giá</th>
                                    <th>Giảm giá</th>
                                    <th>Giá sau khi giảm</th>
                                    <th>Ngày bắt đầu</th>
                                    <th>Ngày kết thúc</th>
                                    <th>Hoạt động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="d" items="${productDiscount}">


                                    <tr onclick="selectProduct('${d.productID}', '${d.name}', '${d.discountPercent}', '${d.startTime}', '${d.endTime}')">
                                    <td>${d.productID}</td>
                                    <td>${d.name}</td>
                                    <td>${d.categoryName}</td>
                                    <td>${d.price}</td>
                                    <td>${d.discountPercent} %</td>
                                    <td>${(d.price-(d.price*(d.discountPercent/100)))}</td>
                                    <td>${d.startTime}</td>
                                    <td>${d.endTime}</td>
                                    <td>
                                    <div class="col-lg-2 col-sm-2 col-4 col-action text-end">
                                        <a href="#" on onclick="doDelete('${d.productID}','${d.discountID}')" class="btn btn-sm font-sm btn-light rounded"> <i class="material-icons md-delete_forever"></i> Xóa </a>
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
        <!-- card .// -->
        <div class="pagination-area mt-15 mb-50">
            <nav aria-label="Page navigation example">


                <ul class="pagination justify-content-start">
                    <c:forEach begin="01" end="${endPage}" var="i">
                        <li class="${index==i?"page-item active":""} "><a class="page-link" href="discount?index=${i}&searchName=${searchName}">${i}</a></li>
                    </c:forEach>
                </ul>


            </nav>
        </div>
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
<script>
    function validateForm() {
        const startDate = document.getElementById("start_date").value;
        const endDate = document.getElementById("end_date").value;

        if (!startDate || !endDate) {
            alert("Ngày bắt đầu và ngày kết thúc không được để trống!");
            return false;
        }

        const mfgDate = new Date(startDate);
        const expDate = new Date(endDate);

        if (expDate < mfgDate) {
            alert("Ngày hết hạn không được trước ngày bắt đầu!");
            return false;
        }
        return true;
    }

    document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById("create");

        form.addEventListener("submit", function(event) {
            if (!validateForm()) {
                event.preventDefault();
            }
        });
    });
</script>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>

