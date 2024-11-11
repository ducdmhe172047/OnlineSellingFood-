<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dal.ManufacterDAO.TextTruncator" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="common.Host" %>
<%@ page import="model.ProductImg" %>
<%@ page import="dal.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Danh sách tin tức</title>
    <meta http-equiv="x-ua-compatible" content="ie=edge" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="shortcut icon" type="image/x-icon" href="nest-backend/assets/imgs/theme/favicon.svg" />
    <link href="nest-backend/assets/css/main.css?v=1.1" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:include page="bar-staff.jsp">
    <jsp:param name="page" value="listNew"/>
    <jsp:param name="menu" value="news"/>
</jsp:include>
<%
    String msg = (String)session.getAttribute("msg");
    if(msg==null) msg="";
    session.removeAttribute("msg");
%>
<main class="main-wrap">
    <jsp:include page="header-staff.jsp"></jsp:include>
    <section class="content-main">
        <div class="content-header">
            <div>
                <h2 class="content-title card-title">Tin tức</h2>
                <p>Tạo, sửa, xóa tin tức</p>
                <h6 style="color: red"><%=msg%></h6>
            </div>
            <div>
                <form action="newsSearch" method="post">
                    <input type="text" name="searchKeyword" placeholder="Tìm kiếm tin tức" class="form-control bg-white" id="search_keyword" />
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </form>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <%--create form                    --%>
                    <div class="col-md-3" id="create">
                        <form action="addNew" method="post" enctype="multipart/form-data">
                            <div class="mb-4">
                                <label for="news_title" class="form-label">Tiêu đề</label>
                                <input type="text" class="form-control" name="title" required />
                            </div>
                            <div class="mb-4">
                                <label for="news_content" class="form-label">Nội dung</label>
                                <textarea class="form-control" name="content" required></textarea>
                            </div>
                            <div class="mb-4">
                                <label class="form-label">Tải ảnh lên</label>
                                <input type="file" name="img" accept="image/gif, image/jpeg, image/png" required />
                            </div>
                            <div>
                                <label for="status">Status</label>
                                <select name="status" required>
                                    <option value="active">Active</option>
                                    <option value="nonactive">Nonactive</option>
                                </select>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Tạo</button>

                            </div>
                        </form>
                        <br>
                    </div>
                    <%--update form auto hide                    --%>
                    <div class="col-md-3" id="update" hidden>
                        <form action="newsupdate" method="post" enctype="multipart/form-data">
                            <div class="mb-4">
                                <label for="news_title" class="form-label">ID</label>
                                <input type="text" id="news_id" name="newsid" value="" readonly required>
                            </div>
                            <div class="mb-4">
                                <label for="news_title" class="form-label">Tiêu đề</label>
                                <input type="text" class="form-control" id="news_title" name="title" required />
                            </div>
                            <div class="mb-4">
                                <label for="news_content" class="form-label">Nội dung</label>
                                <textarea class="form-control" id="news_content" name="content" required></textarea>
                            </div>
                            <div>
                                <label for="status">Status</label>
                                <select id="status" name="status" required>
                                    <option value="active">Active</option>
                                    <option value="nonactive">Nonactive</option>
                                </select>
                            </div>
                            <div class="d-grid">
                                <button type="submit" id="submit_button" class="btn btn-primary">Cập nhật tin tức</button>
                            </div>
                        </form>
                        <br>
                        <div>
                            <a href="addNew" class="btn btn-primary">Hủy</a>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tiêu đề</th>
                                    <th>Nội dung</th>
                                    <th>Ảnh</th>
                                    <th>Nhân viên</th>
                                    <th>Thời gian</th>
                                    <th>Trạng thái</th>
                                    <th class="text-end">Xóa</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    AccountDAO accountDAO = new AccountDAO();
                                    StaffDAO staffDAO = new StaffDAO();
                                    ImgDAO imgDAO = new ImgDAO();
                                    String email;
                                    List<News> newsList = (List<News>) request.getAttribute("list");
                                    if (newsList != null && !newsList.isEmpty()) {
                                        for (News news : newsList) {
                                            email = accountDAO.getAccountByAccountID(staffDAO.getStaffByrID(news.getStaffID()).getAccountID()).getEmail();
                                %>
                                <tr onclick="populateForm('<%= news.getNewsID() %>', '<%= news.getTitle() %>', '<%= news.getContent() %>', '<%=news.getActive() ? "active" : "nonactive"%>')">
                                    <td><%= news.getNewsID() %></td>
                                    <td><%= news.getTitle() %></td>
                                    <td><%= news.getContent() %></td>
                                    <td><img src="<%=Host.IMG_LINK+imgDAO.getImgById(news.getImgID()).getImglink()%>?raw=true" style="max-height: 200px;"></td>
                                    <td><%=email%></td>
                                    <td><%=news.getTime()%></td>
                                    <td><%=news.getActive() ? "Hoạt động" : "Không hoạt động"%></td>
                                    <td class="text-end">
                                        <form action="newsDelete" method="post" style="display:inline;">
                                            <input type="hidden" name="newsid" value="<%=news.getNewsID()%>"/>
                                            <button class="btn btn-light rounded btn-sm font-sm"><i class="material-icons md-delete"></i>Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="7" class="text-center">Không tìm thấy tin tức</td>
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
    function populateForm(newsID, title, content, status) {
        document.getElementById("news_title").value = title;
        document.getElementById("news_content").value = content;
        document.getElementById("news_id").value = newsID;
        document.getElementById("status").value = status;
        document.getElementById("update").removeAttribute("hidden");
        document.getElementById("create").setAttribute("hidden","");
    }
</script>

<script src="nest-backend/assets/js/vendors/jquery-3.6.0.min.js"></script>
<script src="nest-backend/assets/js/vendors/bootstrap.bundle.min.js"></script>
<script src="nest-backend/assets/js/vendors/select2.min.js"></script>
<script src="nest-backend/assets/js/vendors/perfect-scrollbar.js"></script>
<script src="nest-backend/assets/js/vendors/jquery.fullscreen.min.js"></script>
<script src="nest-backend/assets/js/main.js?v=1.1" type="text/javascript"></script>
</body>
</html>
