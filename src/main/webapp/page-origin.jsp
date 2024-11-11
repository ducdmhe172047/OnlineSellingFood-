<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Origin" %>
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
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
  <jsp:param name="page" value="origin"/>
  <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
  <jsp:include page="header-staff.jsp"></jsp:include>
  <section class="content-main">
    <div class="content-header">
      <div>
        <h2 class="content-title card-title">Nguồn gốc sản phẩm</h2>
        <p>Danh sách nguồn gốc sản phẩm</p>
      </div>
      <div>
        <form action="originSearch" method="post">
          <input type="text" name="searchKeyword" placeholder="Tìm kiếm nguồn gôốc" class="form-control bg-white" />
          <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </form>
      </div>
    </div>
    <div class="card">
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
            <tr>
              <th>Mã</th>
              <th>Tên</th>
              <th id="sort-products" style="cursor: pointer;">Số sản phẩm</th>
            </tr>
            </thead>
            <tbody>
            <%
              List<Origin> originList = (List<Origin>) request.getAttribute("originList");
              Map<Integer, Integer> originCounts = (Map<Integer, Integer>) request.getAttribute("originCounts");
              if (originList != null && !originList.isEmpty()) {
                for (Origin origin : originList) {
                  int productCount = originCounts.getOrDefault(origin.getOriginID(), 0);
            %>
            <tr>
              <td><%= origin.getOriginID() %></td>
              <td><b><%= origin.getName() %></b></td>
              <td><%= productCount %></td>
            </tr>
            <%
              }
            } else {
            %>
            <tr>
              <td colspan="3" class="text-center">Không tìm thấy nguồn gốc sản phẩm nào</td>
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
                  <a class="page-link" href="originList?page=<%= currentPage - 1 %>">Previous</a>
                </li>
                <%
                  }
                  for (int i = 1; i <= totalPages; i++) {
                %>
                <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                  <a class="page-link" href="originList?page=<%= i %>"><%= i %></a>
                </li>
                <%
                  }
                  if (currentPage < totalPages) {
                %>
                <li class="page-item">
                  <a class="page-link" href="originList?page=<%= currentPage + 1 %>">Next</a>
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
  </section>


  <!-- content-main end// -->
  <footer class="main-footer font-xs">
    <div class="row pb-30 pt-15">
      <div class="col-sm-6">
        <script>
          document.write(new Date().getFullYear());

            let sortState = 0; // 0 = original, 1 = descending, 2 = ascending
            const originalRows = Array.from(document.querySelectorAll("table tbody tr"));

            document.getElementById("sort-products").addEventListener("click", function() {
            const table = document.querySelector("table tbody");
            const rows = Array.from(table.rows);
            let sortedRows;

            if (sortState === 0) {
            // Sort descending
            sortedRows = rows.sort((a, b) => {
            const countA = parseInt(a.cells[2].textContent);
            const countB = parseInt(b.cells[2].textContent);
            return countB - countA; // Descending
          });
            sortState = 1;
          } else if (sortState === 1) {
            // Sort ascending
            sortedRows = rows.sort((a, b) => {
            const countA = parseInt(a.cells[2].textContent);
            const countB = parseInt(b.cells[2].textContent);
            return countA - countB; // Ascending
          });
            sortState = 2;
          } else {
            // Revert to original order
            sortedRows = originalRows;
            sortState = 0;
          }
            table.innerHTML = "";
            sortedRows.forEach(row => table.appendChild(row));
          });
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
