<%@ page import="dto.CustomerDetailRespone" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
                <h4 class="card-title mb-4">Thông tin khách hàng</h4>
                <form action="updateProfileCustomerForAdmin" method="post">
                    <!-- Hidden field for StaffID and AccountID -->
                    <%--                    <input type="hidden" name="staffID" value="${staff.staffID}" />--%>
                    <%--                    <input type="hidden" name="accountID" value="${staff.account.accountID}" />--%>

                    <div class="mb-3">
                        <label class="form-label">Trạng thái tài khoản</label>
                        <select class="form-control" name="statusID" required>
                            <option value="1" ${customerListDetail.statusID == 1 ? 'selected' : ''}>Hoạt động</option>
                            <option value="4" ${customerListDetail.statusID == 4 ? 'selected' : ''}>Khóa tài khoản</option>
                        </select>
                    </div>
                    <input type="hidden" value="${accountID}" name="accountId">

                    <div class="mb-3">
                        <label class="form-label">Tên</label>
                        <input class="form-control" name="firstName" value="${customerListDetail.name}"  type="text" required readonly />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input class="form-control" name="email" value="${customerListDetail.email}"  type="email" required readonly />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input class="form-control" name="phone" value="${customerListDetail.phoneNumber}"  type="text" required readonly/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ</label>
                        <input class="form-control" name="address" value="${customerListDetail.address}"  type="text" required readonly/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ngày sinh</label>
                        <%
                            CustomerDetailRespone cdr = (CustomerDetailRespone)request.getAttribute("customerListDetail");
                            String birth = "";
                            if(cdr.getBirth()!=null){
                                birth = cdr.getBirth().format(DateTimeFormatter.ISO_LOCAL_DATE);
                            }
                        %>
                        <input id="datePicker" class="form-control" name="birth" value="<%=birth%>" type="date" readonly/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Điểm</label>
                        <input class="form-control" name="point" value="${customerListDetail.point}" type="number" required min="0"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Cấp độ</label>
                        <input class="form-control" name="level" value="${customerListDetail.level}"  type="number" min="0" required  />
                    </div>


                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Cập nhật thông tin</button>
                    </div>
                    <div class="mb-4">
                        <button href="customerList" class="btn btn-primary w-100">Quay trở lại</button>
                    </div>
                </form>
            </div>
        </div>

    </section>
</main>
<script>
    datePicker.max = new Date().toISOString().split("T")[0];
</script>
<script>document.getElementById("birth").defaultValue = "2014-02-09";</script>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>

