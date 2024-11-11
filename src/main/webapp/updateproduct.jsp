<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page import="dal.*" %>
<%@ page import="common.Host" %><%--
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
  int productID = Integer.parseInt(request.getParameter("productid"));
  Product product = new ProductDAO().getProductByID(productID);
  if (msg==null) {
    msg = "";
  }
  List<Category> categories = new CategoryDAO().getAllCategories();
  List<Manufacturer> manufacturers = new ManufacturerDAO().getAllManufacturer();
  List<Origin> origins = new OriginDAO().getAllOrigins();
  List<Unit> units = new UnitDAO().getAllUnit();
  List<Certification> certifications = new CertificationDAO().getAllCertification();
  List<ProductStatus> statuses = new ProductStatusDAO().getAllProductStatus();
  ProductImgDAO productImgDAO = new ProductImgDAO();
  List<ProductImg> productImgs = productImgDAO.getNotDefaultImg(productID);
  ProductImg defaultImg = productImgDAO.getDefaultImg(productID);
  if(defaultImg!=null) productImgs.add(0, productImgDAO.getDefaultImg(productID));
  ImgDAO imgDAO = new ImgDAO();
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
        <h2 class="content-title card-title">Cập nhật sản phẩm</h2>
        <h5 style="color:red"><%=msg%></h5>
      </div>
      <div>
        <a href="LoadProductStaffServlet" class="btn btn-primary">Quay lại</a>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-3">
        <div class="card mb-4">
          <form action="UpdateProductStaffServlet" method="post" >
            <%--                        enctype="multipart/form-data"--%>
            <div class="card-body">
                <input type="hidden" name="productid" id="productid">
              <div>
                <label class="form-label">Tên sản phẩm</label>
                <input type="text" placeholder="Type here" class="form-control" name="name" id="name" required>
              </div>
              <div>
                <label class="form-label">Giá</label>
                <input type="number" placeholder="Type here" class="form-control" name="price" minlength="1" id="price" required>
              </div>
              <div>
                <label class="form-label">Trọng lượng</label>
                <input type="number" placeholder="Type here" class="form-control" name="weight" minlength="1" id="weight" required>
              </div>
              <div>
                <label class="form-label">Mô tả</label>
                <textarea placeholder="Type here" class="form-control" rows="4" name="detail" id="detail" required></textarea>
              </div>
              <div>
                <label class="form-label">Phân loại</label>
                <select class="form-select" name="categoryid" id="categoryid">
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
                <select class="form-select" name="manufacturerid" id="manufacturerid">
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
                <select class="form-select" name="originid" id="originid">
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
                <select class="form-select" name="unitid" id="unitid">
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
                <select class="form-select" name="certificationid" id="certificationid">
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
                <select class="form-select" name="statusid" id="statusid">
                  <%
                    for(ProductStatus status: statuses) {
                  %>
                  <option value="<%=status.getStatusID()%>"><%=status.getDetail()%></option>
                  <%
                    }
                  %>
                </select>
              </div>
              <%--                            <div>--%>
              <%--                                <label class="form-label">Images</label>--%>
              <%--                                <input class="form-control" type="file" name="img" id="imagefile" accept="image/gif, image/jpeg, image/png" required />--%>
              <%--                            </div>--%>
            </div>
            <button class="btn btn-md rounded font-sm hover-up" type="submit">Cập nhật</button>
          </form>
        </div>
      </div>
      <div class="col-lg-9">
        <form action="AddProductImgStaffServlet?productid=<%=productID%>" method="post" enctype="multipart/form-data">
          <label class="form-label">Ảnh</label>
          <input class="form-control" type="file" name="img" accept="image/gif, image/jpeg, image/png" required />
          <button class="btn btn-md rounded font-sm hover-up" type="submit">Thêm ảnh</button>
        </form>
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th>ID</th>
              <th>Ảnh</th>
              <th>Chính</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <%
              for (ProductImg productImg: productImgs) {
            %>
            <tr>
              <th><%=productImg.getImgID()%></th>
              <th><img src="<%=Host.IMG_LINK+imgDAO.getImgById(productImg.getImgID()).getImglink()%>?raw=true" style="max-height: 200px;min-height: 200px"></th>
              <th><%=productImg.getIsDefault()==1 ? "Ảnh chính" : "Ảnh phụ"%></th>
              <th>
                <a href="UpdateProductImgStaffServlet?imgid=<%=productImg.getImgID()%>&productid=<%=productID%>" class="btn btn-primary">Đặt làm ảnh chính</a>
                <a href="DeleteProductImgStaffServlet?imgid=<%=productImg.getImgID()%>&productid=<%=productID%>" class="btn btn-primary">Xóa</a>
              </th>
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
  </section> <!-- content-main end// -->
</main>
<script>
  document.getElementById("productid").value = "<%=product.getProductID()%>";
  document.getElementById("name").value = "<%=product.getName()%>";
  document.getElementById("price").value = "<%=product.getPrice()%>";
  document.getElementById("weight").value = "<%=product.getWeight()%>";
  document.getElementById("detail").value = "<%=product .getDetail()%>";
  document.getElementById("categoryid").value = "<%=product.getCategoryID()%>";
  document.getElementById("manufacturerid").value = "<%=product.getManufacturerID()%>";
  document.getElementById("originid").value = "<%=product.getOriginID()%>";
  document.getElementById("unitid").value = "<%=product.getUnitID()%>";
  document.getElementById("certificationid").value = "<%=product.getCertificationID()%>";
  document.getElementById("statusid").value = "<%=product.getStatusID()%>";
</script>
<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<!-- Main Script -->
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>

</html>