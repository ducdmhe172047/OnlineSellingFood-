<!DOCTYPE html>
<html class="no-js" lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Account" %>
<%@ page import="dal.CustomerDAO" %>
<head>
    <meta charset="utf-8">
    <title>Nest - Multipurpose eCommerce HTML Template</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta property="og:title" content="">
    <meta property="og:type" content="">
    <meta property="og:url" content="">
    <meta property="og:image" content="">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-frontend/assets/imgs/theme/favicon.svg">
    <!-- Template CSS -->
    <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0">
</head>
<
<jsp:include page="header.jsp"></jsp:include>
<%
    String msg = (String)request.getSession().getAttribute("msg");
    if(msg==null) msg = "";
    else request.getSession().removeAttribute("msg");
%>
<body>
<!--End header-->
<main class="main">
    <div class="page-header breadcrumb-wrap">
        <div class="container">
            <div class="breadcrumb">
                <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a>
                <span></span> Sản phẩm
                <span></span> Đặt hàng
            </div>
        </div>
    </div>
    <div class="container mb-80 mt-50">
        <div class="row">
            <div class="col-lg-8 mb-40">
                <h1 class="heading-2 mb-10">Đặt hàng</h1>
                <h6 style="color: red"><%=msg%></h6>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-7">
                <div class="row mb-50">
                    <div class="col-lg-6 mb-sm-15 mb-lg-0 mb-md-3">
                    </div>
                    <div class="col-lg-6">
                        <form action="checkout" method="post" class="apply-coupon">
                            <select name="voucherData" class="coupon-input">
                                <!-- Default option for no selection -->
                                <option value="" ${selectedVoucherID == null ? 'selected' : ''}>Không chọn phiếu giảm
                                    giá
                                </option>

                                <c:forEach items="${listVoucher}" var="lv">
                                    <c:if test="${lv.inventory > 0}">
                                    <option value="${lv.voucherID}" ${lv.voucherID == selectedVoucherID ? 'selected' : ''}>
                                        Giảm giá ${lv.discountPercent}% hết hạn trong ${lv.remainingDay} ngày!
                                    </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="subTotalPrice" value="${subTotalPrice}">

                            <button class="btn btn-md" type="submit" name="apply">Áp dụng</button>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <h4 class="mb-30"> Địa chỉ nhận hàng</h4>
                    <form method="post">
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <input type="text" required="" name="name" value="${c.name}" placeholder="Name*"
                                       readonly="">
                            </div>

                        </div>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <input type="text" name="phone" value="${c.phone}" required="" placeholder="Phone*"
                                       readonly="">
                            </div>

                        </div>

                        <div class="row">
                            <div class="form-group col-lg-6">
                                <input required="" type="text" name="address" value="${c.address}"
                                       placeholder="Address *" readonly="">
                            </div>

                        </div>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <a href="AccountContact" style="color: #3BB77E; font-size:20px ">Bạn có muốn thay đổi
                                    địa chỉ nhận hàng?</a>
                            </div>

                        </div>

                    </form>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="border p-40 cart-totals ml-30 mb-50">
                    <div class="d-flex align-items-end justify-content-between mb-30">
                        <h4>Đơn hàng của bạn</h4>
                        <div>
                            <h6 class="text-muted" style="color: #3BB77E;font-size: 20px">Tổng tiền:${subTotalPrice}
                                VND</h6>
                            <c:if test="${not empty discountedSubtotal}">
                                <h6 class="text-muted" style="color: #3BB77E; font-size: 20px">
                                    Tổng tiền sau khi áp phiếu giảm giá: ${discountedSubtotal} VND
                                </h6>
                            </c:if>
                        </div>


                    </div>
                    <div class="divider-2 mb-30"></div>
                    <div class="table-responsive order_table checkout">
                        <table class="table no-border">
                            <tbody>
                            <c:forEach items="${list}" var="l">
                                <tr>
                                    <td class="image product-thumbnail"><img
                                            src="nest-frontend/assets/imgs/shop/product-1-1.jpg" alt="#"></td>
                                    <td>
                                        <h6 class="w-160 mb-5"><a href="shop-product-full.html"
                                                                  class="text-heading">${l.name}</a></h6></span>
                                        <div class="product-rate-cover">
                                            <div class="product-rate-customer">
                                                <div class="rate" style="width:90%">
                                                    <c:forEach var="i" begin="1" end="5">
                                                            <span class="star">
                                                                <c:choose>
                                                                    <c:when test="${i<=l.averageStar}">
                                                                        &#9733;
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        &#9734;
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </span>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <span class="font-small ml-5 text-muted"> (${l.totalFeedback})</span>
                                        </div>
                                    </td>
                                    <td>
                                        <h6 class="text-muted pl-20 pr-20">x ${l.quantity}</h6>
                                    </td>
                                    <td>
                                        <h4 class="text-brand">${l.priceAfterDiscount * l.quantity} VND</h4>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="payment ml-30">
                    <h4 class="mb-30">Phương thức thanh toán</h4>
                    <div class="payment_option">
                        <div class="custome-radio">
                            <input class="form-check-input" required="" type="radio" name="payment_option"
                                   id="exampleRadios3" checked="">
                            <label class="form-check-label" for="exampleRadios3" data-bs-toggle="collapse"
                                   data-target="#bankTranfer" aria-controls="bankTranfer">Thanh toán bằng ngân
                                hàng</label>
                        </div>

                    </div>
                    <div class="payment-logo d-flex">
                        <img class="mr-15" src="nest-frontend/assets/imgs/theme/icons/payment-paypal.svg" alt="">
                        <img class="mr-15" src="nest-frontend/assets/imgs/theme/icons/payment-visa.svg" alt="">
                        <img class="mr-15" src="nest-frontend/assets/imgs/theme/icons/payment-master.svg" alt="">
                        <img src="nest-frontend/assets/imgs/theme/icons/payment-zapper.svg" alt="">
                    </div>

                    <form id="orderForm" action="payment" method="post">
                        <input type="hidden" name="voucherID" value="${selectedVoucherID}">
<%--                        <input type="hidden" name="priceTotal" value="${discountedSubtotal}">--%>


                        <c:if test="${not empty discountedSubtotal}">
                                <input type="hidden" name="amount" value="${discountedSubtotal}">
                        </c:if>
                        <c:if test="${empty discountedSubtotal}">
                            <input type="hidden" name="amount" value="${subTotalPrice}">
                        </c:if>

                        <input type="hidden" name="orderTime" id="orderTime" value="">
                        <button type="submit" class="btn btn-fill-out btn-block mt-30" >Đặt hàng<i
                                class="fi-rs-sign-out ml-15"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <style>
        .star {
            color: gold; /* Set the color to yellow or gold */
            font-size: 20px; /* Optional: Adjust the size if needed */
        }
    </style>
</main>
<!-- Vendor JS-->
<script src="nest-frontend/assets/js/vendor/modernizr-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/jquery-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="nest-frontend/assets/js/plugins/slick.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.syotimer.min.js"></script>
<script src="nest-frontend/assets/js/plugins/wow.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery-ui.js"></script>
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
<script src="./nest-frontend/assets/js/main.js?v=4.0"></script>
<script src="./nest-frontend/assets/js/shop.js?v=4.0"></script>
</body>

</html>