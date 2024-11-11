<%@ page import="java.util.List" %>
<%@ page import="dto.ImportRespone" %>
<%@ page import="model.Warehouse" %>
<%@ page import="model.Supplier" %>
<%@ page import="model.Product" %>
<%@ page import="model.Unit" %>
<%@ page import="dal.UnitDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>

    function validateForm() {
        const mfgDate = new Date(document.getElementById("mfg").value);
        const expDate = new Date(document.getElementById("exp").value);

        if (expDate < mfgDate) {
            alert("Ngày hết hạn không được trước ngày sản xuất.");
            return false;
        }
        return true;
    }

    function doDelete(importID) {

        if (confirm("Bạn có muốn xóa sản phẩm ?")) {
            window.location = "importProductDelete?importID=" + importID ;
        }
    }

    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector("form");

        form.addEventListener("submit", function(event) {
            if (!validateForm()) {
                event.preventDefault();
            }
        });
    });

    document.addEventListener("DOMContentLoaded", function() {
        const urlParams = new URLSearchParams(window.location.search);
        const importId = urlParams.get("id");

        if (importId) {
            document.getElementById("importId").value = importId;
        }
    });
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
                <h2 class="content-title card-title">Nhập sản phẩm</h2>

            </div>
            <div class="mt-3">
                <a href="Import" class="btn btn-secondary">Quay lại</a>
            </div>
            <div>


            </div>
        </div>
        <div class="card">
            <div class="card-body">

                <div class="row">
                    <div class="col-md-3">
                        <form action="ImportProduct" method="post">

                            <div class="mb-4">
                                <label for="importId" class="form-label">ID Nhập</label>
                                <input type="number" class="form-control" id="importId" name="importId" readonly required />

                            </div>
                            <div class="mb-4">
                                <label for="productID" class="form-label">Sản phẩm</label>
                                <select class="form-control" id="productID" name="productID" required>
                                    <%
                                        List<Product> productList = (List<Product>) request.getAttribute("products");
                                        if (productList != null) {
                                            for (Product pr : productList) {
                                    %>
                                    <option value="<%= pr.getProductID() %>"><%= pr.getName() %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="mb-4">
                                <label for="mfg" class="form-label">Ngày sản xuất</label>
                                <input type="date" class="form-control" id="mfg" name="mfg" required />
                            </div>
                            <div class="mb-4">
                                <label for="exp" class="form-label">Ngày hết hạn</label>
                                <input type="date" class="form-control" id="exp" name="exp" required />
                            </div>
                            <div class="mb-4">
                                <label for="price" class="form-label">Giá</label>
                                <input type="number" class="form-control" id="price" name="price" min="1" required />
                            </div>
                            <div class="mb-4">
                                <label for="quantity" class="form-label">Số lượng nhập</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" min="1" required />
                            </div>
                            <div class="mb-4">
                                <label for="inventory" class="form-label">Số lượng hàng tồn kho</label>
                                <input type="number" class="form-control" id="inventory" name="inventory" min="0" required />
                            </div>
                            <div class="mb-4">
                                <label for="unitID" class="form-label">Đơn vị</label>
                                <select class="form-control" id="unitID" name="unitID" required>
                                    <%
                                        List<Unit> unitList = (List<Unit>) request.getAttribute("units");
                                        if (unitList != null) {
                                            for (Unit un : unitList) {
                                    %>
                                    <option value="<%= un.getUnitID() %>"><%= un.getName() %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="d-grid">

                                <button type="submit" class="btn btn-primary">Nhập sản phẩm</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">

                            <table class="table table-hover">
                                <thead>
                                <tr>

                                    <th>Tên sản phẩm</th>
                                    <th>Ngày sản xuất</th>
                                    <th>Ngày hết hạn</th>
                                    <th>Giá</th>
                                    <th>Số lượng nhập</th>
                                    <th>Số lượng hàng tồn kho</th>
                                    <th>Đơn vị</th>
                                    <th class="text-end">Hoạt động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="imp" items="${list}">
                                    <tr>

                                        <td>${imp.getProductName()}</td>
                                        <td>${imp.getManufactureDate()}</td>
                                        <td>${imp.getExpireDate()}</td>
                                        <td>${imp.getPrice()}</td>
                                        <td>${imp.getImportQuantity()}</td>
                                        <td>${imp.getInventoryQuantity()}</td>
                                        <td>${imp.getUnitName()}</td>
                                        <td class="text-end">

                                            <a href="#" onclick="doDelete('${imp.getImportID()}')" class="btn btn-light rounded btn-sm font-sm">
                                                <i class="material-icons md-delete"></i> Xóa
                                            </a>
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
    </section>
    <!-- content-main end// -->
    <footer class="main-footer font-xs">
        <div class="row pb-30 pt-15">
            <div class="col-sm-6">
                <script>
                    document.write(new Date().getFullYear());
                </script>
                &copy; Nest - HTML Ecommerce Template .
            </div>
            <div class="col-sm-6">
                <div class="text-sm-end">All rights reserved</div>
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