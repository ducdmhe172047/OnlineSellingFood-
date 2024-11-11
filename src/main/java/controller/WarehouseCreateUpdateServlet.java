package controller;

import dal.ContactInformationDAO;
import dal.WarehouseDAO;
import dal.WarehouseStatusDAO;
import model.ContactInformation;
import model.Warehouse;
import model.WarehouseStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WarehouseCreateUpdateServlet", value = "/warehouseCU")
public class WarehouseCreateUpdateServlet extends HttpServlet {
    private WarehouseDAO warehouseDAO = new WarehouseDAO();
    private ContactInformationDAO contactInformationDAO = new ContactInformationDAO();
    private WarehouseStatusDAO warehouseStatusDAO = new WarehouseStatusDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String warehouseID = request.getParameter("warehouseID");
        List<WarehouseStatus> statusList = warehouseStatusDAO.getAllStatus();
        request.setAttribute("statusList", statusList);

        if (warehouseID != null) {
            Warehouse warehouse = warehouseDAO.getWarehouse(Integer.parseInt(warehouseID));
            ContactInformation contactInfo = contactInformationDAO.getContactInformationByContactID(warehouse.getContactInformationID());

            request.setAttribute("warehouseID", warehouse.getWarehouseID());
            request.setAttribute("name", warehouse.getName());
            request.setAttribute("statusID", warehouse.getStatusID());
            request.setAttribute("contactID", contactInfo.getContactInformationID());
            request.setAttribute("address", contactInfo.getAddress());
            request.setAttribute("phoneNumber", contactInfo.getPhoneNumber());
        }
        request.getRequestDispatcher("page-origin-warehouse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String warehouseID = request.getParameter("warehouseID");
        String name = request.getParameter("name");
        String statusID = request.getParameter("statusID");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone");
        String contactID = request.getParameter("contactID");

        if (warehouseID != null && !warehouseID.isEmpty()) {
            Warehouse warehouse = new Warehouse(Integer.parseInt(warehouseID), contactInformationDAO.updateContact(address, phoneNumber), Integer.parseInt(statusID), name);

            boolean isUpdated = warehouseDAO.updateWarehouse(warehouse);

            if (isUpdated) {
                contactInformationDAO.deleteContact(Integer.parseInt(contactID));
                response.sendRedirect("warehouseList");
            } else {
                request.getSession().setAttribute("msg", "Cập nhật warehouse không thành công. Có thể tên warehouse đã tồn tại.");
                response.sendRedirect("warehouseList");
            }
        } else {
            if (warehouseDAO.isWarehouseExists(name)) {
                request.getSession().setAttribute("msg", "Warehouse với tên này đã tồn tại.");
                response.sendRedirect("warehouseList");
                return;
            }

            ContactInformation contactInfo = new ContactInformation(address, phoneNumber);
            Integer newContactID = contactInformationDAO.addContact(contactInfo);

            if (newContactID != null) {
                Warehouse warehouse = new Warehouse(newContactID, Integer.parseInt(statusID), name);
                Integer newWarehouseID = warehouseDAO.addWarehouse(warehouse);

                if (newWarehouseID != null) {
                    response.sendRedirect("warehouseList");
                } else {
                    request.getSession().setAttribute("msg", "Tạo warehouse không thành công. Có thể tên warehouse đã tồn tại.");
                    response.sendRedirect("warehouseList");
                }
            } else {
                request.getSession().setAttribute("msg", "Tạo thông tin liên hệ không thành công.");
                response.sendRedirect("warehouseList");
            }
        }
    }
}
