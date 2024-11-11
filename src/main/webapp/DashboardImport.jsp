<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dto.OrderResponse" %>
<%@ page import="java.util.List" %>
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
    <jsp:param name="menu" value="homepage"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Bảng điều khiển</h2>
                <p>Toàn bộ dữ liệu về doanh nghiệp của bạn ở đây</p>
            </div>

            <form action="ExportImport" method="get">

                <button type="submit" class="btn btn-primary">
                    <i  class="text-muted material-icons md-post_add"></i> Tạo báo cáo
                </button>
            </form>
        </div>
        <div class="row">

            <div class="col-lg-3">
                <div class="card card-body mb-4">
                    <article class="icontext">
                        <span class="icon icon-sm rounded-circle bg-success-light"><i class="text-success material-icons md-local_shipping"></i></span>
                        <div class="text">
                            <h6 class="mb-1 card-title">Số lượng nhà cung cấp</h6>
                            <span>${totalSupplier}</span>

                        </div>
                    </article>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card card-body mb-4">
                    <article class="icontext">
                        <span class="icon icon-sm rounded-circle bg-warning-light"><i class="text-warning material-icons md-qr_code"></i></span>
                        <div class="text">
                            <h6 class="mb-1 card-title">Số lượng kho</h6>
                            <span>${totalWarehouse}</span>

                        </div>
                    </article>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-xl-8 col-lg-12">
                <div class="card mb-4">

                </div>
                <div class="row">
                    <div class="col-lg-5">
                        <div class="card mb-4">

                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="card mb-4">

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-4 col-lg-12">
                <div class="card mb-4">

                </div>
                <div class="card mb-4">

                </div>
            </div>
        </div>
        <div class="card mb-4">
            <div class="search-bar">
                <form action="DashboardI" method="get" class="input-group">
                    <input type="text" name="search" class="form-control" placeholder="Tìm kiếm" value="${param.search}">
                    <input type="hidden" name="index" value="1">
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <div class="table-responsive">
                        <table class="table align-middle table-nowrap mb-0">
                            <thead class="table-light">
                            <tr>

                                <th class="align-middle" scope="col">Mã nhập</th>
                                <th class="align-middle" scope="col">Tên sản phẩm</th>
                                <th class="align-middle" scope="col">Ngày sản xuất</th>
                                <th class="align-middle" scope="col">Ngày hết hạn</th>
                                <th class="align-middle" scope="col" style="color: black;" >
                                    <a href="DashboardI?search=${param.search}&index=${param.index}&sortBy=price&sortOrder=${param.sortOrder == 'asc' ? 'desc' : 'asc'}" style="color: black;">
                                        Giá
                                        <c:choose>
                                            <c:when test="${param.sortBy == 'price' && param.sortOrder == 'asc'}">
                                                <i class="material-icons"></i>
                                            </c:when>
                                            <c:when test="${param.sortBy == 'price' && param.sortOrder == 'desc'}">
                                                <i class="material-icons"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="material-icons"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </th>
                                <th class="align-middle" scope="col" style="color: black;" >
                                    <a href="DashboardI?search=${param.search}&index=${param.index}&sortBy=quantity&sortOrder=${param.sortOrder == 'asc' ? 'desc' : 'asc'}" style="color: black;">
                                        Số lượng nhập
                                        <c:choose>
                                            <c:when test="${param.sortBy == 'quantity' && param.sortOrder == 'asc'}">
                                                <i class="material-icons"></i>
                                            </c:when>
                                            <c:when test="${param.sortBy == 'quantity' && param.sortOrder == 'desc'}">
                                                <i class="material-icons"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="material-icons"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </th>
                                <th class="align-middle" scope="col" style="color: black;" >
                                    <a href="DashboardI?search=${param.search}&index=${param.index}&sortBy=inventory&sortOrder=${param.sortOrder == 'asc' ? 'desc' : 'asc'}" style="color: black;">
                                        Số lượng hàng tồn kho
                                        <c:choose>
                                            <c:when test="${param.sortBy == 'inventory' && param.sortOrder == 'asc'}">
                                                <i class="material-icons"></i>
                                            </c:when>
                                            <c:when test="${param.sortBy == 'inventory' && param.sortOrder == 'desc'}">
                                                <i class="material-icons"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="material-icons"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </th>
                                <th class="align-middle" scope="col">Đơn vị</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="imp" items="${importList}">
                                <tr>
                                    <td>${imp.getImportID()}t</td>
                                    <td>${imp.getProductName()}</td>
                                    <td>${imp.getManufactureDate()}</td>
                                    <td>${imp.getExpireDate()}</td>
                                    <td>${imp.getPrice()} VND</td>
                                    <td>${imp.getImportQuantity()}</td>
                                    <td>${imp.getInventoryQuantity()}</td>
                                    <td>${imp.getUnitName()}</td>

                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <div class="pagination-container">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <li class="page-item ${i == param.index ? 'active' : ''}">
                                            <a class="page-link" href="DashboardI?index=${i}&search=${param.search}">${i}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- table-responsive end// -->
            </div>
        </div>

    </section>
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
