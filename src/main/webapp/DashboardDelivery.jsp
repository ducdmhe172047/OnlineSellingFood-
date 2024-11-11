<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dto.OrderResponse" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <style>
    .status-waiting {
      color: #FFA500;
    }
    .status-confirmed {
      color: #28A745;
    }
    .status-delivering {
      color: #17A2B8;
    }
    .status-completed {
      color: #007BFF;
    }
    .status-canceled {
      color: #DC3545;
    }
    .status-failed {
      color: #6C757D;
    }
    .search-bar {
      margin-bottom: 20px;
      padding: 10px;
      background-color: #f8f9fa;
      border: 1px solid #ddd;
      border-radius: 5px;
    }

    .pagination-container {
      margin-top: 20px;
    }

    .page-item.active .page-link {
      background-color: #007bff;
      color: #fff;
      border-color: #007bff;
    }

    .page-item .page-link {
      color: #007bff;
      transition: background-color 0.3s ease;
    }

    .page-item .page-link:hover {
      background-color: #e9ecef;
      color: #0056b3;
    }
    .filter-btn {
      position: absolute;
      bottom: 10px;
      left: 10px;
    }
    .black-text {
      color: black;
    }
  </style>
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


    </div>
    <div class="row">
      <div class="col-lg-3">
        <div class="card card-body mb-4">
          <article class="icontext">
            <span class="icon icon-sm rounded-circle bg-primary-light"><i class="text-primary material-icons md-monetization_on"></i></span>
            <div class="text">
              <h6 class="mb-1 card-title">Đơn hàng giao thành công</h6>
              <span>${completedOrders}</span>

            </div>
          </article>
        </div>
      </div>
      <div class="col-lg-3">
        <div class="card card-body mb-4">
          <article class="icontext">
            <span class="icon icon-sm rounded-circle bg-success-light"><i class="text-success material-icons md-local_shipping"></i></span>
            <div class="text">
              <h6 class="mb-1 card-title">Đơn hàng đang được giao </h6>
              <span>${beingDeliveredOrders}</span>

            </div>
          </article>
        </div>
      </div>
      <div class="col-lg-3">
        <div class="card card-body mb-4">
          <article class="icontext">
            <span class="icon icon-sm rounded-circle bg-warning-light"><i class="text-warning material-icons md-qr_code"></i></span>
            <div class="text">
              <h6 class="mb-1 card-title">Đơn hàng đang chờ xác nhận </h6>
              <span>${otherOrders}</span>

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

      </div>

      <div class="card-body">
        <div class="table-responsive">
          <div class="table-responsive">
            <table class="table align-middle table-nowrap mb-0">
              <thead class="table-light">
              <tr>

                <th class="align-middle" scope="col" style="color: black;" >Mã đơn hàng</th>
                <th class="align-middle" scope="col" style="color: black;" >Tên khách hàng</th>
                <th class="align-middle" scope="col" style="color: black;" >Ngày đặt hàng</th>
                <th class="align-middle" scope="col" style="color: black;" >Giá</th>
                <th class="align-middle" scope="col" style="color: black;" >Trạng thái đơn hàng</th>
                <th class="align-middle" scope="col" style="color: black;" >Phương thức thanh toán</th>

              </tr>
              </thead>
              <tbody>
              <c:forEach var="ord" items="${orderList}">
                <tr>

                  <td>${ord.getOrderID()}</td>
                  <td>${ord.getCustomerName()}</td>
                  <td>${ord.getOrderDate()}</td>
                  <td>${ord.getPrice()}VND</td>
                  <td>${ord.getOrderStatusName()}</td>
                  <td>${ord.getOrderPaymentName()}</td>

                </tr>
              </c:forEach>


              </tbody>
            </table>
            <div class="pagination-container">
              <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                  <c:forEach begin="1" end="${endPage}" var="i">
                    <li class="page-item ${i == param.index ? 'active' : ''}">
                      <a class="page-link" href="DashboardD?index=${i}">${i}</a>
                    </li>
                  </c:forEach>
                </ul>
              </nav>
            </div>

            <div class="pagination-container">
              <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">

                </ul>
              </nav>
            </div>
          </div>

            <div class="table-responsive">
              <div class="table-responsive">
                <table class="table align-middle table-nowrap mb-0">
                  <thead class="table-light">
                  <tr>

                    <th class="align-middle" scope="col" style="color: black;" >Mã đơn hàng</th>
                    <th class="align-middle" scope="col" style="color: black;" >Tên khách hàng</th>
                    <th class="align-middle" scope="col" style="color: black;" >Ngày đặt hàng</th>
                    <th class="align-middle" scope="col" style="color: black;" >Giá</th>
                    <th class="align-middle" scope="col" style="color: black;" >Trạng thái đơn hàng</th>
                    <th class="align-middle" scope="col" style="color: black;" >Phương thức thanh toán</th>

                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="ord" items="${orderListb}">
                    <tr>

                      <td>${ord.getOrderID()}</td>
                      <td>${ord.getCustomerName()}</td>
                      <td>${ord.getOrderDate()}</td>
                      <td>${ord.getPrice()}VND</td>
                      <td>${ord.getOrderStatusName()}</td>
                      <td>${ord.getOrderPaymentName()}</td>

                    </tr>
                  </c:forEach>


                  </tbody>
                </table>
                <div class="pagination-container">
                  <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                      <c:forEach begin="1" end="${endPageb}" var="ib">
                        <li class="page-item ${ib == param.indexb ? 'active' : ''}">
                          <a class="page-link" href="DashboardD?indexb=${ib}">${ib}</a>
                        </li>
                      </c:forEach>
                    </ul>
                  </nav>
                </div>
                <div class="pagination-container">
                  <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">

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
