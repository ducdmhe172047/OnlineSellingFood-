<%@ page import="java.util.List" %>
<%@ page import="model.ContactInformation" %>
<%@ page import="model.WarehouseStatus" %>
<%@ page import="model.Warehouse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Thương mại điện tử</title>
  <meta http-equiv="x-ua-compatible" content="ie=edge" />
  <meta name="description" content="" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg" />
  <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
  <style>
    .pagination a.active {
      background-color: forestgreen;
      color: white;
      text-decoration: underline;
    }
    .pagination a {
      margin-right: 10px;
      color: #000;
    }
    .pagination span {
      margin-right: 10px;
    }
  </style>
</head>

<body>
<%
  String msg = (String)session.getAttribute("msg");
  if(msg==null) msg="";
  session.removeAttribute("msg");
%>
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
  <jsp:param name="page" value=""/>
  <jsp:param name="menu" value="warehouse"/>
</jsp:include>
<main class="main-wrap">
  <jsp:include page="header-staff.jsp"></jsp:include>
  <section class="content-main">
    <div class="content-header">
      <div>
        <h2 class="content-title card-title">Kho hàng</h2>
        <p>Sửa, thêm, tìm kiếm kho hàng</p>
      </div>
      <div>
        <form action="warehouseList" method="get">
          <input type="text" name="search" placeholder="Tìm kiếm kho" class="form-control bg-white" />
          <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </form>
      </div>
    </div>
    <div class="card">
      <div class="card-body">
        <div class="row">
          <div class="col-md-3">
            <form action="warehouseCU" method="post" onsubmit="return validateForm()">
              <div class="mb-4">
                <label for="warehouse_name" class="form-label">Tên</label>
                <input type="text" placeholder="Tên kho" class="form-control" id="warehouse_name" name="name" required />
                <input type="hidden" id="warehouse_id" name="warehouseID" />
              </div>
              <div class="mb-4">
                <label for="address" class="form-label">Địa chỉ</label>
                <input type="text" placeholder="Địa chỉ kho" class="form-control" id="address" name="address" required />
              </div>
              <div class="mb-4">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input type="text" placeholder="Số điện thoại liên lạc" class="form-control" id="phone" name="phone" required />
              </div>

              <input type="text" hidden class="form-control" id="contactID" name="contactID"/>
              <div class="mb-4">
                <label for="status" class="form-label">Trạng thái</label>
                <select class="form-control" id="status" name="statusID" required>
                  <option value="" disabled selected>Chọn trạng thái</option>
                  <%
                    List<WarehouseStatus> statusList = (List<WarehouseStatus>) request.getAttribute("statusList");
                    if (statusList != null) {
                      for (WarehouseStatus status : statusList) {
                  %>
                  <option value="<%= status.getStatusID() %>"><%= status.getDetail() %></option>
                  <%
                      }
                    }
                  %>
                </select>
              </div>

              <h5 style="color: red"><%= msg != null ? msg : "" %></h5>

              <div class="d-grid">
                <button type="submit" class="btn btn-primary" id="submit_button">Tạo kho hàng</button>
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
                  <th>Địa chỉ</th>
                  <th>Số điện thoại</th>
                  <th>Trạng thái</th>
                </tr>
                </thead>
                <tbody>
                <%
                  List<Warehouse> warehouseList = (List<Warehouse>) request.getAttribute("warehouseList");
                  if (warehouseList != null && !warehouseList.isEmpty()) {
                    for (Warehouse warehouse : warehouseList) {
                      ContactInformation contactInfo = (ContactInformation) request.getAttribute("contactInfo_" + warehouse.getContactInformationID());
                      WarehouseStatus status = (WarehouseStatus) request.getAttribute("status_" + warehouse.getStatusID());
                %>
                <tr>
                  <td onclick="populateForm('<%= warehouse.getWarehouseID() %>', '<%= warehouse.getName() %>', '<%= contactInfo.getAddress() %>', '<%= contactInfo.getPhoneNumber() %>', '<%= status.getStatusID() %>','<%= contactInfo.getContactInformationID() %>')">
                    <%= warehouse.getWarehouseID() %></td>
                  <td><%= warehouse.getName() %></td>
                  <td><%= contactInfo.getAddress() %></td>
                  <td><%= contactInfo.getPhoneNumber() %></td>
                  <td><%= status.getDetail() %></td>
                </tr>
                <%
                  }
                } else {
                %>
                <tr>
                  <td colspan="6" class="text-center">Không tìm thấy kho nào</td>
                </tr>
                <%
                  }
                %>
                </tbody>
              </table>
              <div class="pagination-controls">
                <nav aria-label="Page navigation">
                  <ul class="pagination">
                    <%
                      int currentPage = (int) request.getAttribute("currentPage");
                      int totalPages = (int) request.getAttribute("totalPages");

                      if (currentPage > 1) {
                    %>
                    <li class="page-item">
                      <a class="page-link" href="certificateIssuerList?page=<%= currentPage - 1 %>">Previous</a>
                    </li>
                    <%
                      }
                      for (int i = 1; i <= totalPages; i++) {
                    %>
                    <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                      <a class="page-link" href="certificateIssuerList?page=<%= i %>"><%= i %></a>
                    </li>
                    <%
                      }
                      if (currentPage < totalPages) {
                    %>
                    <li class="page-item">
                      <a class="page-link" href="certificateIssuerList?page=<%= currentPage + 1 %>">Next</a>
                    </li>
                    <%
                      }
                    %>
                  </ul>
                </nav>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <script>
    function populateForm(warehouseID, warehouseName, address, phone, statusID, contactID) {
      document.getElementById("warehouse_name").value = warehouseName;
      document.getElementById("address").value = address;
      document.getElementById("phone").value = phone;
      document.getElementById("status").value = statusID;
      document.getElementById("contactID").value = contactID;
      document.getElementById("warehouse_id").value = warehouseID;
      document.getElementById("submit_button").innerText = "Sửa kho";

      document.getElementById("cancel_button").style.display = "block";
    }

    function validateForm() {
      const nameField = document.getElementById("warehouse_name");
      if (nameField.value.trim() === "") {
        alert("Please enter a warehouse name.");
        return false;
      }
      return true;
    }

    function resetForm() {
      document.getElementById("warehouse_name").value = "";
      document.getElementById("address").value = "";
      document.getElementById("phone").value = "";
      document.getElementById("status").value = "";
      document.getElementById("warehouse_id").value = "";
      document.getElementById("submit_button").innerText = "Create Warehouse";
      document.getElementById("cancel_button").style.display = "none";
    }
  </script>

  <footer class="main-footer font-xs">
    <div class="row pb-30 pt-15">
      <div class="col-sm-6">
        <script>
          document.write(new Date().getFullYear());
        </script>
        &copy; Nest - HTML Ecommerce Template.
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
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
<script src="nest-backend/assets/js/custom-chart.js" type="text/javascript"></script>
</body>
</html>
