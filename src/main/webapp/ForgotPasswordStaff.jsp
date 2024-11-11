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
                <h4 class="card-title mb-4">Forgot Password</h4>
                <%
                    String msg = (String)request.getAttribute("msg");
                    if(msg==null) msg="";
                %>
                <h6 style="color: red"><%=msg%></h6>
                <%
                    String email = request.getParameter("email");
                    if (email == null) {
                %>
                <form method="get" action="forgotpasswordstaff" onsubmit="return validateForm()">
                    <div class="mb-3">
                        <input class="form-control" name="email" id="email" type="text" required="" placeholder="Enter Email *" />
                    </div>
                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Submit</button>
                    </div>
                </form>
                <%
                } else {
                %>
                <form method="get" action="changepassstaff" onsubmit="return validateForm()">
                    <div class="mb-3">
                        <input class="form-control" name="email" id="emailforgot" type="text" required="" value="<%out.print(email);%>" readonly />
                    </div>
                    <div class="mb-3">
                        <input class="form-control" name="otp" id="otp" type="text" required="" placeholder="Enter OTP *" />
                    </div>
                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Submit</button>
                    </div>
                </form>
                <a href="forgotpasswordstaff?email=<%=email%>">Resend otp</a>
                <%
                    }
                %>
            </div>
        </div>
    </section>

    <footer class="main-footer text-center">
        <p class="font-xs">
            <script>
                document.write(new Date().getFullYear());
            </script>
            &copy; Nest - HTML Ecommerce Template .
        </p>
        <p class="font-xs mb-30">All rights reserved</p>
    </footer>
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>

