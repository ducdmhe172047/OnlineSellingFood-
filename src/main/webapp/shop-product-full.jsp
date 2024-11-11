<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="common.Host" %>
<%@ page import="dal.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Thương mại điện tử</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta property="og:title" content=""/>
    <meta property="og:type" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:image" content=""/>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-frontend/assets/imgs/theme/favicon.svg"/>
    <!-- Template CSS -->
    <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0"/>
</head>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        const stars = document.querySelectorAll('.stars input[type="radio"]');
        const labels = document.querySelectorAll('.stars label');
        const ratingText = document.querySelector('.rating-text');

        const ratingMessages = {
            1: "Rất không hài lòng",
            2: "Không hài lòng",
            3: "Bình thường",
            4: "Hài lòng",
            5: "Xuất sắc"
        };

        function updateStars(ratingValue) {
            // Đặt tất cả sao về màu xám
            labels.forEach(label => label.style.color = 'lightgray');

            // Đổi màu vàng cho các sao từ phải sang trái dựa trên ngôi sao được chọn
            for (let i = 0; i < ratingValue; i++) {
                labels[i].style.color = '#FFD700';
            }
            // Cập nhật văn bản hiển thị mức độ hài lòng
            ratingText.textContent = ratingMessages[ratingValue];
        }

        // Thiết lập mặc định là 1 sao (Rất không hài lòng)
        updateStars(1);

        // Lắng nghe sự kiện thay đổi khi người dùng chọn sao khác
        stars.forEach(star => {
            star.addEventListener('change', function () {
                const ratingValue = parseInt(this.value);
                updateStars(ratingValue);
            });
        });
    });
</script>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        const errorMessage = "<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage").toString() : "" %>";
        if (errorMessage) {
            const loginModal = new bootstrap.Modal(document.getElementById('loginModal'), {});
            loginModal.show();
        }
    });

    function login() {
        window.location.href = "http://localhost:9998/OnlineSellingFood_war/login";
    }

    function register() {
        window.location.href = "http://localhost:9998/OnlineSellingFood_war/register";
    }
</script>

<style>
    .reply-form {
        display: none;
    }

    .comment-list:hover .reply-form {
        display: block;
    }
</style>

<body class="single-product">
<%
    UnitDAO unitDAO = new UnitDAO();
    ManufacterDAO manufacterDAO = new ManufacterDAO();
    String accountName = "";
    try {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            accountName = account.getName();
        }
    } catch (Exception e) {
        accountName = "";
    }

    Product product = (Product) request.getAttribute("product");

    ImgDAO imgDAO = new ImgDAO();
    ProductImgDAO productImgDAO = new ProductImgDAO();
    List<Img> imgUrls = new ArrayList<>();
    boolean haveimg;
    try{
        imgUrls.add(imgDAO.getImgById(productImgDAO.getDefaultImg(product.getProductID()).getImgID()));
        List<ProductImg> productImgs = productImgDAO.getNotDefaultImg(product.getProductID());
        for(ProductImg productImg : productImgs){
            imgUrls.add(imgDAO.getImgById(productImg.getImgID()));
        }
        haveimg = true;
    }catch(Exception e){
        haveimg = false;
    }

    String name = request.getParameter("name");
    // Tạo đối tượng DAO để lấy số sao trung bình
    FeedbackProductDAO feedbackDAO = new FeedbackProductDAO();
    int star = feedbackDAO.averageStarInProduct(product.getProductID());
    if(star==0){star=5;}
%>
<jsp:include page="header.jsp">
    <jsp:param name="accountName" value="<%= accountName %>"/>
</jsp:include>

<main class="main">
    <div class="container mb-30">
        <div class="row">
            <div class="col-xl-10 col-lg-12 m-auto">
                <div class="product-detail accordion-detail">
                    <div class="row mb-50 mt-30">
                        <!-- Gallery chính -->
                        <div class="col-md-6 col-sm-12 mb-md-0 mb-sm-5">
                            <div class="detail-gallery">
                                <!-- Ảnh chính -->
                                <div class="product-image-slider">
                                    <figure class="border-radius-10">
                                        <img src="<%if(haveimg)%><%=Host.IMG_LINK+imgUrls.get(0).getImglink()+"?raw=true"%>" alt="<%=name%>" style="width: 100%; height: auto; object-fit: cover;" />
                                    </figure>
                                </div>

                                <div class="thumbnail-list mt-20">
                                    <%
                                        for (int i = 1; i < imgUrls.size(); i++) {
                                            Img img = imgUrls.get(i);
                                    %>
                                    <div class="thumbnail-item" style="display: inline-block; margin-right: 5px;">
                                        <img src="<%= Host.IMG_LINK + img.getImglink() + "?raw=true" %>" alt="<%= name %>" style="width: 50px; height: 50px; border-radius: 5px; object-fit: cover;" />
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-12">
                            <div class="detail-info pr-30 pl-30">
                                <span class="stock-status out-stock">Sale Off</span>
                                <h2 class="title-detail"><%= (product != null) ? product.getName() : "Product name not available." %>
                                </h2>

                                <!-- Static 4-Star Rating and Placeholder for Reviews -->
                                <div class="product-detail-rating">
                                    <div class="product-rate-cover text-end">
                                        <div class="product-rate d-inline-block">
                                            <div class="product-rating" style="width: <%=star * 20%>%"></div>
                                        </div>
                                        <span class="font-small ml-5 text-muted"> (<%=star%>)</span>
                                    </div>
                                </div>

                                <!-- Product Price (Without Discount) -->
                                <div class="clearfix product-price-cover">
                                    <div class="product-price primary-color float-left">
                                        <!-- Display current price only -->
                                        <%
                                            DiscountDAO discountDAO = new DiscountDAO();
                                            int discountid = product.getDiscountID() != null ? product.getDiscountID() : 0;
                                            Discount discount = discountDAO.getDiscountByDiscountId(discountid);
                                        %>
                                        <div class="product-price">
                                            <% if (discount != null && discount.getEndTime().isAfter(LocalDateTime.now()) && discount.getStartTime().isBefore(LocalDateTime.now())) { %>
                                            <span class="current-price text-brand"><%=product.getPrice() - product.getPrice() * discount.getDiscountPercent() / 100%> VND</span>
                                            <span class="old-price"><%=product.getPrice()%> VND</span>
                                            <% } else { %>
                                            <span class="current-price text-brand"><%=product.getPrice()%> VND</span>
                                            <% } %>
                                        </div>
                                    </div>
                                </div>

                                <!-- Product Description -->
                                <div class="short-desc mb-30">
                                    <p class="font-lg"><%= (product != null) ? product.getDetail() : "Product details not available." %>
                                    </p>
                                </div>

                                <!-- Static Unit Display -->
                                <div class="attr-detail attr-size mb-30">
                                    <strong class="mr-10">Đơn vị: </strong>
                                    <ul class="list-filter size-filter font-small">
                                        <p class="font-lg"><%= (product != null) ? unitDAO.getUnitNameByID(product.getUnitID()): "Product units not available." %></p>
                                    </ul>
                                </div>
                                <div class="product-extra-link2">
                                    <form action="addtocart" method="post">
                                        <button type="submit" class="button button-add-to-cart"><i class="fi-rs-shopping-cart"></i>Thêm vào giỏ hàng</button>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="product-info">
                        <div class="tab-style3">
                            <ul class="nav nav-tabs text-uppercase">
                                <li class="nav-item">
                                    <a class="nav-link active" id="Description-tab" data-bs-toggle="tab"
                                       href="#Description">Mô tả sản phẩm</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="Vendor-info-tab" data-bs-toggle="tab" href="#Vendor-info">Nhà cung cấp</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="Reviews-tab" data-bs-toggle="tab" href="#Reviews">Đánh giá
                                        (${count})</a>
                                </li>
                            </ul>
                            <div class="tab-content shop_info_tab entry-main-content">

                                <div class="tab-pane fade" id="Reviews">
                                    <!--Comments-->
                                    <div class="comments-area">
                                        <div class="row">
                                            <div class="col-lg-8">
                                                <h4 class="mb-30">Câu hỏi của khách hàng và trả lời</h4>
                                                <!-- Loop through main comments -->
                                                <!-- Loop through main comments -->
                                                <c:forEach var="l" items="${list}">
                                                    <div class="comment-list">
                                                        <div class="single-comment justify-content-between d-flex mb-30">
                                                            <div class="user justify-content-between d-flex">
                                                                <div class="thumb text-center">
                                                                    <img src="nest-frontend/assets/imgs/blog/author-2.png" alt=""/>
                                                                    <div class="user-name">
                                                                        <a href="#" class="font-heading text-brand">${l.customerName}</a>
                                                                    </div>
                                                                </div>
                                                                <div class="desc">
                                                                    <div class="d-flex justify-content-between mb-10 align-items-center">
                                                                        <div class="product-rate-custom">
                                                                            <div class="rate" style="width: ${l.star * 20}%">
                                                                                <c:forEach var="i" begin="1" end="5">
                                                                                    <span class="star">
                                                                                        <c:choose>
                                                                                            <c:when test="${i <= l.star}">
                                                                                                &#9733; <!-- Ngôi sao đầy -->
                                                                                            </c:when>
                                                                                            <c:otherwise>
                                                                                                &#9734; <!-- Ngôi sao rỗng -->
                                                                                            </c:otherwise>
                                                                                        </c:choose>
                                                                                    </span>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <span class="comment-time">${l.time}</span>
                                                                    </div>
                                                                    <p class="mb-10">${l.feedback}</p>

                                                                    <!-- Hiển thị nút "Reply" nếu comment không phải của tài khoản hiện tại -->

                                                                    <c:if test="${l.customerID != currentID}">
                                                                        <div class="reply-form">
                                                                            <form action="replyComment" method="post" class="mt-2">
                                                                                <input type="hidden" name="productID" value="${productID}" />
                                                                                <input type="hidden" name="replyID" value="${l.feedbackID}" />

                                                                                <textarea name="replyContent" class="form-control" rows="1" required minlength="1" placeholder="Vết phản hồi..."></textarea>
                                                                                <button type="submit" class="btn btn-sm btn-primary mt-2 reply-button">Gửi phản hồi</button>


                                                                                <div class="comment-replies">
                                                                                    <c:forEach var="reply" items="${l.replies}">
                                                                                        <div class="single-reply d-flex mb-10" style="margin-left: 40px;">
                                                                                            <div class="thumb text-center">
                                                                                                <img src="nest-frontend/assets/imgs/blog/author-2.png" alt=""/>
                                                                                            </div>
                                                                                            <div class="reply-desc">
                                                                                                <div class="user-name">
                                                                                                    <a href="#" class="font-heading text-brand">${reply.customerName}</a>
                                                                                                </div>
                                                                                                <span class="comment-time">${reply.time}</span>
                                                                                                <p>${reply.feedback}</p>
                                                                                            </div>
                                                                                        </div>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </c:if>


                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div   >
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>

                                    <!--comment form-->
                                    <div class="comment-form">
                                        <h4 class="mb-15">Thêm đánh giá</h4>
                                        <form class="form-contact comment_form" action="ProductDetail" method="post"
                                              id="commentForm">
                                            <input type="hidden" name="productID"
                                                   value="<%= (product != null) ? product.getProductID() : "" %>">
                                            <div class="rating-container">
                                                <div class="rating-section">
                                                    <label >Mức độ đánh giá *</label>
                                                    <div class="stars">
                                                        <input type="radio" name="rating" id="star1" value="1"
                                                               checked><label for="star1"
                                                                              title="Rất không hài lòng">&#9733;</label>
                                                        <input type="radio" name="rating" id="star2" value="2"><label
                                                            for="star2" title="Không hài lòng">&#9733;</label>
                                                        <input type="radio" name="rating" id="star3" value="3"><label
                                                            for="star3" title="Bình thường">&#9733;</label>
                                                        <input type="radio" name="rating" id="star4" value="4"><label
                                                            for="star4" title="Hài lòng">&#9733;</label>
                                                        <input type="radio" name="rating" id="star5" value="5"><label
                                                            for="star5" title="Xuất sắc">&#9733;</label>
                                                    </div>
                                                    <span class="rating-text">Rất không hài lòng</span>
                                                </div>


                                                <div class="row">
                                                    <div class="col-lg-8 col-md-12">
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <div class="form-group">
                                                                    <textarea required minlength="1" class="form-control w-100" name="comment"
                                                                              id="comment" cols="30" rows="9"
                                                                              placeholder="Viết đánh giá"></textarea>
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="button button-contactForm">
                                                                Gửi đánh giá
                                                            </button>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="tab-content shop_info_tab entry-main-content">
                            <div class="tab-pane fade show active" id="Description">
                                <div class="">
                                    <p class="font-lg"><%= (product != null) ? product.getDetail() : "Product details not available." %></p>
                                </div>
                            </div>
                            <div class="tab-pane fade show active" id="Vendor-info">
                                <div class="">
                                    <p class="font-lg"><%= (product != null) ? manufacterDAO.getManufacturerName(product.getManufacturerID()) : "Product Manufactor not available." %></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-60">
                        <div class="col-12">
                            <h2 class="section-title style-1 mb-30">Sản phẩm liên quan</h2>
                        </div>
                        <div class="row product-grid-4">
                            <%
                                ImportProductDAO importProductDAO = new ImportProductDAO();
                                ProductDAO productDAO = new ProductDAO();
                                List<Product> products = productDAO.get5RelatedProductsByManufacturer(product.getManufacturerID());
                                CategoryDAO categoryDAO = new CategoryDAO();
                            %>
                            <% for (Product product1 : products) {
                                List<String> images = productDAO.getProductImages(product1.getProductID());
                                String defaultImageUrl = images.size() > 0 ? images.get(0) : "default-image.jpg";
                                String hoverImageUrl = images.size() > 1 ? images.get(1) : defaultImageUrl;
                                int inventory = importProductDAO.countProductQuantity(product.getProductID());
                                if(inventory>0){
                            %>
                            <jsp:include page="product-box.jsp">
                                <jsp:param name="category" value="<%= categoryDAO.getCategoryName(product1.getCategoryID())%>" />
                                <jsp:param name="name" value="<%= product1.getName() %>" />
                                <jsp:param name="manufacturer" value="<%= manufacterDAO.getManufacturerName(product1.getManufacturerID()) %>" />
                                <jsp:param name="star" value="4" />
                                <jsp:param name="discount" value="<%= product1.getDiscountID() != null ? product1.getDiscountID().toString() : '0' %>" />
                                <jsp:param name="price" value="<%= product1.getPrice().toString() %>" />
                                <jsp:param name="productID" value="<%= product1.getProductID().toString() %>" />
                                <jsp:param name="imageUrl" value="<%= defaultImageUrl %>" />
                                <jsp:param name="hoverImageUrl" value="<%= hoverImageUrl %>" />
                                <jsp:param name="inventory" value="<%=inventory%>"/>
                            </jsp:include>
                            <% }} %>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLabel">Notification</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body text-center">
                    <p style="color:red;">${errorMessage}</p>
                </div>
                <%
                    Account account = (Account) session.getAttribute("account");
                %>
                <c:if test="${account==null}">
                    <div class="d-flex justify-content-center gap-3 mt-3">
                        <button class="btn btn-outline-primary" onclick="register()">Register</button>
                        <button class="btn btn-primary" onclick="login()">Login</button>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp"/>
<script src="nest-frontend/assets/js/vendor/jquery-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="nest-frontend/assets/js/plugins.js"></script>
<script src="nest-frontend/assets/js/main.js?v=4.0"></script>
</body>
</html>