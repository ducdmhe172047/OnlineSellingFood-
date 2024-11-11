<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page import="dal.*" %><%--
  Created by IntelliJ IDEA.
  User: anh21
  Date: 10/31/2024
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Thương mại điện tử</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta property="og:title" content="">
    <meta property="og:type" content="">
    <meta property="og:url" content="">
    <meta property="og:image" content="">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg">
    <!-- Template CSS -->
    <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
</head>
<%
    String msg = request.getParameter("msg");
    if (msg==null) {
        msg = "";
    }
    List<Category> categories = new CategoryDAO().getAllCategories();
    List<Manufacturer> manufacturers = new ManufacturerDAO().getAllManufacturer();
    List<Origin> origins = new OriginDAO().getAllOrigins();
    List<Unit> units = new UnitDAO().getAllUnit();
    List<Certification> certifications = new CertificationDAO().getAllCertification();
    List<ProductStatus> statuses = new ProductStatusDAO().getAllProductStatus();
%>
<body>
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="productList"/>
    <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Thêm sản phẩm mới</h2>
                <h5 style="color:red"><%=msg%></h5>
            </div>
            <div>
                <a href="LoadProductStaffServlet" class="btn btn-primary">Quay lại</a>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <div class="card mb-4">
                    <form action="AddProductStaffServlet" method="post" enctype="multipart/form-data">
                        <div class="card-body">
                            <div>
                                <label class="form-label">Tên sản phẩm</label>
                                <input type="text" placeholder="Type here" class="form-control" name="name" required>
                            </div>
                            <div>
                                <label class="form-label">Giá</label>
                                <input type="number" placeholder="Type here" class="form-control" name="price" minlength="1" required>
                            </div>
                            <div>
                                <label class="form-label">Trọng lượng</label>
                                <input type="number" placeholder="Type here" class="form-control" name="weight" minlength="1" required>
                            </div>
                            <div>
                                <label class="form-label">Mô tả</label>
                                <textarea placeholder="Type here" class="form-control" rows="4" name="detail" required></textarea>
                            </div>
                            <div>
                                <label class="form-label">Phân loại</label>
                                <select class="form-select" name="categoryid">
                                    <%
                                        for(Category category: categories) {
                                    %>
                                    <option value="<%=category.getCategoryID()%>"><%=category.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div>
                                <label class="form-label">Nhà sản xuất</label>
                                <select class="form-select" name="manufacturerid">
                                    <%
                                        for(Manufacturer manufacturer: manufacturers) {
                                    %>
                                    <option value="<%=manufacturer.getManufacturerID()%>"><%=manufacturer.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div>
                                <label class="form-label">Nguồn gốc</label>
                                <select class="form-select" name="originid">
                                    <%
                                        for(Origin origin: origins) {
                                    %>
                                    <option value="<%=origin.getOriginID()%>"><%=origin.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div>
                                <label class="form-label">Đơn vị</label>
                                <select class="form-select" name="unitid">
                                    <%
                                        for(Unit unit: units) {
                                    %>
                                    <option value="<%=unit.getUnitID()%>"><%=unit.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div>
                                <label class="form-label">Chứng chỉ</label>
                                <select class="form-select" name="certificationid">
                                    <%
                                        for(Certification certification: certifications) {
                                    %>
                                    <option value="<%=certification.getCertificationID()%>"><%=certification.getName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div>
                                <label class="form-label">Trạng thái</label>
                                <select class="form-select" name="statusid">
                                    <%
                                        for(ProductStatus status: statuses) {
                                    %>
                                    <option value="<%=status.getStatusID()%>"><%=status.getDetail()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div>
                                <label class="form-label">Ảnh</label>
                                <input class="form-control" type="file" name="img" id="imagefile" accept="image/gif, image/jpeg, image/png" required />
                            </div>
                        </div>
                        <button class="btn btn-md rounded font-sm hover-up" type="submit">Tạo mới</button>
                    </form>
                </div>
            </div>
        </div>
        </div>
    </section> <!-- content-main end// -->
</main>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>

</html>