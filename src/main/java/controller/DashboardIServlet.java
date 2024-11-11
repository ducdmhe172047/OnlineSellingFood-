package controller;


import dal.ImportProductDAO;
import dal.SupplierDAO;
import dal.WarehouseDAO;
import dto.ImportProductResponse;
import dto.OrderResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "DashboardIServlet", value = "/DashboardI")
public class DashboardIServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = 0;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        String search = request.getParameter("search");
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        int totalWarehouse = warehouseDAO.getTotalWarehouses();
        SupplierDAO supplierDAO = new SupplierDAO();
        int totalSupplier = supplierDAO.getTotalSuppliers();
        ImportProductDAO dao = new ImportProductDAO();
        List<ImportProductResponse> importList =  dao.getAllImportProductsd(index,search);
        int totalRecords;
        if (search != null && !search.trim().isEmpty()) {
            totalRecords = dao.getTotalImportProductWithSearch(search);
        } else{
            totalRecords = dao.getTotalImports();
        }


        int endPage = totalRecords / 5;
        if (totalRecords % 5 != 0) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);

        String sortBy = request.getParameter("sortBy");
        String sortOrder = request.getParameter("sortOrder");
        if ("price".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(importList, Comparator.comparing(ImportProductResponse::getPrice));
            } else {
                Collections.sort(importList, Comparator.comparing(ImportProductResponse::getPrice).reversed());
            }
        } else if ("quantity".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(importList, Comparator.comparing(ImportProductResponse::getImportQuantity));
            } else {
                Collections.sort(importList, Comparator.comparing(ImportProductResponse::getImportQuantity).reversed());
            }
        }else if ("inventory".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(importList, Comparator.comparing(ImportProductResponse::getInventoryQuantity));
            } else {
                Collections.sort(importList, Comparator.comparing(ImportProductResponse::getInventoryQuantity).reversed());
            }
        }


        request.setAttribute("importList", importList);
        request.setAttribute("totalWarehouse", totalWarehouse);
        request.setAttribute("totalSupplier", totalSupplier);
        request.getRequestDispatcher("DashboardImport.jsp").forward(request, response);
    }
}
