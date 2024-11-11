package controller;

import dal.ContactInformationDAO;
import dal.SupplierDAO;
import model.Supplier;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SupplierDeleteServlet", value = "/supplierDelete")
public class SupplierDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int supplierID = Integer.parseInt(request.getParameter("supplierID"));
        String msg;
        if (new SupplierDAO().deleteSupplier(supplierID)) {
            msg = "Xóa nhà cung cấp thành công";
        } else msg = "Xóa nhà cung cấp thất bại";
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("supplierList");
    }
}
