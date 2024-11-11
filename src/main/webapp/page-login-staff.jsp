<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/25/2024
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Nest Dashboard</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta property="og:title" content="" />
    <meta property="og:type" content="" />
    <meta property="og:url" content="" />
    <meta property="og:image" content="" />
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg" />
    <!-- Template CSS -->
    <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
</head>

<body>
<main>
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main mt-80 mb-80">
        <div class="card mx-auto card-login">
            <div class="card-body">
                <h4 class="card-title mb-4">Sign in</h4>
                <%
                    String msg = (String)request.getAttribute("msg");
                    if(msg==null) msg="";
                %>
                <h6 style="color:red"><%=msg%></h6>
                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                <form action="loginstaff" method="post">
                    <div class="mb-3">
                        <input class="form-control" placeholder="Email" name="email"  type="text"
                               value="${cookie.cuser.value}"/>
                    </div>
                    <!-- form-group// -->
                    <div class="mb-3">
                        <input class="form-control" placeholder="Password" name="password" type="password"
                               value="${cookie.cpass.value}"/>
                    </div>
                    <!-- form-group// -->
                    <div class="mb-3">
                        <a href="ForgotPasswordStaff.jsp" class="float-end font-sm text-muted">Forgot password?</a>
                        <label class="form-check">
                            <input type="checkbox" class="form-check-input" ${(cookie.crem!=null?'checked':'')} name="rem" id="remember_me" value="ON" />
                            <span class="form-check-label">Remember</span>
                        </label>
                    </div>
                    <!-- form-group form-check -->
                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Login</button>
                    </div>
                    <!-- form-group// -->
                </form>
            </div>
        </div>
    </section>
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>