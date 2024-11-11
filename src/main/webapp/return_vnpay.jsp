<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/7/2024
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="dal.PaymentDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <meta name="description" content="">
  <meta name="author" content="">
  <title>KẾT QUẢ THANH TOÁN</title>
  <!-- Bootstrap core CSS -->
  <link href="./nest-frontend/assets/css/returnPayment/bootstrap.min.css" rel="stylesheet"/>
  <!-- Custom styles for this template -->
  <link href="./nest-frontend/assets/css/returnPayment/jumbotron-narrow.css" rel="stylesheet">
  <script src="./nest-frontend/assets/js/returnPayment/jquery-1.11.3.min.js"></script>
</head>
<body>
<%
  //Begin process return from VNPAY
  Map fields = new HashMap();
  for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
    String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
    String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
    if ((fieldValue != null) && (fieldValue.length() > 0)) {
      fields.put(fieldName, fieldValue);
    }
  }

  String vnp_SecureHash = request.getParameter("vnp_SecureHash");
  if (fields.containsKey("vnp_SecureHashType")) {
    fields.remove("vnp_SecureHashType");
  }
  if (fields.containsKey("vnp_SecureHash")) {
    fields.remove("vnp_SecureHash");
  }
  String signValue = PaymentDAO.hashAllFields(fields);

%>
<!--Begin display -->
<div class="container">

  <div class="header clearfix">
    <h3 class="text-muted">KẾT QUẢ THANH TOÁN</h3>
  </div>
  <form action="orderProduct" method="get">
    <div class="table-responsive">
      <div class="form-group">

        <label >Mã giao dịch thanh toán:</label>
        <label><%=request.getParameter("vnp_TxnRef")%></label>
      </div>
      <div class="form-group">
        <label>Số tiền:</label>
        <%
          // Retrieve the amount from the request parameter and convert it to an integer
          int amount = Integer.parseInt(request.getParameter("vnp_Amount"));
          // Divide the amount by 100
          int dividedAmount = amount / 100;
        %>
        <input name="uID" value="${sessionScope.user.userID}" style="display: none"/>
        <!-- Sử dụng giá trị của dividedAmount như giá trị của thuộc tính value -->
        <input name="amount" value="<%= dividedAmount %>" style="display: none"/>
        <!-- Hiển thị số tiền sau khi chia 100 -->
        <label><%= dividedAmount %>đ</label>
      </div>
      <div class="form-group">
        <label >Mô tả giao dịch:</label>
        <label><%=request.getParameter("vnp_OrderInfo")%></label>
      </div>
      <div class="form-group">
        <label >Mã lỗi thanh toán:</label>
        <label><%=request.getParameter("vnp_ResponseCode")%></label>
      </div>
      <div class="form-group">
        <label >Mã giao dịch tại CTT VNPAY-QR:</label>
        <label><%=request.getParameter("vnp_TransactionNo")%></label>
      </div>
      <div class="form-group">
        <label >Mã ngân hàng thanh toán:</label>
        <label><%=request.getParameter("vnp_BankCode")%></label>
      </div>
      <%
        // Lấy thời gian thanh toán từ tham số yêu cầu
        String payDateStr = request.getParameter("vnp_PayDate");
        // Định dạng thời gian từ chuỗi thành đối tượng Date
        Date payDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(payDateStr);
        // Định dạng lại thời gian thành chuỗi theo định dạng mong muốn
        String formattedPayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payDate);
      %>

      <div class="form-group">
        <label>Thời gian thanh toán:</label>
        <label><%= formattedPayDate %></label>
        <input name="date" value="<%= formattedPayDate %>" style="display:none"/>
      </div>
      <div class="form-group">
        <label>Tình trạng giao dịch:</label>
        <label>
          <% if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) { %>
          Thành công
          <% } else { %>
          Không thành công
          <% }
          } else { %>
          Invalid signature
          <% } %>
        </label>
      </div>
    </div>
    <!-- Button hoặc link dựa trên kết quả của giao dịch -->
    <%
      if ("00".equals(request.getParameter("vnp_TransactionStatus"))) { %>
    <button style="margin-top:30px" type="submit" class="btn btn-primary">Back To Homepage</button>
    <% } else { %>
    <a href="checkout" style="margin-top:30px" class="btn btn-primary">Back To Checkout</a>
    <% } %>
  </form>
  <p>
    &nbsp;
  </p>
  <footer class="footer">
    <p>&copy; VNPAY 2020</p>
  </footer>
</div>
</body>
</html>

