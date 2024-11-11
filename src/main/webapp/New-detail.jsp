<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Account" %>
<%@ page import="common.Host" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>News</title>
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
    <link rel="stylesheet" href="nest-frontend/assets/css/plugins/animate.min.css" />
    <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0" />
</head>
<body>
<%
    String accountName;
    try {
        accountName = ((Account) session.getAttribute("account")).getName();
    } catch (NullPointerException e) {
        accountName = "";
    }
%>
<jsp:include page="header.jsp">
    <jsp:param name="accountName" value="<%=accountName%>"/>
</jsp:include>
<!--End header-->
<main class="main">
    <div class="page-header breadcrumb-wrap">
        <div class="container">
            <div class="breadcrumb">
                <a href="home-page.jsp" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a>
                <span></span> <a href="news">News</a> <span></span> ${news.title}
            </div>
        </div>
    </div>
    <div class="page-content mb-50">
        <div class="container">
            <div class="row">
                <div class="col-xl-11 col-lg-12 m-auto">
                    <div class="row">
                        <div class="col-lg-9">
                            <div class="single-page pt-50 pr-30">
                                <div class="single-header style-2">
                                    <div class="row">
                                        <div class="col-xl-10 col-lg-12 m-auto">
                                            <h2 class="mb-10">${news.title}</h2>
                                            <div class="single-header-meta">
                                                <div class="entry-meta meta-1 font-xs mt-15 mb-15">
                                                    <span class="post-on has-dot">${news.time}</span>
                                                </div>
                                                <div class="social-icons single-share">
                                                    <ul class="text-grey-5 d-inline-block">
                                                        <li class="mr-5">
                                                            <a href=""><img src="assets/imgs/theme/icons/icon-bookmark.svg" alt="" /></a>
                                                        </li>
                                                        <li>
                                                            <a href=""><img src="assets/imgs/theme/icons/icon-heart-2.svg" alt="" /></a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <img src="<%=Host.IMG_LINK%>${img}?raw=true" style="max-height: 200px;min-height: 200px">
                                        </div>
                                    </div>
                                </div>
                                <figure class="single-thumbnail">
                                    <img src="" alt="" />
                                </figure>
                                <div class="single-content">
                                    <div class="row">
                                        <div class="col-xl-10 col-lg-12 m-auto">
                                            <p class="single-excerpt">${news.content}</p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Entry bottom -->
                                <div class="entry-bottom mt-50 mb-30">
                                    <div class="social-icons single-share">
                                        <ul class="text-grey-5 d-inline-block">
                                            <li><strong class="mr-10">Share this:</strong></li>
                                            <li class="social-facebook">
                                                <a href="#"><img src="assets/imgs/theme/icons/icon-facebook.svg" alt="" /></a>
                                            </li>
                                            <li class="social-twitter">
                                                <a href="#"><img src="assets/imgs/theme/icons/icon-twitter.svg" alt="" /></a>
                                            </li>
                                            <li class="social-instagram">
                                                <a href="#"><img src="assets/imgs/theme/icons/icon-instagram.svg" alt="" /></a>
                                            </li>
                                            <li class="social-linkedin">
                                                <a href="#"><img src="assets/imgs/theme/icons/icon-pinterest.svg" alt="" /></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 primary-sidebar sticky-sidebar pt-50">
                            <div class="widget-area">
                                <div class="sidebar-widget widget-category-2 mb-50">
                                    <h5 class="section-title style-1 mb-30">Danh mục sản phẩm</h5>
                                    <ul>
                                        <c:forEach var="category" items="${categories}">
                                            <li>
                                                <a href="shop-grid-left.jsp?categoryID=${category.categoryID}">
                                                    <img src="assets/imgs/theme/icons/category-5.svg" alt="" />${category.name}
                                                </a>
                                                <!-- Retrieve the product count from the map based on category ID -->
                                                <span class="count">${categoryProductCounts[category.categoryID]}</span>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <!-- Product sidebar Widget -->
                                <div class="sidebar-widget product-sidebar mb-50 p-30 bg-grey border-radius-10">
                                    <h5 class="section-title style-1 mb-30">Sản phẩm phổ biến</h5>
                                    <c:forEach var="product" items="${popularProducts}">
                                        <div class="single-post clearfix">
                                            <div class="content pt-10">
                                                <h5><a href="ProductDetail?productID=${product.productID}">${product.name}</a></h5>
                                                <p class="price mb-0 mt-5">${product.price} VND</p>
                                                <div class="product-rate">
                                                    <div class="product-rating" style="width: 90%"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>
<!-- End Footer -->
<script src="nest-frontend/assets/js/vendor/jquery-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/waypoints.min.js"></script>
<script src="nest-frontend/assets/js/vendor/nice-select/jquery.nice-select.min.js"></script>
<script src="nest-frontend/assets/js/vendor/swiper-bundle.min.js"></script>
<script src="nest-frontend/assets/js/main.js?v=4.0"></script>
</body>
</html>
