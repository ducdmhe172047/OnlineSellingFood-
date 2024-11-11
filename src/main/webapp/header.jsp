<%@ page import="model.Account" %>
<%@ page import="dal.CustomerDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Account account = (Account)session.getAttribute("account");
    int customerID = -1;
    if (account != null) {
        CustomerDAO customerDAO = new CustomerDAO();
        customerID = customerDAO.getCustomerIDByAccountID(account.getAccountID());
    }
%>
<header class="header-area header-style-1 header-style-5 header-height-2">
    <div class="header-middle header-middle-ptb-1 d-none d-lg-block">
        <div class="container">
            <div class="header-wrap">
                <div class="header-right">
                    <div class="search-style-2">
                    </div>
                    <div class="header-action-right">
                        <div class="header-action-2">
                            <%
                                if (account != null) {
                            %>
                            <div class="header-action-icon-2">
                                <a class="mini-cart-icon" href="cart?customerId=<%= customerID %>">
                                    <img alt="Nest" src="nest-frontend/assets/imgs/theme/icons/icon-cart.svg" />
                                </a>
                                <a href="cart?customerId=<%= customerID %>"><span class="lable">Giỏ hàng</span></a>
                            </div>
                            <%
                            }
                            %>
                            <div class="header-action-icon-2">
                                <a href="#">
                                    <img class="svgInject" alt="Nest" src="nest-frontend/assets/imgs/theme/icons/icon-user.svg" />
                                </a>
                                <%
                                    if (account == null) {
                                %>
                                <a href="login"><span class="lable ml-0">Đăng nhập</span></a>
                                <%
                                } else {
                                %>
                                <a><span class="lable ml-0"><%= account.getName() %></span></a>
                                <div class="cart-dropdown-wrap cart-dropdown-hm2 account-dropdown">
                                    <ul>
                                        <li>
                                            <a href="page-account-information.jsp"><i class="fi fi-rs-user mr-10"></i>Tài khoản</a>
                                        </li>
                                        <li>
                                            <a href="orderHistory"><i class="fi fi-rs-location-alt mr-10"></i>Đơn hàng</a>
                                        </li>
                                        <li>
                                            <a href="LoadVoucher"><i class="fi fi-rs-label mr-10"></i>Mã giảm giá</a>
                                        </li>
                                        <li>
                                            <a href="logout"><i class="fi fi-rs-sign-out mr-10"></i>Đăng xuất</a>
                                        </li>
                                    </ul>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="header-bottom header-bottom-bg-color sticky-bar">
        <div class="container">
            <div class="header-wrap header-space-between position-relative">
                <div class="logo logo-width-1 d-block d-lg-none">
                    <a href="#"><img src="nest-frontend/assets/imgs/theme/logo.svg" alt="logo" /></a>
                </div>
                <div class="header-nav d-none d-lg-flex">
                    <div class="main-menu main-menu-padding-1 main-menu-lh-2 d-none d-lg-block font-heading">
                        <nav>
                            <ul>
                                <li>
                                    <a href="home-page.jsp">Trang chủ</a>
                                </li>
                                <li>
                                    <a href="shop-grid-left.jsp">Sản phẩm</a>
                                </li>
                                <li>
                                    <a href="news">Tin tức</a>
                                </li>
                                <li>
                                    <a href="AboutPage"> Giới thiệu </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="header-action-icon-2 d-block d-lg-none">
                    <div class="burger-icon burger-icon-white">
                        <span class="burger-icon-top"></span>
                        <span class="burger-icon-mid"></span>
                        <span class="burger-icon-bottom"></span>
                    </div>
                </div>
                <div class="header-action-right d-block d-lg-none">
                    <div class="header-action-2">
                        <div class="header-action-icon-2">
                        </div>
                        <div class="header-action-icon-2">
                            <a class="mini-cart-icon" href="#">
                                <img alt="Nest" src="nest-frontend/assets/imgs/theme/icons/icon-user.svg" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="mobile-header-active mobile-header-wrapper-style">
    <div class="mobile-header-wrapper-inner">
        <div class="mobile-header-top">
            <div class="mobile-header-logo">
                <a href="#"><img src="nest-frontend/assets/imgs/theme/logo.svg" alt="logo" /></a>
            </div>
            <div class="mobile-menu-close close-style-wrap close-style-position-inherit">
                <button class="close-style search-close">
                    <i class="icon-top"></i>
                    <i class="icon-bottom"></i>
                </button>
            </div>
        </div>
        <div class="mobile-header-content-area">
            <div class="mobile-menu-wrap mobile-header-border">
                <!-- mobile menu start -->
                <nav>
                    <ul class="mobile-menu font-heading">
                        <li class="menu-item-has-children">
                            <a href="home-page.jsp">Trang chủ</a>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="shop-grid-left.jsp">Sản phẩm</a>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="news">Tin tức</a>
                        </li>
                        <li class="menu-item-has-children">
                            <a href="#">Giới thiệu</a>
                        </li>
                    </ul>
                </nav>
                <!-- mobile menu end -->
            </div>
        </div>
    </div>
</div>
