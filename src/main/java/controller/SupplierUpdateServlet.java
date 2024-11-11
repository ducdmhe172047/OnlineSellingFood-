package controller;

import dal.ContactInformationDAO;
import dal.SupplierDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Supplier;

import java.io.IOException;

@WebServlet(name = "SupplierUpdateServlet", value = "/supplierupdate")
public class SupplierUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int supplierID = Integer.parseInt(request.getParameter("supplierID"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String note = request.getParameter("note");

        Supplier supplier = new Supplier(supplierID, new ContactInformationDAO().updateContact(address,phonenumber), name, note);
        if(new SupplierDAO().updateSupplier(supplier)){
            request.getSession().setAttribute("msg", "Cập nhật nhà cung cấp thành công");
        }else request.getSession().setAttribute("msg", "Cập nhật nhà cung cấp thất bại");
        response.sendRedirect("supplierList");

    }

}
