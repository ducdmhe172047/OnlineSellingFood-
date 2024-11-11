<%@ page import="model.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <!DOCTYPE html>
    <html class="no-js" lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Thương mại điện tử</title>
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
try{
accountName =  ((Account)session.getAttribute("account")).getName();
}catch(NullPointerException e){
accountName="";
}
%>
<jsp:include page="header.jsp">
    <jsp:param name="accountName" value="<%=accountName%>"/>
</jsp:include>

<div class="mobile-header-active mobile-header-wrapper-style">
    <div class="mobile-header-wrapper-inner">
        <div class="mobile-header-top">
            <div class="mobile-header-logo">
                <a href="index.html"><img src="nest-frontend/assets/imgs/theme/logo.svg" alt="logo" /></a>
            </div>
            <div class="mobile-menu-close close-style-wrap close-style-position-inherit">
                <button class="close-style search-close">
                    <i class="icon-top"></i>
                    <i class="icon-bottom"></i>
                </button>
            </div>
        </div>
        <div class="mobile-header-content-area">
            <div class="mobile-search search-style-3 mobile-header-border">
                <form action="#">
                    <input type="text" placeholder="Search for items…" />
                    <button type="submit"><i class="fi-rs-search"></i></button>
                </form>
            </div>
            <div class="mobile-menu-wrap mobile-header-border">
                <!-- mobile menu start -->
                <nav>
                    <ul class="mobile-menu font-heading">
                        <li class="menu-item-has-children">
                            <a href="index.html">Home</a>
                            <ul class="dropdown">
                                <li><a href="index.html">Home 1</a></li>
                                <li><a href="index-2.html">Home 2</a></li>
                                <li><a href="index-3.html">Home 3</a></li>
                                <li><a href="index-4.html">Home 4</a></li>
                                <li><a href="index-5.html">Home 5</a></li>
                                <li><a href="index-6.html">Home 6</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="shop-grid-right.html">shop</a>
                            <ul class="dropdown">
                                <li><a href="shop-grid-right.html">Shop Grid – Right Sidebar</a></li>
                                <li><a href="shop-grid-left.html">Shop Grid – Left Sidebar</a></li>
                                <li><a href="shop-list-right.html">Shop List – Right Sidebar</a></li>
                                <li><a href="shop-list-left.html">Shop List – Left Sidebar</a></li>
                                <li><a href="shop-fullwidth.html">Shop - Wide</a></li>
                                <li class="menu-item-has-children">
                                    <a href="#">Single Product</a>
                                    <ul class="dropdown">
                                        <li><a href="shop-product-right.html">Product – Right Sidebar</a></li>
                                        <li><a href="shop-product-left.html">Product – Left Sidebar</a></li>
                                        <li><a href="shop-product-full.html">Product – No sidebar</a></li>
                                        <li><a href="shop-product-vendor.html">Product – Vendor Infor</a></li>
                                    </ul>
                                </li>
                                <li><a href="shop-filter.html">Shop – Filter</a></li>
                                <li><a href="shop-wishlist.html">Shop – Wishlist</a></li>
                                <li><a href="shop-cart.html">Shop – Cart</a></li>
                                <li><a href="shop-checkout.html">Shop – Checkout</a></li>
                                <li><a href="shop-compare.html">Shop – Compare</a></li>
                                <li class="menu-item-has-children">
                                    <a href="#">Shop Invoice</a>
                                    <ul class="dropdown">
                                        <li><a href="shop-invoice-1.html">Shop Invoice 1</a></li>
                                        <li><a href="shop-invoice-2.html">Shop Invoice 2</a></li>
                                        <li><a href="shop-invoice-3.html">Shop Invoice 3</a></li>
                                        <li><a href="shop-invoice-4.html">Shop Invoice 4</a></li>
                                        <li><a href="shop-invoice-5.html">Shop Invoice 5</a></li>
                                        <li><a href="shop-invoice-6.html">Shop Invoice 6</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Vendors</a>
                            <ul class="dropdown">
                                <li><a href="vendors-grid.html">Vendors Grid</a></li>
                                <li><a href="vendors-list.html">Vendors List</a></li>
                                <li><a href="vendor-details-1.html">Vendor Details 01</a></li>
                                <li><a href="vendor-details-2.html">Vendor Details 02</a></li>
                                <li><a href="vendor-dashboard.html">Vendor Dashboard</a></li>
                                <li><a href="vendor-guide.html">Vendor Guide</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Mega menu</a>
                            <ul class="dropdown">
                                <li class="menu-item-has-children">
                                    <a href="#">Women's Fashion</a>
                                    <ul class="dropdown">
                                        <li><a href="shop-product-right.html">Dresses</a></li>
                                        <li><a href="shop-product-right.html">Blouses & Shirts</a></li>
                                        <li><a href="shop-product-right.html">Hoodies & Sweatshirts</a></li>
                                        <li><a href="shop-product-right.html">Women's Sets</a></li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children">
                                    <a href="#">Men's Fashion</a>
                                    <ul class="dropdown">
                                        <li><a href="shop-product-right.html">Jackets</a></li>
                                        <li><a href="shop-product-right.html">Casual Faux Leather</a></li>
                                        <li><a href="shop-product-right.html">Genuine Leather</a></li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children">
                                    <a href="#">Technology</a>
                                    <ul class="dropdown">
                                        <li><a href="shop-product-right.html">Gaming Laptops</a></li>
                                        <li><a href="shop-product-right.html">Ultraslim Laptops</a></li>
                                        <li><a href="shop-product-right.html">Tablets</a></li>
                                        <li><a href="shop-product-right.html">Laptop Accessories</a></li>
                                        <li><a href="shop-product-right.html">Tablet Accessories</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="blog-category-fullwidth.html">Blog</a>
                            <ul class="dropdown">
                                <li><a href="blog-category-grid.html">Blog Category Grid</a></li>
                                <li><a href="blog-category-list.html">Blog Category List</a></li>
                                <li><a href="blog-category-big.html">Blog Category Big</a></li>
                                <li><a href="blog-category-fullwidth.html">Blog Category Wide</a></li>
                                <li class="menu-item-has-children">
                                    <a href="#">Single Product Layout</a>
                                    <ul class="dropdown">
                                        <li><a href="blog-post-left.html">Left Sidebar</a></li>
                                        <li><a href="blog-post-right.html">Right Sidebar</a></li>
                                        <li><a href="blog-post-fullwidth.html">No Sidebar</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="page-about.html">About Us</a></li>
                                <li><a href="page-contact.html">Contact</a></li>
                                <li><a href="page-account.html">My Account</a></li>
                                <li><a href="page-login.html">Login</a></li>
                                <li><a href="page-register.html">Register</a></li>
                                <li><a href="page-purchase-guide.html">Purchase Guide</a></li>
                                <li><a href="page-privacy-policy.html">Privacy Policy</a></li>
                                <li><a href="page-terms.html">Terms of Service</a></li>
                                <li><a href="page-404.html">404 Page</a></li>
                            </ul>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Language</a>
                            <ul class="dropdown">
                                <li><a href="#">English</a></li>
                                <li><a href="#">French</a></li>
                                <li><a href="#">German</a></li>
                                <li><a href="#">Spanish</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <!-- mobile menu end -->
            </div>
            <div class="mobile-header-info-wrap">
                <div class="single-mobile-header-info">
                    <a href="page-contact.html"><i class="fi-rs-marker"></i> Our location </a>
                </div>
                <div class="single-mobile-header-info">
                    <a href="page-login.html"><i class="fi-rs-user"></i>Log In / Sign Up </a>
                </div>
                <div class="single-mobile-header-info">
                    <a href="#"><i class="fi-rs-headphones"></i>(+01) - 2345 - 6789 </a>
                </div>
            </div>
            <div class="mobile-social-icon mb-50">
                <h6 class="mb-15">Follow Us</h6>
                <a href="#"><img src="nest-frontend/assets/imgs/theme/icons/icon-facebook-white.svg" alt="" /></a>
                <a href="#"><img src="nest-frontend/assets/imgs/theme/icons/icon-twitter-white.svg" alt="" /></a>
                <a href="#"><img src="nest-frontend/assets/imgs/theme/icons/icon-instagram-white.svg" alt="" /></a>
                <a href="#"><img src="nest-frontend/assets/imgs/theme/icons/icon-pinterest-white.svg" alt="" /></a>
                <a href="#"><img src="nest-frontend/assets/imgs/theme/icons/icon-youtube-white.svg" alt="" /></a>
            </div>
            <div class="site-copyright">Copyright 2021 © Nest. All rights reserved. Powered by AliThemes.</div>
        </div>
    </div>
</div>
<!--End header-->
<main class="main pages">
    <div class="page-header breadcrumb-wrap">
        <div class="container">
            <div class="breadcrumb">
                <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Home</a>
                <span></span> Pages <span></span> About us
            </div>
        </div>
    </div>
    <div class="page-content pt-50">
        <div class="container">
            <div class="row">
                <div class="col-xl-10 col-lg-12 m-auto">
                    <section class="row align-items-center mb-50">
                        <div class="col-lg-6">
                            <img src="Img/anhcuahang.png" alt="" class="border-radius-15 mb-md-3 mb-lg-0 mb-sm-4" />
                        </div>
                        <div class="col-lg-6">
                            <div class="pl-25">
                                <h2 class="mb-30">Chào mừng đến với cửa hàng tiện lợi của chúng tôi</h2>
                                <p class="mb-25">Cửa hàng tiện lợi của chúng tôi xin trân trọng gửi lời cảm ơn sâu sắc đến Quý khách hàng đã luôn quan tâm, ủng hộ và tin tưởng lựa chọn chúng tôi trong suốt thời gian qua. Chúng tôi chân thành kính chúc Quý khách và gia đình luôn dồi dào sức khỏe, hạnh phúc, và gặp nhiều may mắn trong cuộc sống!</p>
                                <p class="mb-50">Tuy mới chỉ được thành lập không lâu, nhưng Cửa hàng tiện lợi của chúng tôi tự hào là một trong những cửa hàng tiện lợi tiên phong tại khu vực Phước Tỉnh - Long Hải, chuyên cung cấp một loạt sản phẩm phong phú nhằm phục vụ tốt nhất nhu cầu của cư dân trong khu vực. Tại đây, Quý khách hàng có thể dễ dàng tìm thấy mọi nhu yếu phẩm, hàng tiêu dùng và thực phẩm đa dạng, với các thương hiệu ngoại nhập chất lượng cao.Chúng tôi cam kết mang đến cho khách hàng mức giá hợp lý nhất, cùng với dịch vụ khách hàng chu đáo. Đội ngũ nhân viên tại cửa hàng tiện lợi luôn nhiệt tình, tận tâm, sẵn sàng hỗ trợ và cung cấp dịch vụ giao hàng tận nơi nhanh chóng để đảm bảo sự hài lòng tối đa cho Quý khách.</p>
                                <div class="carausel-3-columns-cover position-relative">
                                    <div id="carausel-3-columns-arrows"></div>
                                    <div class="carausel-3-columns" id="carausel-3-columns">
                                        <img src="Img/bentrong.png" alt="" />
                                        <img src="Img/thietke3.jpg" alt="" />
                                        <img src="Img/thietke1.jpg" alt="" />
                                        <img src="Img/thietke2.jpg" alt="" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>



    </div>
</main>
<footer class="main">
    <section class="section-padding footer-mid">
        <div class="container pt-15 pb-20">
            <div class="row">
                <div class="col">
                    <div class="widget-about font-md mb-md-3 mb-lg-3 mb-xl-0">
                        <h5 style="font-weight: bold; color: #333;">Contact Information</h5>
                        <ul class="contact-infor" style="list-style: none; padding-left: 0; display: flex; justify-content: space-between; flex-wrap: wrap;">
                            <li style="display: flex; align-items: center; margin-right: 20px;">
                                <img src="nest-frontend/assets/imgs/theme/icons/icon-location.svg" alt="Location" style="width: 18px; height: 18px; margin-right: 8px;" />
                                <span><strong>Address:</strong> Hà Nội</span>
                            </li>
                            <li style="display: flex; align-items: center; margin-right: 20px;">
                                <img src="nest-frontend/assets/imgs/theme/icons/icon-contact.svg" alt="Phone" style="width: 18px; height: 18px; margin-right: 8px;" />
                                <span><strong>Call Us:</strong> (+84) - 866-943-212</span>
                            </li>
                            <li style="display: flex; align-items: center; margin-right: 20px;">
                                <img src="nest-frontend/assets/imgs/theme/icons/icon-email-2.svg" alt="Email" style="width: 18px; height: 18px; margin-right: 8px;" />
                                <span><strong>Email:</strong> cuahangtienloi@gmail.com</span>
                            </li>
                            <li style="display: flex; align-items: center;">
                                <img src="nest-frontend/assets/imgs/theme/icons/icon-clock.svg" alt="Business Hours" style="width: 18px; height: 18px; margin-right: 8px;" />
                                <span><strong>Hours:</strong> 7:00 - 20:00, Mon - Sun</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
</footer>
<!-- Preloader Start -->
<div id="preloader-active">
    <div class="preloader d-flex align-items-center justify-content-center">
        <div class="preloader-inner position-relative">
            <div class="text-center">
                <img src="nest-frontend/assets/imgs/theme/loading.gif" alt="" />
            </div>
        </div>
    </div>
</div>
<!-- Vendor JS-->
<script src="nest-frontend/assets/js/vendor/modernizr-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/jquery-3.6.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/jquery-migrate-3.3.0.min.js"></script>
<script src="nest-frontend/assets/js/vendor/bootstrap.bundle.min.js"></script>
<script src="nest-frontend/assets/js/plugins/slick.js"></script>
<script src="nest-frontend/assets/js/plugins/jquery.syotimer.min.js"></script>
<script src="nest-frontend/assets/js/plugins/wow.js"></script>
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
