<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="dto.StaffDetailRespone" %>
<%@ page import="model.Warehouse" %>
<%@ page import="java.util.List" %>
<%@ page import="dal.AccountDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Nest Dashboard</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta property="og:title" content=""/>
    <meta property="og:type" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:image" content=""/>
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg"/>
    <!-- Template CSS -->
    <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css"/>
</head>

<body>
<main>
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main mt-80 mb-80">
        <div class="card mx-auto card-login">
            <div class="card-body">
                <h4 class="card-title mb-4">Chỉnh sửa nhân viên thông tin</h4>
                <h6 style="color: red">${msg}</h6>
                <form action="staffListDetail" method="post">
                    <!-- Hidden field for StaffID and AccountID -->
                    <%--                    <input type="hidden" name="staffID" value="${staff.staffID}" />--%>
                    <%--                    <input type="hidden" name="accountID" value="${staff.account.accountID}" />--%>

                    <div class="mb-3">
                        <label class="form-label">Chức vụ</label>
                        <select class="form-control" name="roleID" required>
                            <option value="2" ${staffListDetail.roleID == 2 ? 'selected' : ''}>Nhân viên giao hàng
                            </option>
                            <option value="3" ${staffListDetail.roleID == 3 ? 'selected' : ''}>Nhân viên báo cáo
                            </option>
                            <option value="4" ${staffListDetail.roleID == 4 ? 'selected' : ''}>Quản lý kho hàng</option>
                            <option value="5" ${staffListDetail.roleID == 5 ? 'selected' : ''}>Quản lý bán hàng</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên</label>
                        <input class="form-control" name="name" value="${staffListDetail.name}" type="text" required/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Giới tính</label>
                        <select class="form-control" name="gender" required>
                            <option value="1" ${staffListDetail.gender == '1' ? 'selected' : ''}>Nam</option>
                            <option value="2" ${staffListDetail.gender == '2' ? 'selected' : ''}>Nữ</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input class="form-control" name="email" value="${staffListDetail.email}" type="email"
                               required=""/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input class="form-control" name="phone" value="${staffListDetail.phoneNumber}" type="text"
                               required="" minlength="10" maxlength="10"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ</label>
                        <input class="form-control" name="address" value="${staffListDetail.address}" type="text"
                               required
                               minlength="5" maxlength="200"/>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ngày sinh</label>
                        <input class="form-control" name="birth" value="${formattedBirth}" type="date" required=>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Lương</label>
                        <input class="form-control" name="salary" value="${staffListDetail.salary}" type="number" min="0"
                               required/>
                    </div>
                    <div>
                        <input name="accountID" value="${accountID}" hidden="">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tên kho hàng</label>

                        <select class="form-control" name="warehouseID" required>
                            <c:forEach items="${warehouses}" var="wh">
                                <option value="${wh.warehouseID}" ${staffListDetail.warehouseID == wh.warehouseID ? 'selected' : ''}> ${wh.name}</option>
                            </c:forEach>
                        </select>


                    </div>
                    <div class="mb-3">
                        <label class="form-label">Trạng thái tài khoản</label>
                        <select class="form-control" name="statusID" required>
                            <option value="1" ${staffListDetail.statusID == 1 ? 'selected' : ''}>Hoạt động</option>
                            <option value="3" ${staffListDetail.statusID == 3 ? 'selected' : ''}>Mật khẩu cần phải thay
                                đổi
                            </option>
                            <option value="4" ${staffListDetail.statusID == 4? 'selected' : ''}>Khóa tài khoản</option>

                        </select>
                    </div>

                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Cập nhật thông tin</button>
                    </div>
                    <div class="mb-4">
                        <button href="staffList" class="btn btn-primary w-100">Quay trở lại</button>
                    </div>
            </div>
            </form>
        </div>
        </div>

    </section>

</main>
<script>
    datePicker.max = new Date().toISOString().split("T")[0];
</script>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>

