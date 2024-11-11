<%@ page import="common.Host" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <main class="main-wrap">
            <section class="content-main">
                <div class="content-header">
                    <div>
                        <h2 class="content-title card-title">Chi tiết đơn hàng</h2>
                        <p>Chi tiết đơn hàng: ${orderID}</p>
                    </div>
                </div>
                <div class="card">
                    <header class="card-header">
                        <div class="row align-items-center">
                            <div class="col-lg-6 col-md-6 mb-lg-0 mb-15">
                                <span> <i class="material-icons md-calendar_today"></i> <b>${orderTime}</b> </span> <br />
                                <small class="text-muted">ID đặt hàng:${orderID} </small>
                            </div>
                            <div class="col-lg-6 col-md-6 ms-auto text-md-end">
                                <form action="viewOrderDetail" method="get">
                                    <select class="form-select d-inline-block mb-lg-0 mr-5 mw-200" name="statusID" >
                                        <option value="1" ${statusID == 1 ? 'selected' : ''}>Wait for confirmation</option>
                                        <option value="2" ${statusID == 2 ? 'selected' : ''}>Confirmation</option>
                                        <option value="3" ${statusID == 3 ? 'selected' : ''}>Being delivered</option>
                                        <option value="4" ${statusID == 4 ? 'selected' : ''}>Completed</option>
                                        <option value="5" ${statusID == 5 ? 'selected' : ''}>Canceled</option>
                                        <option value="6" ${statusID == 6 ? 'selected' : ''}>Failed</option>
                                    </select>
                                    <button class="btn btn-primary" type="submit" >Lưu</button>
                                    <a class="btn btn-primary" href="customerOrder">Quay trở lại</a>
                                    <input type="hidden" name="orderID" value="${orderID}"/>
                                    <input type="hidden" name="customerName" value="${customerName}"/>
                                    <input type="hidden" name="email" value="${email}"/>
                                    <input type="hidden" name="price" value="${price}"/>
                                    <input type="hidden" name="phone" value="${phone}"/>
                                    <input type="hidden" name="address" value="${address}"/>
                                    <input type="hidden" name="statusDetail" value="${statusDetail}"/>
                                    <input type="hidden" name="orderTime" value="${orderTime}"/>
                                    <input type="hidden" name="statusID" value="${statusID}"/>

                                </form>

                            </div>
                        </div>
                    </header>
                    <!-- card-header end// -->
                    <div class="card-body">
                        <div class="row mb-50 mt-20 order-info-wrap">
                            <div class="col-md-4">
                                <article class="icontext align-items-start">
                                    <span class="icon icon-sm rounded-circle bg-primary-light">
                                        <i class="text-primary material-icons md-person"></i>
                                    </span>
                                    <div class="text">
                                        <h6 class="mb-1">Khách hàng</h6>
                                        <p class="mb-1">
                                            ${customerName} <br />
                                            ${email} <br />
                                            ${phone}
                                        </p>
                                    </div>
                                </article>
                            </div>
                            <!-- col// -->
                            <div class="col-md-4">
                                <article class="icontext align-items-start">
                                    <span class="icon icon-sm rounded-circle bg-primary-light">
                                        <i class="text-primary material-icons md-place"></i>
                                    </span>
                                    <div class="text">
                                        <h6 class="mb-1">Địa chỉ nhận hàng</h6>
                                        <p class="mb-1">
                                           ${address} <br />

                                        </p>
                                    </div>
                                </article>
                            </div>
                            <!-- col// -->
                        </div>
                        <!-- row // -->
                        <div class="row">
                            <div class="col-lg-7">
                                <c:set var="totalAmount" value="0" />
                                <c:forEach items="${list}" var="l">
                                    <c:set var="totalPrice" value="${totalPrice + l.totalPrice}" />
                                    <c:set var="discountVoucher" value="${l.discountVoucher}"/>
                                    <c:set var="totalPriceAfterVoucher" value="${l.totalPriceAfterVoucher}"/>
                                    <c:set var="statusDetail" value="${l.statusDetail}"/>
                                    <c:set var="statusID" value="${l.statusID}"/>

                                </c:forEach>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th width="40%">Sản phẩm</th>
                                                <th width="20%">Giá</th>
                                                <th width="20%">Số lượng</th>
                                                <th width="20%" class="text-end">Tổng tiền</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${list}" var="l">
                                        <tr>
                                            <td>
                                                <a class="itemside" href="#">
                                                    <div class="left">
                                                        <img src="<%=Host.IMG_LINK%>${l.imgLink}?raw=true" width="40" height="40" class="img-xs" alt="Item" />
                                                    </div>
                                                    <div class="info">${l.productName}</div>
                                                </a>
                                            </td>
                                            <td>${l.price}VND</td>
                                            <td>${l.quantity}</td>
                                            <td class="text-end">${l.totalPrice}VND</td>
                                        </tr>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="4">
                                                <article class="float-end">
                                                    <dl class="dlist">
                                                        <dt>Tổng tiền:</dt>
                                                        <dd>${totalPrice}VND</dd>
                                                    </dl>
                                                    <dl class="dlist">
                                                        <dt>Phiếu giảm giá:</dt>
                                                        <dd>
                                                            <c:choose>
                                                                <c:when test="${discountVoucher != null && discountVoucher > 0}">
                                                                    ${discountVoucher}%
                                                                </c:when>
                                                                <c:otherwise>
                                                                    Không dùng phiếu giảm giá
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </dd>
                                                    </dl>
                                                    <dl class="dlist">
                                                        <dt>Tổng tiền sau khi áp dụng phiếu giảm giá:</dt>
                                                        <dd><b class="h5">${totalPriceAfterVoucher}VND</b></dd>
                                                    </dl>
                                                    <dl class="dlist">
                                                        <dt class="text-muted">Trạng thái:</dt>
                                                        <dd>
                                                            <c:choose>
                                                                <c:when test="${statusID == 2}">
                                                                    <span class="badge rounded-pill alert-warning">${statusDetail}</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="badge rounded-pill alert-success">${statusDetail}</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </dd>
                                                    </dl>
                                                </article>
                                            </td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- table-responsive// -->
                            </div>
                            <!-- col// -->
                            <div class="col-lg-1"></div>

                            <!-- col// -->
                        </div>
                    </div>
                    <!-- card-body end// -->
                </div>
                <!-- card end// -->
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
