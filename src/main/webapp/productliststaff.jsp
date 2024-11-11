<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page import="dal.*" %>
<%@ page import="common.Host" %>
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
        <h2 class="content-title card-title">Sản phẩm</h2>
        <%
          String msg = request.getParameter("msg");
            if (msg==null) {
              msg = "";
            }
        %>
        <h5 style="color:red"><%=msg%></h5>
      </div>
      <div>
        <input type="text" id="search" placeholder="Search Product" class="form-control bg-white" />
        <a href="addproduct.jsp" class="btn btn-primary"><i class="material-icons md-plus"></i>Tạo mới</a>
      </div>
    </div>
    <div class="card">
      <div class="card-body">
        <div class="row">
          <div class="">
            <div class="table-responsive">
              <table class="table table-hover">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Ảnh</th>
                  <th>Tên</th>
                  <th>Giá</th>
                  <th>Trọng lượng</th>
                  <th>Phân loại</th>
                  <th>Nhà sản xuất</th>
                  <th>Nguồn gốc</th>
                  <th>Đơn vị</th>
                  <th>Chứng chỉ</th>
                  <th>Trạng thái</th>
                  <th></th>
                </tr>
                </thead>
                <tbody>
                <%
                  List<Product> products = (List<Product>) request.getAttribute("products");
                  CategoryDAO categoryDAO = new CategoryDAO();
                  ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
                  OriginDAO originDAO = new OriginDAO();
                  UnitDAO unitDAO = new UnitDAO();
                  CertificateDAO certificateDAO = new CertificateDAO();
                  ProductImgDAO productImgDAO = new ProductImgDAO();
                  ProductStatusDAO productStatusDAO = new ProductStatusDAO();
                  ImgDAO imgDAO = new ImgDAO();
                  for (Product product : products) {
                %>
                <tr>
                  <th><%=product.getProductID()%></th>
                  <%
                    ProductImg productImg = productImgDAO.getDefaultImg(product.getProductID());
                    if(productImg!=null){
                  %>
                  <th><img src="<%=Host.IMG_LINK+imgDAO.getImgById(productImg.getImgID()).getImglink()%>?raw=true" style="max-height: 200px;min-height: 200px"></th>
                  <%
                      }else{
                  %>
                  <th><img src=""></th>
                  <%
                      }
                  %>
                  <th><%=product.getName()%></th>
                  <th><%=product.getPrice()%></th>
                  <th><%=product.getWeight()%></th>
                  <th><%=categoryDAO.getCategoryById(product.getCategoryID()).getName()%></th>
                  <th><%=manufacturerDAO.getManufacturerByID(product.getManufacturerID()).getName()%></th>
                  <th><%=originDAO.getOriginById(product.getOriginID()).getName()%></th>
                  <th><%=unitDAO.getUnitByID(product.getUnitID()).getName()%></th>
                  <th><%=certificateDAO.getCertificationById(product.getCertificationID()).getName()%></th>
                  <th><%=productStatusDAO.getProductStatusById(product.getStatusID()).getDetail()%></th>
                  <th>
                    <a href="productFeedback?productid=<%=product.getProductID()%>" class="btn btn-sm btn-brand rounded font-sm mt-15">Xem đánh giá</a>
                    <a href="updateproduct.jsp?productid=<%=product.getProductID()%>" class="btn btn-sm btn-brand rounded font-sm mt-15">Cập nhật</a>
                    <a href="DeleteProductStaffServlet?productid=<%=product.getProductID()%>" class="btn btn-sm btn-brand rounded font-sm mt-15">Xóa</a>
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
    </div>
  </section>

</main>
<script>
  document.getElementById('search').addEventListener('input', function() {
    var searchQuery = this.value.toLowerCase();
    var rows = document.querySelectorAll('tbody tr');

    rows.forEach(function(row) {
      var productName = row.querySelector('th:nth-child(3)').textContent.toLowerCase();
      if (productName.includes(searchQuery)) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    });
  });
</script>
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
