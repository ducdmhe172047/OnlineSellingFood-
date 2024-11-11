<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.Account" %>
<%@ page import="dal.CustomerDAO" %>
<%@ page import="dal.DiscountDAO" %>
<%@ page import="dal.ImportProductDAO" %>
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
  String msg = (String)session.getAttribute("msg");
  if(msg==null) msg="";
  session.removeAttribute("msg");
  String msg1 = (String)session.getAttribute("msg1");
  if(msg1==null) msg1="";
  session.removeAttribute("msg1");
%>
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
  ImportProductDAO importProductDAO = new ImportProductDAO();
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
        <span></span> Giỏ hàng
      </div>
    </div>
  </div>
  <div class="container mb-80 mt-50">
    <div class="row">
      <div class="col-lg-8 mb-40">
        <h1 class="heading-2 mb-10">Giỏ hàng của bạn</h1>
        <div class="d-flex justify-content-between">
          <h6 class="text-body">Có <span class="text-brand">${cartItems.size()}</span> sản phẩm trong giỏ hàng </h6>
          <h5 style="color: red"><%=msg%></h5>
          <h6 class="text-body">
            <form action="deleteCartServlet" method="post" style="display:inline;">
              <input type="hidden" name="customerId" value="<%= customerID != -1 ? customerID : "" %>">
              <input type="hidden" name="action" value="clearCart">
              <button type="submit" class="text-muted" onclick="return confirm('Are you sure you want to clear your cart?');" style="color: white;">
                <i class="fi-rs-trash mr-5" style="color: white;"></i>Xóa toàn bộ sản phẩm
              </button>
            </form>
          </h6>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-8">
        <div class="table-responsive shopping-summery">
          <table class="table table-wishlist">
            <thead>
            <tr class="main-heading">
              <th scope="col">Sản phẩm</th>
              <th scope="col">Giá</th>
              <th scope="col">Số lượng</th>
              <th scope="col">Thành Tiền</th>
              <th scope="col" class="end"></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="total" value="0" />
            <c:forEach items="${cartItems}" var="cartItem">
              <tr>
                <td class="product-des product-name">
                  <h6 class="mb-5">
                    <a href="ProductDetail?productID=${cartItem.productID}" class="product-name mb-10 text-heading">${productMap[cartItem.productID].name}</a>
                  </h6>
                </td>
                <td class="price" data-title="Price">
                  <h4 class="text-body">${productMap[cartItem.productID].price} VND</h4>
                </td>

                <td class="text-center detail-info" data-title="Stock">
                  <%
                    // Lấy số lượng tồn kho của sản phẩm
                   // int maxQuantity = importProductDAO.countProductQuantity(cartItem.productID);
                  %>
                  <form action="updateCartServlet" method="post" class="quantity-form">
                    <input type="hidden" name="customerId" value="<%= customerID %>">
                    <input type="hidden" name="productId" value="${cartItem.productID}">
                    <input type="number" name="quantity" value="${cartItem.quantity}" min="1" max="15" style="width: 60px; margin-right: 20px"
                           onchange="this.form.submit()" class="quantity-input">
                  </form>
                </td>
                <td class="price" data-title="Subtotal">
                  <h4 class="text-brand">${productMap[cartItem.productID].price * cartItem.quantity} VND</h4>
                </td>
                <c:set var="total" value="${total + productMap[cartItem.productID].price * cartItem.quantity}" />
                <td class="action text-center remove-column" data-title="Remove">
                  <form action="deleteCartServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="removeItem">
                    <input type="hidden" name="customerId" value="<%= customerID %>">
                    <input type="hidden" name="productId" value="${cartItem.productID}">
                    <button type="submit" class="text-muted" onclick="return confirm('Are you sure you want to delete this item?');">
                      <i class="fi-rs-trash mr-5"></i>
                    </button>
                  </form>
                </td>
              </tr>
            </c:forEach>
            <c:if test="${empty cartItems}">
              <tr>
                <td colspan="6" style="text-align: center"><h5 style="color: red"><%= msg != null ? msg : "" %></h5></td>
              </tr>
            </c:if>
            </tbody>
          </table>
        </div>

        <div class="divider-2 mb-30"></div>
        <div class="cart-action d-flex justify-content-between">
          <a href="home-page.jsp" class="btn"><i class="fi-rs-arrow-left mr-10"></i>Tiếp tục mua sắm</a>
        </div>
      </div>

      <div class="col-lg-4">
        <div class="border p-md-4 cart-totals ml-30">
          <tr>
            <td colspan="6" style="text-align: center ; margin-left: 10px"><h5 style="color: red"><%= msg1 != null ? msg1 : "" %></h5></td>
          </tr>
          <div class="table-responsive">
            <table class="table no-border">
              <tbody>
              <tr>
                <td class="cart_total_label">
                  <h6 class="text-muted">Thành tiền</h6>
                </td>
                <td class="cart_total_amount">
                  <h4 class="text-brand text-end" id="total1">$${total}</h4>
                </td>
              </tr>
              <tr>
                <td class="cart_total_label">
                  <h6 class="text-muted">Tổng</h6>
                </td>
                <td class="cart_total_amount">
                  <h4 class="text-brand text-end" id="total">$${total}</h4>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
          <a href="checkout" class="btn mb-20 w-100">Tiến hành thanh toán<i class="fi-rs-sign-out ml-15"></i></a>
        </div>
      </div>
    </div>
  </div>
</main>

<jsp:include page="footer.jsp" />
</body>
</html>
