<%@ page import="model.Account" %><%--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  Created by IntelliJ IDEA.
  User: anh21
  Date: 9/30/2024
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    int role = ((Account)session.getAttribute("account")).getRoleID();
    String pageActive = request.getParameter("page"), pageMenuActive = request.getParameter("menu");
%>
<aside class="navbar-aside" id="offcanvas_aside">
    <div class="aside-top">
        <div>
            <button class="btn btn-icon btn-aside-minimize"><i class="text-muted material-icons md-menu_open"></i></button>
        </div>
    </div>
    <nav>
        <ul class="menu-aside">
            <%if(role == 5){%>
            <li class="menu-item" id="homepage">
                <a class="menu-link" href="Dashboard?index=1&search=&orderStatus=">
                    <i class="icon material-icons md-home"></i>
                    <span class="text">Bảng điều khiển</span>
                </a>
            </li>
            <%
                }
                else if(role==4){
            %>
            <li class="menu-item" id="homepage">
                <a class="menu-link" href="DashboardI?index=1&search=">
                    <i class="icon material-icons md-home"></i>
                    <span class="text">Bảng điều khiển</span>
                </a>
            </li>
            <%
            }
            else if(role==2){
            %>
            <li class="menu-item" id="homepage">
                <a class="menu-link" href="DashboardD">
                    <i class="icon material-icons md-home"></i>
                    <span class="text">Bảng điều khiển</span>
                </a>
            </li>
            <%

                }
                else{
            %>
            <li class="menu-item" id="homepage">
                <a class="menu-link" href="home-page-staff.jsp">
                    <i class="icon material-icons md-home"></i>
                    <span class="text">Bảng điều khiển</span>
                </a>
            </li>
            <%}%>
            <li class="menu-item" id="changepass">
                <a class="menu-link" href="account-changepass-staff.jsp">
                    <i class="icon material-icons md-person"></i>
                    <span class="text">Thay đổi mật khẩu</span>
                </a>
            </li>
            <%
                if(role==1){
            %>
            <li class="menu-item has-submenu" id="account">
                <a class="menu-link" href="#">
                    <i class="icon material-icons md-person"></i>
                    <span class="text">Tài khoản</span>
                </a>
                <div class="submenu">
                    <a href="staffList" id="staff">Danh sách nhân viên</a>
                    <a href="customerList" id="customer">Danh sách khách hàng</a>
                    <a href="registerstaff" id="registerstaff">Đăng ký nhân viên</a>
                </div>
            </li>
            <li class="menu-item" id="warehouse">
                <a class="menu-link" href="warehouseList">
                    <i class="icon material-icons md-store"></i>
                    <span class="text">Kho hàng</span>
                </a>
            </li>
            <li class="menu-item" id="unit">
                <a class="menu-link" href="unitlist">
                    <i class="icon material-icons md-shopping_bag"></i>
                    <span class="text">Đơn vị</span>
                </a>
            </li>

            <%
                }
                if(role==2){
            %>

            <%
                }
                if(role==3||role==4){
            %>
            <li class="menu-item has-submenu" id="product">
                <a class="menu-link" href="#">
                    <i class="icon material-icons md-shopping_bag"></i>
                    <span class="text">Nhập</span>
                </a>
                <div class="submenu">
                    <a href="Import" id="Import">Nhập</a>
                </div>
            </li>
            <%
                }
                if(role==4){
            %>
            <li class="menu-item" id="supplier">
                <a class="menu-link" href="supplierList">
                    <i class="icon material-icons md-store"></i>
                    <span class="text">Nhà cung cấp</span>
                </a>
            </li>
            <%
                }
                if(role==5){
            %>

            <li class="menu-item has-submenu" id="product">
                <a class="menu-link" href="#">
                    <i class="icon material-icons md-shopping_bag"></i>
                    <span class="text">Sản phẩm</span>
                </a>
                <div class="submenu">
                    <a href="LoadProductStaffServlet" id="productList">Danh sách sản phẩm</a>
                    <a href="certificationList" id="certification">Chứng nhận</a>
                    <a href="certificateIssuerList" id="certificateissuer">Bên cấp chứng nhận</a>
                    <a href="categoryList" id="category">Phân loại</a>
                    <a href="originList" id="origin">Nguồn gốc</a>
                    <a href="discount" id="discount">Giảm giá sản phẩm</a>
                    <a href="voucher" id="voucher">Mã giảm giá</a>
                    <a href="customerOrder" id="order">Danh sách đặt hàng</a>
                </div>
            </li>
            <li class="menu-item has-submenu" id="news">
                <a class="menu-link" href="#">
                    <i class="icon material-icons md-add_box"></i>
                    <span class="text">News</span>
                </a>
                <div class="submenu">
                    <a href="addNew" id=addNew>Quản lý News</a>
                </div>
            </li>
            <li class="menu-item has-submenu" id="manufacter">
                <a class="menu-link" href="#">
                    <i class="icon material-icons md-store"></i>
                    <span class="text">Nhà sản xuất</span>
                </a>
                <div class="submenu">
                    <a href="manulist" id="manulist">Danh sách nhà sản xuất</a>
                    <a href="registerManu" id="addmanu">Thêm nhà sản xuất</a>
                </div>
            </li>
            <%

                }
            %>
        </ul>
        <hr/>
    </nav>
</aside>
<script>
    document.getElementById("<%=pageMenuActive%>").classList.add("active");
    document.getElementById("<%=pageActive%>").classList.add("active");
</script>