<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Chi tiết</title>
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
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="manulist"/>
    <jsp:param name="menu" value="manufacter"/>
</jsp:include>
<main class="main-wrap">
    <section class="content-main">
        <div class="content-header">
            <a href="javascript:history.back()"><i class="material-icons md-arrow_back"></i> Quay lại</a>
        </div>
        <div class="card mb-4">
            <div class="card-header bg-brand-2" style="height: 150px"></div>
            <div class="card-body">
                <div class="row">
                    <div class="col-xl col-lg flex-grow-0" style="flex-basis: 230px">
                        <div class="img-thumbnail shadow w-100 bg-white position-relative text-center" style="height: 190px; width: 200px; margin-top: -120px">
                            <!-- Logo placeholder -->
                        </div>
                    </div>
                    <div class="col-xl col-lg">
                        <h3>${manuDetail.name}</h3>
                    </div>
                </div>
                <hr class="my-4" />
                <div class="row g-4">
                    <div class="col-md-12 col-lg-4 col-xl-2">
                        <article class="box">
                            <p class="mb-0 text-muted">Tổng số sản phẩm:</p>
                            <h5 class="text-success">${manuDetail.productCount}</h5>
                        </article>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-4">
            <div class="card-body">
                <h3 class="card-title">Sản phẩm của ${manuDetail.name}</h3>
                <div class="row">
                    <c:forEach items="${products}" var="product">
                        <div class="col-xl-2 col-lg-3 col-md-6">
                            <div class="card card-product-grid">
                                <a href="product-detail?id=${product.productID}" class="img-wrap">
<%--                                    <img src="${product.img}" alt="${product.name}" />--%>
                                </a>
                                <div class="info-wrap">
                                    <a href="product-detail?id=${product.productID}" class="title">${product.name}</a>
                                    <div class="price mt-1">$${product.price}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Pagination -->
        <div class="pagination-area mt-30 mb-50">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-start">
                    <li class="page-item active"><a class="page-link" href="#">01</a></li>
                    <li class="page-item"><a class="page-link" href="#">02</a></li>
                    <li class="page-item"><a class="page-link" href="#">03</a></li>
                    <li class="page-item"><a class="page-link dot" href="#">...</a></li>
                    <li class="page-item"><a class="page-link" href="#">16</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#"><i class="material-icons md-chevron_right"></i></a>
                    </li>
                </ul>
            </nav>
        </div>
    </section>
</main>

<!-- Scripts -->
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>