<%@ page import="model.Account" %>
<%@ page import="model.AccountContact" %>
<%@ page import="java.util.List" %>
<%@ page import="dal.ContactInformationDAO" %>
<%@ page import="model.ContactInformation" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/19/2024
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8" />
    <title>Nest - Multipurpose eCommerce HTML Template</title>
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
    <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0" />
</head>

<body>
<%
    String accountName =  ((Account)session.getAttribute("account")).getName();
%>
<jsp:include page="header.jsp">
    <jsp:param name="accountName" value="<%=accountName%>"/>
</jsp:include>
<!--End header-->
<main class="main pages">
    <div class="page-header breadcrumb-wrap">
        <div class="container">
            <div class="breadcrumb">
                <a href="index.html" rel="nofollow"><i class="fi-rs-home mr-5"></i>Trang chủ</a>
                <span></span> Trang <span></span> Tài khoản
            </div>
        </div>
    </div>
    <div class="page-content pt-150 pb-150">
        <div class="container">
            <div class="row">
                <div class="col-lg-10 m-auto">
                    <div class="row">
                        <div class="col-md-3">
                            <jsp:include page="account-menu.jsp">
                                <jsp:param name="page" value="contact-tab"/>
                            </jsp:include>
                        </div>
                        <div class="col-md-9">
                            <div class="tab-content account dashboard-content pl-50">
                                <div class="tab-pane fade active show" id="orders" role="tabpanel" aria-labelledby="orders-tab">
                                    <div class="card">
                                        <div class="card-header">
                                            <h3 class="mb-0">Thông tin liên hệ</h3>
                                            <button><a href="page-account-update-contact.jsp">Thêm thông tin liên hệ mới</a></button>
                                        </div>
                                        <div class="card-body">
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <%
                                                        List<AccountContact> accountContacts = (List<AccountContact>)request.getAttribute("contacts");
                                                        ContactInformationDAO contactInformationDAO = new ContactInformationDAO();
                                                        ContactInformation contactInformation;
                                                        for(AccountContact ac:accountContacts){
                                                            contactInformation = contactInformationDAO.getContactInformationByContactID(ac.getContactInformationID());
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <p><%=contactInformation.getPhoneNumber()%></p>
                                                            <p><%=contactInformation.getAddress()%></p>
                                                            <%
                                                                if(ac.getIsDefault()==1){
                                                            %>
                                                                <p style="color: red">Mặc định</p>
                                                            <%
                                                                }
                                                            %>
                                                        </td>
                                                        <td>
                                                            <a href="page-account-update-contact.jsp?contactID=<%=ac.getContactInformationID()%>&address=<%=contactInformation.getAddress()%>&phone=<%=contactInformation.getPhoneNumber()%>">Cập nhật</a><br>
                                                            <%
                                                                if(ac.getIsDefault()!=1){
                                                            %>
                                                                <a href="DeleteContactServlet?contactID=<%=ac.getContactInformationID()%>">Xóa</a><br>
                                                                <a href="UpdateContactServlet?contactID=<%=ac.getContactInformationID()%>&isdefault=1">Đặt làm mặc định</a><br>
                                                            <%
                                                                }
                                                            %>
                                                        </td>
                                                    </tr>
                                                    <%
                                                        }
                                                    %>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
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
<script src="nest-frontend/assets/js/main.js?v=4.0"></script>
<script src="nest-frontend/assets/js/shop.js?v=4.0"></script>
</body>
</html>

