<%--
  Created by IntelliJ IDEA.
  User: ducdx
  Date: 11/4/2024
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Account" %>
<%@ page import="common.Host" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>New</title>
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
    <div class="page-header mt-30 mb-75">
        <div class="container">
            <div class="archive-header">
                <div class="row align-items-center">
                    <div class="col-xl-3">
                        <h1 class="mb-15">Blog & News</h1>
                        <div class="breadcrumb">
                            <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a>
                            <span></span> Blog & News
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page-content mb-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-9">
                    <div class="shop-product-fillter mb-50 pr-30">
                        <div class="totall-product">
                        </div>
                        <div class="sort-by-product-area">
                            <div class="sort-by-cover mr-10">
<%--                                <div class="sort-by-product-wrap">--%>
<%--                                    <div class="sort-by">--%>
<%--                                        <span><i class="fi-rs-apps"></i>Show:</span>--%>
<%--                                    </div>--%>
<%--                                    <div class="sort-by-dropdown-wrap">--%>
<%--                                        <span> 50 <i class="fi-rs-angle-small-down"></i></span>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="sort-by-dropdown">--%>
<%--                                    <ul>--%>
<%--                                        <li><a class="active" href="#">50</a></li>--%>
<%--                                        <li><a href="#">100</a></li>--%>
<%--                                        <li><a href="#">150</a></li>--%>
<%--                                        <li><a href="#">200</a></li>--%>
<%--                                        <li><a href="#">All</a></li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
                            </div>
<%--                            <div class="sort-by-cover">--%>
<%--                                <div class="sort-by-product-wrap">--%>
<%--                                    <div class="sort-by">--%>
<%--                                        <span><i class="fi-rs-apps-sort"></i>Sort:</span>--%>
<%--                                    </div>--%>
<%--                                    <div class="sort-by-dropdown-wrap">--%>
<%--                                        <span>Featured <i class="fi-rs-angle-small-down"></i></span>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="sort-by-dropdown">--%>
<%--                                    <ul>--%>
<%--                                        <li><a class="active" href="#">Featured</a></li>--%>
<%--                                        <li><a href="#">Newest</a></li>--%>
<%--                                        <li><a href="#">Most comments</a></li>--%>
<%--                                        <li><a href="#">Release Date</a></li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                    <div class="loop-grid loop-list pr-30 mb-50">
                        <c:forEach items="${list}" var="n">
                            <article class="wow fadeIn animated hover-up mb-30 animated">
                                <div class="post-thumb" style="width: 438px; height: 366px;">

                                </div>
                                <div class="entry-content-2 pl-50">
                                    <h3 class="post-title mb-20">
                                        <a href="new-detail?newsId=${n.newsID}">${n.title}</a>
                                    </h3>
                                    <p class="post-exerpt mb-40">
                                        <c:choose>
                                            <c:when test="${n.content.length() > 100}">
                                                ${n.content.substring(0, 100)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${n.content}
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                    <div class="entry-meta meta-1 font-xs color-grey mt-10 pb-10">
                                        <a href="new-detail?newsId=${n.newsID}" class="text-brand font-heading font-weight-bold">
                                            Đọc <i class="fi-rs-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </article>
                        </c:forEach>
                    </div>
                    <div class="pagination-area mt-15 mb-sm-5 mb-lg-0">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-start">
                                <li class="page-item">
                                    <a class="page-link" href="#"><i class="fi-rs-arrow-small-left"></i></a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
<%--                                <li class="page-item active"><a class="page-link" href="#">2</a></li>--%>
<%--                                <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--                                <li class="page-item"><a class="page-link dot" href="#">...</a></li>--%>
<%--                                <li class="page-item"><a class="page-link" href="#">6</a></li>--%>
                                <li class="page-item">
                                    <a class="page-link" href="#"><i class="fi-rs-arrow-small-right"></i></a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-lg-3 primary-sidebar sticky-sidebar">
                    <div class="widget-area">
                        <div class="sidebar-widget-2 widget_search mb-50">
                            <div class="search-form">
                                <form action="news" method="get">
                                    <input type="text" name="search" placeholder="Search…" value="${param.search}" />
                                    <button type="submit"><i class="fi-rs-search"></i></button>
                                </form>
                            </div>
                        </div>
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

                        <div class="sidebar-widget widget_instagram mb-50">
<%--                            <h5 class="section-title style-1 mb-30">Gallery</h5>--%>
<%--                            <div class="instagram-gellay">--%>
<%--                                <ul class="insta-feed">--%>
<%--                                    <li>--%>
<%--                                        <a href="#"><img class="border-radius-5" src="assets/imgs/shop/thumbnail-1.jpg" alt="" /></a>--%>
<%--                                    </li>--%>
<%--                                    <li>--%>
<%--                                        <a href="#"><img class="border-radius-5" src="assets/imgs/shop/thumbnail-2.jpg" alt="" /></a>--%>
<%--                                    </li>--%>
<%--                                    <li>--%>
<%--                                        <a href="#"><img class="border-radius-5" src="assets/imgs/shop/thumbnail-3.jpg" alt="" /></a>--%>
<%--                                    </li>--%>
<%--                                    <li>--%>
<%--                                        <a href="#"><img class="border-radius-5" src="assets/imgs/shop/thumbnail-4.jpg" alt="" /></a>--%>
<%--                                    </li>--%>
<%--                                    <li>--%>
<%--                                        <a href="#"><img class="border-radius-5" src="assets/imgs/shop/thumbnail-5.jpg" alt="" /></a>--%>
<%--                                    </li>--%>
<%--                                    <li>--%>
<%--                                        <a href="#"><img class="border-radius-5" src="assets/imgs/shop/thumbnail-6.jpg" alt="" /></a>--%>
<%--                                    </li>--%>
<%--                                </ul>--%>
<%--                            </div>--%>
                        </div>
                        <!--Tags-->
                        <div class="sidebar-widget widget-tags mb-50 pb-10">
<%--                            <h5 class="section-title style-1 mb-30">Popular Tags</h5>--%>
<%--                            <ul class="tags-list">--%>
<%--                                <li class="hover-up">--%>
<%--                                    <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>Cabbage</a>--%>
<%--                                </li>--%>
<%--                                <li class="hover-up">--%>
<%--                                    <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>Broccoli</a>--%>
<%--                                </li>--%>
<%--                                <li class="hover-up">--%>
<%--                                    <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>Smoothie</a>--%>
<%--                                </li>--%>
<%--                                <li class="hover-up">--%>
<%--                                    <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>Fruit</a>--%>
<%--                                </li>--%>
<%--                                <li class="hover-up mr-0">--%>
<%--                                    <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>Salad</a>--%>
<%--                                </li>--%>
<%--                                <li class="hover-up mr-0">--%>
<%--                                    <a href="blog-category-grid.html"><i class="fi-rs-cross mr-10"></i>Appetizer</a>--%>
<%--                                </li>--%>
<%--                            </ul>--%>
                        </div>
                        <div class="banner-img wow fadeIn mb-50 animated d-lg-block d-none">
<%--                            <img src="assets/imgs/banner/banner-11.png" alt="" />--%>
<%--                            <div class="banner-text">--%>
<%--                                <span>Oganic</span>--%>
<%--                                <h4>--%>
<%--                                    Save 17% <br />--%>
<%--                                    on <span class="text-brand">Oganic</span><br />--%>
<%--                                    Juice--%>
<%--                                </h4>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
<!-- Vendor JS-->
<script src="assets/js/vendor/modernizr-3.6.0.min.js"></script>
<script src="assets/js/vendor/jquery-3.6.0.min.js"></script>
<script src="assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="assets/js/plugins/slick.js"></script>
<script src="assets/js/plugins/jquery.syotimer.min.js"></script>
<script src="assets/js/plugins/wow.js"></script>
<script src="assets/js/plugins/jquery-ui.js"></script>
<script src="assets/js/plugins/perfect-scrollbar.js"></script>
<script src="assets/js/plugins/magnific-popup.js"></script>
<script src="assets/js/plugins/select2.min.js"></script>
<script src="assets/js/plugins/waypoints.js"></script>
<script src="assets/js/plugins/counterup.js"></script>
<script src="assets/js/plugins/jquery.countdown.min.js"></script>
<script src="assets/js/plugins/images-loaded.js"></script>
<script src="assets/js/plugins/isotope.js"></script>
<script src="assets/js/plugins/scrollup.js"></script>
<script src="assets/js/plugins/jquery.vticker-min.js"></script>
<script src="assets/js/plugins/jquery.theia.sticky.js"></script>
<script src="assets/js/plugins/jquery.elevatezoom.js"></script>
<!-- Template  JS -->
<script src="./assets/js/main.js?v=4.0"></script>
<script src="./assets/js/shop.js?v=4.0"></script>
</body>
</html>
