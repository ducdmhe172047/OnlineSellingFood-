<%@ page import="java.util.List" %>
<%@ page import="model.Unit" %>
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
    <jsp:param name="menu" value="unit"/>
</jsp:include>
<%
    List<Unit> units = (List<Unit>) request.getAttribute("units");
    String msg  = request.getParameter("msg");
    if(msg==null) msg="";
%>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Đơn vị</h2>
                <p>Thêm sửa xóa đơn vị</p>
            </div>
            <div>
                <input class="form-control bg-white" type="text" id="myinput" onkeyup="myFunction()" placeholder="Tra cứu">
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <h6 style="color: red"><%=msg%></h6>
                        <form action="createunit" method="get" onsubmit="return validateForm()">
                            <div class="mb-4">
                                <label class="form-label">Mã đơn vị</label>
                                <input type="text" class="form-control" id="unitid" name="unitid" readonly />
                            </div>
                            <div class="mb-4">
                                <label class="form-label">Đơn vị</label>
                                <input type="text" placeholder="Type here" class="form-control" id="unitname" name="unitname" required />
                            </div>

                            <div class="mb-4" id="conversionratediv">
                                <label class="form-label">Tỉ lệ chuyển đổi</label>
                                <input type="number" placeholder="Type here" class="form-control" id="conversionrate" name="conversionrate" required min="1"/>
                            </div>

                            <div class="mb-4" id="baseunitdiv">
                                <label class="form-label">Đơn vị cơ bản</label>
                                <select class="form-control" id="baseunitid" name="baseunitid" required>
                                    <%
                                        for (Unit unit : units) {
                                    %>
                                    <option value="<%=unit.getUnitID()%>"><%= unit.getName() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary" id="submit_button">Tạo đơn vị mới</button>
                                <button type="button" class="btn btn-secondary mt-2" id="cancel_button" style="display: none;"><a href="unitlist">Hủy</a></button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <table class="table table-hover" id="mytable">
                                <thead>
                                <tr>
                                    <th>Mã</th>
                                    <th>Đơn vị</th>
                                    <th>Tỉ lệ chuyển đổi</th>
                                    <th>Đơn vị cơ bản</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    for (Unit unit : units) {
                                %>
                                <tr onclick="populateForm('<%=unit.getUnitID()%>', '<%=unit.getName()%>', '<%=unit.getConversionRate()%>', '<%=unit.getBaseUnitID()%>')">
                                    <td><%=unit.getUnitID()%></td>
                                    <td><%=unit.getName()%></td>
                                    <td><%=unit.getConversionRate()%></td>
                                    <td><%=unit.getBaseUnitID()%></td>
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

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
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
        function populateForm(unitid, name, conversionrate, baseunitid) {
            document.getElementById("unitid").value = unitid;
            document.getElementById("unitname").value = name;
            if(conversionrate==='null'){
                document.getElementById("conversionratediv").setAttribute("hidden","");
            }else{
                document.getElementById("conversionrate").value = conversionrate;
                document.getElementById("conversionratediv").removeAttribute("hidden");
            }
            if(baseunitid==='null'){
                document.getElementById("baseunitdiv").setAttribute("hidden","");
            }else{
                document.getElementById("baseunitid").value = baseunitid;
                document.getElementById("baseunitdiv").removeAttribute("hidden");
            }
            document.getElementById("submit_button").innerText = "Update Unit";

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
