<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dal.ManufacterDAO.TextTruncator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Danh sách nhà sản xuất</title>
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
    <jsp:param name="page" value="manulist"/>
    <jsp:param name="menu" value="manufacter"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <h2 class="content-title">Nhà sản xuất</h2>
            <div>
                <a href="registerManu" class="btn btn-primary"><i class="material-icons md-plus"></i>Tạo mới</a>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <div class="card mb-4">
            <header class="card-header">
                <div class="row gx-3">
                    <div class="col-lg-4 col-md-6 me-auto">
                        <form action="manulist" method="GET" style="display: flex;">
                            <input type="text" name="search" placeholder="Search..." class="form-control" style="flex: 1;" />
                            <button type="submit" class="btn btn-primary" style="margin-left: 10px;">Search</button>
                        </form>
                    </div>
                    <div class="col-lg-2 col-md-3 col-6">
<%--                        <select class="form-select">--%>
<%--                            <option>Status</option>--%>
<%--                            <option>Active</option>--%>
<%--                            <option>Disabled</option>--%>
<%--                            <option>Show all</option>--%>
<%--                        </select>--%>
                    </div>
                    <div class="col-lg-2 col-md-3 col-6">
<%--                        <select class="form-select">--%>
<%--                            <option>Show 20</option>--%>
<%--                            <option>Show 30</option>--%>
<%--                            <option>Show 40</option>--%>
<%--                        </select>--%>
                    </div>
                </div>
            </header>
            <!-- card-header end// -->
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Tên</th>
                                          <th>Mô tả</th>
                            <th></th>
                            <th>Tổng số sản phẩm</th>
                                          <th>Trạng thái</th>
                            <th class="text-end">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty manuList}">
                                <tr>
                                    <td colspan="3">Không có nhà sản xuất</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${manuList}" var="mn">
                                    <tr>
                                        <td width="40%">
                                            <a  class="itemside">
<%--                                                <div class="left">--%>
<%--                                                    <img src="nest-backend/assets/imgs/people/avatar-1.png" class="img-sm img-avatar" alt="Userpic" />--%>
<%--                                                </div>--%>
                                                <div class="info pl-3">
                                                    <a href="manuDetail?ManufacturerID=${mn.manufacturerID}" class="mb-0 title">${mn.name}</a>
                                                </div>
                                            </a>
                                        </td>
<%--                                        <td>${mn.introduce}</td>--%>
                                        <td>
                                            <c:choose>
                                                <c:when test="${fn:length(mn.introduce) > 40}">
                                                    ${fn:substring(mn.introduce, 0, 40)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${mn.introduce}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><td>${mn.productCount}</td></td>
                                        <td>${mn.active ? "Active" : "Non-Active"}</td>
                                        <td class="text-end">
                                            <div class="col-action" style="display: flex; justify-content: flex-end; width: 100%; gap: 10px;">
                                                <a href="manuListDetail?ManufacturerID=${mn.manufacturerID}" class="btn btn-sm font-sm rounded btn-brand"> <i class="material-icons md-edit"></i> Sửa </a>
                                                <a href="manuDetail?ManufacturerID=${mn.manufacturerID}" class="btn btn-sm font-sm rounded btn-brand"> <i class="material-icons md-launch"></i> View </a>
                                                <a href="#" onclick="confirmDelete(${mn.manufacturerID}, ${mn.productCount}); return false;"
                                                   class="btn btn-sm font-sm btn-light rounded">
                                                    <i class="material-icons md-delete_forever"></i> Xóa
                                                </a>
                                                <form id="deleteForm${mn.manufacturerID}" action="manulist" method="POST" style="display:none;">
                                                    <input type="hidden" name="action" value="delete" />
                                                    <input type="hidden" name="ManufacturerID" value="${mn.manufacturerID}" />
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <!-- table-responsive.// -->
                </div>
            </div>
            <!-- card-body end// -->
        </div>
        <!-- card end// -->
        <div id="pagination-container" class="pagination-area mt-15 mb-50">
            <nav aria-label="Page navigation example">
                <ul id="pagination" class="pagination justify-content-start">
                </ul>
            </nav>
        </div>
    </section>
    <!-- content-main end// -->
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<script>
    const itemsPerPage = 10; // Số mục trên mỗi trang
    let currentPage = 1;
    const items = document.querySelectorAll('tbody tr');
    const totalPages = Math.ceil(items.length / itemsPerPage);

    function showPage(page) {
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        items.forEach((item, index) => {
            if (index >= startIndex && index < endIndex) {
                item.style.display = '';
            } else {
                item.style.display = 'none';
            }
        });
    }

    function setupPagination() {
        const paginationElement = document.getElementById('pagination');
        paginationElement.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            const li = document.createElement('li');
            li.classList.add('page-item');
            if (i === currentPage) {
                li.classList.add('active');
            }
            const a = document.createElement('a');
            a.classList.add('page-link');
            a.href = '#';
            a.innerText = i;
            a.addEventListener('click', (e) => {
                e.preventDefault();
                currentPage = i;
                showPage(currentPage);
                setupPagination();
            });
            li.appendChild(a);
            paginationElement.appendChild(li);
        }
    }

    showPage(currentPage);
    setupPagination();
</script>
<script type="text/javascript">
    function confirmDelete(manufacturerID) {
        var confirmed = confirm("Bạn muốn xóa nhà sản xuất này?");
        if (confirmed) {
            document.getElementById('deleteForm' + manufacturerID).submit();
        }
    }
</script>
<script type="text/javascript">
    function confirmDelete(manufacturerID, productCount) {
        if (productCount >= 1) {
            alert("Không thể xóa vì vẫn tồn tại sản phẩm trong kho");
            return false;
        }
        var confirmed = confirm("Bạn muốn xóa nhà sản xuất này?");
        if (confirmed) {
            document.getElementById('deleteForm' + manufacturerID).submit();
        }
    }
</script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>