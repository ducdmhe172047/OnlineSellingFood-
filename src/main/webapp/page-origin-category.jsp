<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg" />
    <!-- Template CSS -->
    <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
</head>

<body>
<%
    String msg = (String)session.getAttribute("msg");
    if(msg==null) msg="";
    session.removeAttribute("msg");
%>
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="category"/>
    <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Loại sản phẩm</h2>
                <p>Thêm, sửa, xóa loại sản phẩm</p>
            </div>
            <div>
                <form action="categorySearch" method="post">
                    <input type="text" name="searchKeyword" placeholder="Tìm kiếm loại sản phẩm" class="form-control bg-white" />
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <form action="categoryCU" method="post" onsubmit="return validateForm()">
                            <div class="mb-4">
                                <label for="product_name" class="form-label">Tên</label>
                                <input type="text" placeholder="Tên loại" class="form-control" id="product_name" name="name" required />
                                <input type="hidden" id="category_id" name="categoryID" />
                            </div>
                            <h5 style="color: red"><%= msg != null ? msg : "" %></h5>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary" id="submit_button">Tạo loại sản phẩm</button>
                                <button type="button" class="btn btn-secondary mt-2" id="cancel_button" onclick="resetForm()" style="display: none;">Hủy</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Mã</th>
                                    <th>Tên</th>
                                    <td>Số sản phẩm</td>
                                    <th class="text-end">Xóa</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
                                    Map<Integer, Integer> productCountByCategory = (Map<Integer, Integer>) request.getAttribute("productCountByCategory");

                                    if (categoryList != null && !categoryList.isEmpty()) {
                                        for (Category category : categoryList) {
                                %>
                                <tr>
                                    <td onclick="populateForm('<%= category.getCategoryID() %>', '<%= category.getName() %>')"><%= category.getCategoryID() %></td>
                                    <td onclick="populateForm('<%= category.getCategoryID() %>', '<%= category.getName() %>')"><b><%= category.getName() %></b></td>
                                    <td>
                                        <%= productCountByCategory.getOrDefault(category.getCategoryID(), 0) %>
                                    </td>
                                    <td class="text-end">
                                        <button class="btn btn-light rounded btn-sm font-sm">
                                            <a href="categoryDelete?categoryID=<%=category.getCategoryID()%>"><i class="material-icons md-delete"></i>Xóa</a>
                                        </button>
                                    </td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="4" class="text-center">Không tìm thấy loại sản phẩm nào</td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Script to populate the form -->
    <script>
        function populateForm(categoryID, categoryName) {
            document.getElementById("product_name").value = categoryName;
            document.getElementById("category_id").value = categoryID;
            document.getElementById("submit_button").innerText = "Cập nhật loại sản phẩm";

            document.getElementById("cancel_button").style.display = "block";
        }

        function validateForm() {
            const nameField = document.getElementById("product_name");
            if (nameField.value.trim() === "") {
                alert("Please enter a category name.");
                return false;
            }
            return true;
        }

        function resetForm() {
            document.getElementById("product_name").value = "";
            document.getElementById("category_id").value = "";
            document.getElementById("submit_button").innerText = "Create category";
            document.getElementById("cancel_button").style.display = "none";
        }
    </script>

    <%
        if (categoryList != null) {
            for (Category category : categoryList) {
    %>
    <div class="modal fade" id="deleteModal<%= category.getCategoryID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete the category "<%= category.getName() %>"?
                </div>
                <div class="modal-footer">
                    <form action="categoryDelete" method="post">
                        <input type="hidden" name="categoryID" value="<%= category.getCategoryID() %>">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%
            }
        }
    %>

    <!-- content-main end// -->
    <footer class="main-footer font-xs">
        <div class="row pb-30 pt-15">
            <div class="col-sm-6">
                <script>
                    document.write(new Date().getFullYear());
                </script>
                &copy; Nest - HTML Ecommerce Template .
            </div>
            <div class="col-sm-6">
                <div class="text-sm-end">All rights reserved</div>
            </div>
        </div>
    </footer>
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<script src="nest-backend/assets/js/vendors/chart.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
<script src="nest-backend/assets/js/custom-chart.js" type="text/javascript"></script>
</body>
</html>
