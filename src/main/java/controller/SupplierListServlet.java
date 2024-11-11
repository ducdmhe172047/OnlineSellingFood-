package controller;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SupplierListServlet", value = "/supplierList")
public class SupplierListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Supplier> suppliers = new SupplierDAO().getAllSuppliers();
        String sort_str = request.getParameter("sort");
        if(sort_str != null) {
            Integer sort = Integer.parseInt(sort_str);
            if(sort == 1) {
                suppliers.sort(Comparator.comparing(Supplier::getName));
            } else if(sort == 5) {
                suppliers.sort(Comparator.comparing(Supplier::getNote));
            }
        }

        request.setAttribute("supplierList", suppliers);
        request.getRequestDispatcher("supplierList.jsp").forward(request, response);
    }
}
