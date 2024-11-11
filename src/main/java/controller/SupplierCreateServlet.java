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

@WebServlet(name = "SupplierCreateServlet", value = "/suppliercreate")
public class SupplierCreateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String note = request.getParameter("note");

        Supplier supplier = new Supplier(new ContactInformationDAO().updateContact(address,phonenumber), name, note);
        if(new SupplierDAO().insertSupplier(supplier)!=null){
            request.getSession().setAttribute("msg", "Thêm nhà cung cấp thành công");
        }else request.getSession().setAttribute("msg", "Thêm nhà cung cấp thất bại");
        response.sendRedirect("supplierList");
    }
}