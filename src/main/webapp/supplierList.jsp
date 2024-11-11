<%@ page import="java.util.List" %>
<%@ page import="model.Unit" %>
<%@ page import="model.Supplier" %>
<%@ page import="dal.ContactInformationDAO" %>
<%@ page import="model.ContactInformation" %>
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
  <jsp:param name="page" value=""/>
  <jsp:param name="menu" value="supplier"/>
</jsp:include>
<%
  List<Supplier> suppliers = (List<Supplier>) request.getAttribute("supplierList");
  ContactInformationDAO contactInformationDAO = new ContactInformationDAO();
  String msg  = request.getParameter("msg");
  if(msg==null) msg="";

%>
<main class="main-wrap">
  <jsp:include page="header-staff.jsp"></jsp:include>
  <section class="content-main">
    <div class="content-header">
      <div>
        <h2 class="content-title card-title">Nhà cung cấp</h2>
        <p>Thêm sửa xóa Nhà cung cấp</p>
      </div>
      <div>
        <select id="searchType" class="form-control bg-white">
          <option value="0">ID</option>
          <option value="1">Nhà cung cấp</option>
          <option value="2">Địa chỉ</option>
          <option value="3">Số điện thoại</option>
          <option value="4">Ghi chú</option>
        </select>
        <input class="form-control bg-white" type="text" id="myinput" onkeyup="myFunction()" placeholder="Tra cứu">
      </div>
    </div>
    <div class="card">
      <div class="card-body">
        <div class="row">
          <div class="col-md-3">
            <h6 style="color: red"><%=msg%></h6>
            <form action="suppliercreate" method="post" id="form">
              <div class="mb-4">
                <label class="form-label">ID</label>
                <input type="text" class="form-control" id="supplierID" name="supplierID" readonly />
              </div>
              <div class="mb-4">
                <label class="form-label">Nhà cung cấp</label>
                <input type="text" placeholder="Type here" class="form-control" id="name" name="name" required maxlength="100"/>
              </div>

              <div class="mb-4">
                <label class="form-label">Địa chỉ</label>
                <input type="text" placeholder="Type here" class="form-control" id="address" name="address" required maxlength="200"/>
              </div>

              <div class="mb-4" >
                <label class="form-label">Số điện thoại</label>
                <input type="text" placeholder="Type here" class="form-control" id="phonenumber" name="phonenumber" required minlength="10" maxlength="10"/>
              </div>

              <div class="mb-4">
                <label class="form-label">Ghi chú</label>
                <input type="text" placeholder="Type here" class="form-control" id="note" name="note" required maxlength="1000"/>
              </div>

              <div class="d-grid">
                <button type="submit" class="btn btn-primary" id="submit_button">Thêm nhà cung cấp mới</button>
                <button type="submit" class="btn btn-primary" id="update_button" hidden>Cập nhật</button>
                <button type="button" class="btn btn-secondary mt-2" id="cancel_button" hidden><a href="supplierList">Hủy</a></button>
              </div>
            </form>
          </div>
          <div class="col-md-9">
            <div class="table-responsive">
              <table class="table table-hover" id="mytable">
                <thead>
                <tr>
                  <th><a href="supplierList?sort=0">ID</a></th>
                  <th><a href="supplierList?sort=1">Nhà cung cấp</a></th>
                  <th><a href="">Địa chỉ</a></th>
                  <th><a href="">Số điện thoại</a></th>
                  <th><a href="supplierList?sort=5">Ghi chú</a></th>
                </tr>
                </thead>
                <tbody>
                <%
                  ContactInformation contactInformation;
                  for (Supplier supplier : suppliers) {
                    contactInformation = contactInformationDAO.getContactInformationByContactID(supplier.getContactInformationID());
                %>
                <tr onclick="populateForm('<%=supplier.getSupplierID()%>', '<%=supplier.getName()%>', '<%=contactInformation.getAddress()%>', '<%=contactInformation.getPhoneNumber()%>', '<%=supplier.getNote()%>')">
                  <td><%=supplier.getSupplierID()%></td>
                  <td><%=supplier.getName()%></td>
                  <td><%=contactInformation.getAddress()%></td>
                  <td><%=contactInformation.getPhoneNumber()%></td>
                  <td><%=supplier.getNote()%></td>
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
    function myFunction() {
      // Declare variables
      var input, filter, table, tr, td, i, txtValue;
      input = document.getElementById("myinput");
      filter = input.value.toUpperCase();
      table = document.getElementById("mytable");
      tr = table.getElementsByTagName("tr");
      searchType = document.getElementById("searchType").value;

      // Loop through all table rows, and hide those who don't match the search query
      for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[searchType];
        if (td) {
          txtValue = td.textContent || td.innerText;
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
          } else {
            tr[i].style.display = "none";
          }
        }
      }
    }
    function populateForm(id, name, address, phonenumber, note) {
      document.getElementById("supplierID").value = id;
      document.getElementById("name").value = name;
      document.getElementById("address").value = address;
      document.getElementById("phonenumber").value = phonenumber;
      document.getElementById("note").value = note;
      document.getElementById("submit_button").setAttribute("hidden", "");
      document.getElementById("update_button").removeAttribute("hidden");
      document.getElementById("cancel_button").removeAttribute("hidden");
      document.getElementById("form").action = "supplierupdate";
    }

    function validateForm() {
      const nameField = document.getElementById("warehouse_name");
      if (nameField.value.trim() === "") {
        alert("Please enter a warehouse name.");
        return false;
      }
      return true;
    }
  </script>

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
