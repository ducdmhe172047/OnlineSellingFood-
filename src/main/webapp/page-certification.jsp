<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Certification" %>
<%@ page import="model.CertificateIssuer" %>
<%@ page import="dal.ImgDAO" %>
<%@ page import="dal.CertificateIssuerDAO" %>
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
<%
    String msg = (String)session.getAttribute("msg");
    if(msg==null) msg="";
    session.removeAttribute("msg");
%>
<div class="screen-overlay"></div>
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="certification"/>
    <jsp:param name="menu" value="product"/>
</jsp:include>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Chứng chỉ</h2>
                <p>Tạo, sửa, xóa chứng chỉ sản phẩm</p>
            </div>
            <div>
                <form action="certificationSearch" method="post">
                    <input type="text" name="searchKeyword" placeholder="Tìm kiếm chứng chỉ" class="form-control bg-white" />
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <form action="certificationCU" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                            <div class="mb-4">
                                <label for="certificate_name" class="form-label">Tên</label>
                                <input type="text" placeholder="Tên chứng chỉ" class="form-control" id="certificate_name" name="name" required />
                                <input type="hidden" id="certification_id" name="certificationID" value="" />
                            </div>
                            <div class="mb-4">
                                <label for="certificate_detail" class="form-label">Chi tiết</label>
                                <input type="text" placeholder="Mô tả chứng chỉ" class="form-control" id="certificate_detail" name="detail" required />
                            </div>
                            <div class="mb-4">
                                <label for="certificate_issuer" class="form-label">Nhà phát hành chứng chỉ</label>
                                <select class="form-control" id="certificate_issuer" name="certificateIssuerID" required>
                                    <%
                                        List<CertificateIssuer> issuerList = (List<CertificateIssuer>) request.getAttribute("issuerList");
                                        if (issuerList != null && !issuerList.isEmpty()) {
                                            for (CertificateIssuer issuer : issuerList) {
                                    %>
                                    <option value="<%= issuer.getCertificateIssuerID() %>"><%= issuer.getName() %></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="mb-4">
                                <label class="form-label">Ảnh</label>
                                <input class="form-control" type="file" name="img" id="imagefile" accept="image/gif, image/jpeg, image/png" required />
                            </div>
                            <h5 style="color: red"><%= msg != null ? msg : "" %></h5>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary" >Tạo chứng chỉ mới</button>
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
                                    <th>Chi tiết</th>
                                    <th>Nhà phát hành</th>
                                    <th>Ảnh</th>
                                    <th class="text-end">Xóa</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<Certification> certificationList = (List<Certification>) request.getAttribute("certificationList");
                                    if (certificationList != null && !certificationList.isEmpty()) {
                                        ImgDAO imgdao = new ImgDAO();
                                        for (Certification certification : certificationList) {
                                %>
                                <tr>
                                    <td onclick="populateForm('<%= certification.getCertificationID() %>', '<%= certification.getName() %>', '<%= certification.getDetail() %>', '<%= certification.getImgID() %>', '<%= certification.getCertificateIssuerID() %>')"><%= certification.getCertificationID() %></td>
                                    <td onclick="populateForm('<%= certification.getCertificationID() %>', '<%= certification.getName() %>', '<%= certification.getDetail() %>', '<%= certification.getImgID() %>', '<%= certification.getCertificateIssuerID() %>')">
                                        <b><%= certification.getName() %></b></td>
                                    <td><%= certification.getDetail() %></td>
                                    <%
                                        CertificateIssuerDAO certificateIssuerDAO = new CertificateIssuerDAO();
                                        CertificateIssuer issuer = certificateIssuerDAO.getCertificateIssuerById(certification.getCertificateIssuerID());
                                    %>
                                    <td><%=  issuer.getName() %></td>
                                    <th><img src="<%=Host.IMG_LINK+imgdao.getImgById(certification.getImgID()).getImglink()%>?raw=true" style="max-height: 200px;"></th>
                                    <td class="text-end">
                                        <button class="btn btn-light rounded btn-sm font-sm">
                                            <a href="certificationDelete?certificationID=<%= certification.getCertificationID() %>"><i class="material-icons md-delete"></i>Xóa</a>
                                        </button>
                                    </td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="6" class="text-center">Không tìm thấy chứng chỉ</td>
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
        function populateForm(certificationID, name, detail, imgID, certificateIssuerID) {
            document.getElementById("certificate_name").value = name;
            document.getElementById("certificate_detail").value = detail;
            document.getElementById("certification_id").value = certificationID;
            document.getElementById("certificate_issuer").value = certificateIssuerID;

            // Cập nhật hình ảnh
            const imgTag = document.getElementById("imagefile");
            imgTag.src = imgID;

            document.getElementById("submit_button").innerText = "Cập nhật chứng chỉ";
            document.getElementById("cancel_button").style.display = "block";
        }

        function validateForm() {
            const nameField = document.getElementById("certificate_name");
            const detailField = document.getElementById("certificate_detail");
            if (nameField.value.trim() === "" || detailField.value.trim() === "") {
                alert("Hãy điền toàn bộ thông tin vào phần input");
                return false;
            }
            return true;
        }

        function resetForm() {
            document.getElementById("certificate_name").value = "";
            document.getElementById("certificate_detail").value = "";
            document.getElementById("image_link").value = "";
            document.getElementById("certification_id").value = "";
            document.getElementById("submit_button").innerText = "Create certification";
            document.getElementById("cancel_button").style.display = "none";
        }

        document.getElementById("submit_button").onclick = function() {
            if (document.getElementById("certification_id").value === "") {
                alert("No certification selected for update.");
                return false;  // Ngăn form submit nếu không có giá trị ID
            }
        };

    </script>


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
