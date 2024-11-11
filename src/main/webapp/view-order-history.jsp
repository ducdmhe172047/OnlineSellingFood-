<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Account" %>
<%@ page import="dal.CustomerDAO" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
  <meta charset="utf-8" />
  <title>Giỏ hàng </title>
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="shortcut icon" type="image/x-icon" href="nest-frontend/assets/imgs/theme/favicon.svg" />
  <link rel="stylesheet" href="nest-frontend/assets/css/plugins/animate.min.css" />
  <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0" />
  <script src="nest-frontend/assets/js/vendor/jquery-3.6.0.min.js"></script>
</head>

<body>
<%
  String accountName;
  try {
    accountName = ((Account) session.getAttribute("account")).getName();
  } catch (NullPointerException e) {
    accountName = "";
  }
  Account account = (Account)session.getAttribute("account");
  int customerID = -1;
  if (account != null) {
    CustomerDAO customerDAO = new CustomerDAO();
    customerID = customerDAO.getCustomerIDByAccountID(account.getAccountID());
  }
%>

<jsp:include page="header.jsp">
  <jsp:param name="accountName" value="<%= accountName %>"/>
</jsp:include>

<main class="main">
  <div class="page-header breadcrumb-wrap">
    <div class="container">
      <div class="breadcrumb">
        <a href="homepage" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a>
        <span></span> Shop
        <span></span> Lịch sử mua hàng
      </div>
    </div>
  </div>
  <div class="container mb-80 mt-50">
    <div class="row">
      <div class="col-lg-8 mb-40">
        <h1 class="heading-2 mb-10">Lịch sử mua hàng</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-15">
        <div class="table-responsive shopping-summery">
          <table class="table table-wishlist">
            <thead>
            <tr class="main-heading">
              <th scope="col">Sản phẩm</th>
              <th scope="col">Giá</th>
              <th scope="col">Số lượng</th>
              <th scope="col">Thời gian đặt hàng</th>
              <th scope="col">Trạng thái</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="l">
              <tr>
                <td class="product-des product-name">
                  <h4 class="text-body">${l.productName}</h4>
                </td>
                <td class="price" data-title="Price">
                  <h4 class="text-body">${l.price}</h4>
                </td>
                <td class="text-center detail-info" data-title="Stock">
                  <h4 class="text-body">${l.quantity}</h4>
                </td>
                <td class="price" data-title="Subtotal">
                  <h4 class="text-body">${l.time}</h4>
                </td>
                <td class="product-des product-name" data-title="Stock">
                  <h4 class="text-brand">${l.statusDetail}</h4>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>


    </div>
  </div>
</main>

<jsp:include page="footer.jsp" />
</body>
</html>
