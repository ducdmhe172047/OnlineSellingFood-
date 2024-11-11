package controller;

import dal.StaffDAO;
import dto.StaffDetailRespone;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import dal.ManufacterDAO;
import model.Manufacturer;

import java.util.List;

@WebServlet(name = "ManufacterDetailServlet", value = "/manuListDetail")
public class ManufacterDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacterDAO md = new ManufacterDAO();
        String ManufacturerID_raw = request.getParameter("ManufacturerID");
        try {
            int ManufacturerID = Integer.parseInt(ManufacturerID_raw);
            Manufacturer m = md.getManufacturerByID(ManufacturerID);

            if (m != null) {
                request.setAttribute("manuListDetail", m);
                request.setAttribute("ManufacturerID", ManufacturerID);
                request.getRequestDispatcher("manufacter-detail.jsp").forward(request, response);
            } else {
                response.sendRedirect("errorPage.jsp"); // Nếu không tìm thấy nhà sản xuất, chuyển hướng đến trang lỗi.
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp"); // Xử lý lỗi nếu tham số không phải số.
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Không cần xử lý POST ở đây, vì bạn đang dùng phương thức GET để lấy thông tin chi tiết.
    }
}

