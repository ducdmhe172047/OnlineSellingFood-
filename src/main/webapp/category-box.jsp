<%--
  Created by IntelliJ IDEA.
  User: anh21
  Date: 9/29/2024
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

    String name = request.getParameter("name");
    String id = request.getParameter("id");
    int quantity = Integer.parseInt(request.getParameter("quantity")), color = Integer.parseInt(request.getParameter("color"));
%>
<div class="card-2 bg-<%=color%> wow animate__animated animate__fadeInUp" data-wow-delay="0s">
    <figure class="img-hover-scale overflow-hidden">
        <a href="shop-grid-left.jsp?categoryID=<%=id%>&page=1"><img src="nest-frontend/assets/imgs/shop/cat-15.png" alt="" /></a>
    </figure>
    <h6><a href="shop-grid-left.jsp?categoryID=<%=id%>&page=1"><%=name%></a></h6>
    <span><%=quantity%> sản phẩm</span>
</div>