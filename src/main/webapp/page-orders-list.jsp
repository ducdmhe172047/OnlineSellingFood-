<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <jsp:param name="page" value="order"/>
    <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Danh sách khách hàng đặt hàng</h2>
                <p>Xem chi tiết,chỉnh sửa và xóa </p>
            </div>

        </div>
        <div class="card mb-4">
            <header class="card-header">
                <div class="row gx-3">
                    <div class="col-lg-4 col-md-6 me-auto">
                        <form action="customerOrder" method="GET" style="display: flex;">
                            <input type="text" name="searchName" placeholder="Tìm kiếm theo tên..." class="form-control"
                                   value="${searchName}" style="flex: 1;"/>
                            <button type="submit" class="btn btn-primary" style="margin-left: 10px;">Tìm kiếm</button>
                        </form>

                    </div>

                    <div class="col-lg-2 col-6 col-md-3">
                        <form action="customerOrder" method="GET">
                        <select class="form-select" name="statusID" onchange="this.form.submit()">
                            <option value="1" ${statusID == 1 ? 'selected' : ''}>Wait for confirmation</option>
                            <option value="2" ${statusID == 2 ? 'selected' : ''}>Confirmation</option>
                            <option value="3" ${statusID == 3 ? 'selected' : ''}>Being delivered</option>
                            <option value="4" ${statusID == 4 ? 'selected' : ''}>Completed</option>
                            <option value="5" ${statusID == 5 ? 'selected' : ''}>Canceled</option>
                            <option value="6" ${statusID == 6 ? 'selected' : ''}>Failed</option>
                        </select>
                        </form>
                    </div>

                    <!-- Thêm một select để chọn sắp xếp theo giá -->
                    <div class="col-lg-2 col-6 col-md-3">
                        <form action="customerOrder" method="GET">
                            <select class="form-select" name="sortPrice" onchange="this.form.submit()">
                                <option value="asc" ${sortPrice == 'asc' ? 'selected' : ''}>Giá tăng dần</option>
                                <option value="desc" ${sortPrice == 'desc' ? 'selected' : ''}>Giá giảm dần</option>
                            </select>
                        </form>
                    </div>
                </div>
            </header>
            <!-- card-header end// -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Email</th>
                            <th scope="col">Tổng tiền</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Ngày đặt hàng</th>
                            <th scope="col" class="text-end">Hoạt động</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="count" value="${startCount}"/>
                        <c:forEach items="${customerOrderList}" var="co">
                            <tr>
                                <td>${count}</td>
                                <c:set var="count" value="${count + 1}"/>
                                <td><b>${co.customerName}</b></td>
                                <td>${co.email}</td>
                                <td>${co.price}VND</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${co.statusID == 2}">
                                            <span class="badge rounded-pill alert-success">${co.statusDetail}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge rounded-pill alert-warning">${co.statusDetail}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${co.orderTime}</td>
                                <td class="text-end">
                                    <form action="viewOrderDetail" method="post" style="display:inline;">
                                        <input type="hidden" name="orderID" value="${co.orderID}"/>
                                        <input type="hidden" name="customerName" value="${co.customerName}"/>
                                        <input type="hidden" name="email" value="${co.email}"/>
                                        <input type="hidden" name="price" value="${co.price}"/>
                                        <input type="hidden" name="phone" value="${co.phoneNumber}"/>
                                        <input type="hidden" name="address" value="${co.address}"/>
                                        <input type="hidden" name="statusDetail" value="${co.statusDetail}"/>
                                        <input type="hidden" name="orderTime" value="${co.orderTime}"/>
                                        <input type="hidden" name="statusID" value="${co.statusID}"/>

                                        <button type="submit" class="btn btn-md rounded font-sm">Xem chi tiết</button>
                                    </form>
<%--                                    <div class="dropdown">--%>
<%--                                        <a href="#" data-bs-toggle="dropdown"--%>
<%--                                           class="btn btn-light rounded btn-sm font-sm"> <i--%>
<%--                                                class="material-icons md-more_horiz"></i> </a>--%>
<%--                                        <div class="dropdown-menu">--%>
<%--                                            <a class="dropdown-item" href="#">View detail</a>--%>
<%--                                            <a class="dropdown-item" href="#">Edit info</a>--%>
<%--                                            <a class="dropdown-item text-danger" href="#">Delete</a>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                    <!-- dropdown //end -->
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- table-responsive //end -->
            </div>
            <!-- card-body end// -->
        </div>
        <!-- card end// -->
        <div class="pagination-area mt-15 mb-50">
            <nav aria-label="Page navigation example">


                <ul class="pagination justify-content-start">
                    <c:forEach begin="1" end="${endPage}" var="i">
                        <li class="${index == i ? 'page-item active' : ''}">
                            <a class="page-link" href="customerOrder?index=${i}&statusID=${statusID}&searchName=${searchName}&sortPrice=${sortPrice}">${i}</a>
                        </li>
                    </c:forEach>
                </ul>


            </nav>
        </div>

    </section>
    <!-- content-main end// -->
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
