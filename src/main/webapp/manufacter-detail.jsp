<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Chi tiết</title>
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
                <h4 class="card-title mb-4">Sửa thông tin</h4>

                <!-- Error message -->
                <c:if test="${not empty errorMessage}">
                    <div id="errorAlert" class="alert alert-danger" role="alert" style="display: none;">
                            ${errorMessage}
                    </div>
                </c:if>

                <form action="updateManuForAdmin" method="post">
                    <input type="hidden" name="ManufacturerID" value="${manuListDetail.manufacturerID}" />

                    <div class="mb-3">
                        <label class="form-label">Tên</label>
                        <input class="form-control" name="name" value="${manuListDetail.name}" placeholder="Name" type="text" required />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mô tả</label>
                        <textarea class="form-control" name="introduce" placeholder="Introduce" rows="4" required>${manuListDetail.introduce}</textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Trạng thái</label>
                        <select class="form-control" name="active" required>
                            <option value="true" ${manuListDetail.active ? "selected" : ""}>Active</option>
                            <option value="false" ${!manuListDetail.active ? "selected" : ""}>Non-Active</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <button type="submit" class="btn btn-primary w-100">Cập nhật</button>
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
            }, 3000); // Hide after 5 seconds
        }

        if (errorAlert && errorAlert.innerHTML.trim() !== '') {
            errorAlert.style.display = 'block';
            setTimeout(function() {
                errorAlert.style.display = 'none';
            }, 3000); // Hide after 5 seconds
        }
    });
</script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>

