<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <link rel="stylesheet" href="nest-frontend/assets/css/main.css?v=4.0" />
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<%
    String msg = (String)request.getAttribute("msg");
    if(msg==null) msg="";
%>
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
        <div class="col-xl-8 col-lg-10 col-md-12 m-auto">
          <div class="row">
            <div class="col-lg-6 col-md-8">
              <div class="login_wrap widget-taber-content background-white">
                <div class="padding_eight_all bg-white">
                  <div class="heading_s1">
                    <h1 class="mb-5">Đăng ký tài khoản</h1>
                    <p class="mb-30">Đã có tài khoản? <a href="page-login.html">Đăng nhập</a></p>
                    <h6 style="color: red"><%=msg%></h6>
                  </div>
                  <form action="register" method="post">
                    <!-- Name -->
                    <div class="form-group">
                      <input type="text" required="" name="name" minlength="2" maxlength="100" placeholder="Tên" />
                    </div>

                    <!-- Email -->
                    <div class="form-group">
                      <input type="email" required="" name="email" placeholder="Email" minlength="10" maxlength="100"/>
                    </div>

                    <!-- Birth -->
                    <div class="form-group">
                      Ngày tháng năm sinh<input type="date" id="datePicker" name="birth" required/>
                    </div>

                    <!-- Gender -->
                    <select class="form-group" name="gender">
                      <option value="1">Nam</option>
                      <option value="2">Nữ</option>
                    </select>

                    <!-- Contact Information -->
                    <div class="form-group">
                      <input type="text" required="" name="phone" placeholder="Số điện thoại" minlength="10" maxlength="10"/>

                    </div>
                    <div class="form-group">
                      <input type="text" required="" name="address"  minlength="5" maxlength="200" placeholder="Địa chỉ" />

                    </div>
                    <!-- Password -->
                    <div class="form-group">
                      <input required="" type="password" name="password" minlength="8" maxlength="64" placeholder="Mật khẩu" />
                    </div>

                    <!-- Confirm Password -->
                    <div class="form-group">
                      <input required="" type="password" name="confirmPassword" minlength="8" maxlength="64" placeholder="Xác nhận mật khẩu" />
                    </div>

                    <!-- Agree to Terms -->
                    <div class="login_footer form-group mb-50">
                      <div class="chek-form">
                        <div class="custome-checkbox">
                          <input class="form-check-input" type="checkbox" name="checkbox" id="exampleCheckbox12" value="" required/>
                          <label class="form-check-label" for="exampleCheckbox12">
                            <span>Tôi đồng ý với chính sách</span>
                          </label>
                        </div>
                      </div>
                      <a href="page-privacy-policy.html"><i class="fi-rs-book-alt mr-5 text-muted"></i>Thông tin thêm</a>
                    </div>

                    <!-- Submit Button -->
                    <div class="form-group mb-30">
                      <button type="submit" class="btn btn-fill-out btn-block hover-up font-weight-bold" name="register">Đăng ký</button>
                    </div>

                    <p class="font-xs text-muted">
                      <strong>Ghi chú:</strong> Dữ liệu cá nhân của bạn sẽ được sử dụng để hỗ trợ trải nghiệm của bạn trên toàn trang web này, để quản lý quyền truy cập vào tài khoản của bạn và cho các mục đích khác được mô tả trong chính sách bảo mật của chúng tôi.
                    </p>
                  </form>
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
<script>
  datePicker.max = new Date().toISOString().split("T")[0];
</script>
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
