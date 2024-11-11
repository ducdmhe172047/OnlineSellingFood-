package controller;


import dal.ImportDAO;
import dal.SupplierDAO;
import dal.WarehouseDAO;
import dto.ImportRespone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Import;
import model.Supplier;
import model.Warehouse;


import java.io.IOException;

import java.util.List;

@WebServlet(name = "ImportServlet", value = "/Import")
public class ImportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int index = 0;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        ImportDAO dao = new ImportDAO();
        List<ImportRespone> importList = dao.getImportList(index);
        request.setAttribute("importList", importList);
        int totalRecords = dao.getTotalImport();
        int endPage = totalRecords / 5;
        if (totalRecords % 5 != 0) {
            endPage++;
        }
        request.setAttribute("endPage", endPage);




        WarehouseDAO warehouseDAO = new WarehouseDAO();
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouseActivity();
        request.setAttribute("warehouses",warehouses);
        SupplierDAO supplierDAO = new SupplierDAO();
        List<Supplier> suppliers = supplierDAO.getAllSupplierActivity();
        request.setAttribute("suppliers",suppliers);

        request.getRequestDispatcher("ImportList.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int staffID = Integer.parseInt(request.getParameter("staffID"));
        int warehouseID = Integer.parseInt(request.getParameter("warehouseID"));
        int supplierID = Integer.parseInt(request.getParameter("supplierID"));
        String time = request.getParameter("time");

        // Thêm bản ghi vào cơ sở dữ liệu
        ImportDAO dao = new ImportDAO();
        String msg = "";
        if(dao.addImport(staffID, warehouseID, supplierID, time)){
            msg = "Thêm phiếu nhập thành công.";
        } else {
            msg = "Có lỗi xảy ra khi thêm phiếu nhập.";
        }
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("Import");
    }
    }

