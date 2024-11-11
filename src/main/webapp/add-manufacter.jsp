<%--
  Created by IntelliJ IDEA.
  User: ducdx
  Date: 10/11/2024
  Time: 12:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Thêm nhà sản xuất</title>
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
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="addmanu"/>
    <jsp:param name="menu" value="manufacter"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main mt-80 mb-80">
        <div class="card mx-auto card-login">
            <div class="card-body">
                <h4 class="card-title mb-4">Tạo mới</h4>

                <!-- Success message -->
                <!-- Success message -->
                <c:if test="${not empty successMessage}">
                    <div id="successAlert" class="alert alert-success" role="alert" style="display: none;">
                            ${successMessage}
                    </div>
                </c:if>

                <!-- Error message -->
                <c:if test="${not empty errorMessage}">
                    <div id="errorAlert" class="alert alert-danger" role="alert" style="display: none;">
                            ${errorMessage}
                    </div>
                </c:if>

                <form action="registerManu" method="post">
                    <div class="mb-3">
                        <label class="form-label">Tên</label>
                        <input class="form-control" name="name" placeholder="Name" type="text" required />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mô tả</label>
                        <textarea class="form-control" name="introduce" placeholder="Introduce"></textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Trạng thái</label>
                        <select class="form-control" name="active" required>
                            <option value="true">Active</option>
                            <option value="false">Nonactive</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Tạo</button>
                    </div>
                </form>
            </div>

        </div>
    </section>
</main>

<script>
</script>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var successAlert = document.getElementById('successAlert');
        var errorAlert = document.getElementById('errorAlert');

        if (successAlert && successAlert.innerHTML.trim() !== '') {
            successAlert.style.display = 'block';
            setTimeout(function() {
                successAlert.style.display = 'none';
            }, 1000); // Hide after 5 seconds
        }

        if (errorAlert && errorAlert.innerHTML.trim() !== '') {
            errorAlert.style.display = 'block';
            setTimeout(function() {
                errorAlert.style.display = 'none';
            }, 1000); // Hide after 5 seconds
        }
    });
</script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>
