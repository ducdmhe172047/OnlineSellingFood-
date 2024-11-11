package controller;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WarehouseListServlet", value = "/warehouseList")
public class WarehouseListServlet extends HttpServlet {
    private static final int RECORDS_PER_PAGE = 9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }

        ContactInformationDAO contactInfoDAO = new ContactInformationDAO();
        WarehouseStatusDAO statusDAO = new WarehouseStatusDAO();
        WarehouseDAO warehouseDAO = new WarehouseDAO();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * RECORDS_PER_PAGE;

        List<Warehouse> warehouseList = warehouseDAO.searchWarehouses(search, start, RECORDS_PER_PAGE);

        List<WarehouseStatus> statusList = statusDAO.getAllWarehouseStatuses();

        for (Warehouse warehouse : warehouseList) {
            ContactInformation contactInfo = contactInfoDAO.getContactInformationByContactID(warehouse.getContactInformationID());
            WarehouseStatus status = statusDAO.getStatusByStatusID(warehouse.getStatusID());

            request.setAttribute("contactInfo_" + warehouse.getContactInformationID(), contactInfo);
            request.setAttribute("status_" + warehouse.getStatusID(), status);
        }

        int totalWarehouses = warehouseDAO.countWarehouseBySearch(search);
        int totalPages = (int) Math.ceil((double) totalWarehouses / RECORDS_PER_PAGE);

        request.setAttribute("warehouseList", warehouseList);
        request.setAttribute("statusList", statusList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("warehouseList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý phương thức POST nếu cần
    }
}
