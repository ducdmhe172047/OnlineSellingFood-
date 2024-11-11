<%@ page import="common.Host" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String category = request.getParameter("category");
    String name = request.getParameter("name");
    String manufacturer = request.getParameter("manufacturer");
    int star = Integer.parseInt(request.getParameter("star"));
    int discount = Integer.parseInt(request.getParameter("discount"));
    int price = Integer.parseInt(request.getParameter("price"));
    int productID = Integer.parseInt(request.getParameter("productID"));
    String inventory = request.getParameter("inventory");
    String imageUrl = request.getParameter("imageUrl");
%>

<div class="col-lg-1-5 col-md-4 col-12 col-sm-6">
    <div class="product-cart-wrap mb-30 wow animate__animated animate__fadeIn" data-wow-delay=".1s">
        <div class="product-img-action-wrap">
            <div class="product-img product-img-zoom">
                <a href="ProductDetail?productID=<%=productID%>">
                    <div style="min-height: 200px;max-height: 200px;">
                        <img class="default-img" src="<%=Host.IMG_LINK+imageUrl+"?raw=true"%>" alt="<%=name%>" />
                    </div>
                </a>
            </div>
        </div>
        <div class="product-content-wrap">
            <div class="product-category">
                <a href="shop-grid-left.jsp?category=<%=category%>"><%=category%></a>
            </div>
            <h2><a href="ProductDetail?productID=<%=productID%>"><%=name%></a></h2>

            <div class="product-rate-cover">
                <div class="product-rate d-inline-block">
                    <div class="product-rating" style="width: <%=star * 20%>%"></div>
                </div>
                <span class="font-small ml-5 text-muted"> (<%=inventory%>)</span>
            </div>

            <div>
                <span class="font-small text-muted">By <a href="vendor-details-1.html?manufacturer=<%=manufacturer%>"><%=manufacturer%></a></span>
            </div>
            <div class="product-card-bottom">
                <div class="product-card-bottom">
                    <div class="product-price">
                        <% if (discount > 0) { %>
                        <span><%=price - price * discount / 100%> VND</span>
                        <span class="old-price"><%=price%> VND</span>
                        <% } else { %>
                        <span><%=price%> VND</span>
                        <% } %>
                    </div>

<%--                    discount = discountDAO.getDiscountById(discountID);--%>
<%--                    if (discount.getEndTime().isAfter(LocalDateTime.now())) {--%>
<%--                    product.setPrice(product.getPrice() * (100-discount.getDiscountPercent())/100);--%>
<%--                    }--%>
                </div>
            </div>
            <div class="product-card-bottom">
            <div class="add-cart">
                <form action="addtocart" method="post">
                    <input type="hidden" name="productID" value="<%=productID%>">
                    <input type="submit" class="add mr-5" value="Thêm vào giỏ hàng" style="color: #3BB77E">
                </form>
            </div>
            </div>
        </div>
    </div>
</div>
