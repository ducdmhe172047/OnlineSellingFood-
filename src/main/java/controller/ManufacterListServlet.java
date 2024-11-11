package controller;

import dal.ManufacterDAO;
import dal.StaffDAO;
import dto.StaffListResponse;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Manufacturer;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManufacterListServlet", value = "/manulist")
public class ManufacterListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacterDAO md = new ManufacterDAO();
        List<Manufacturer> lmf;

        // Get any error message from session and clear it
        HttpSession session = request.getSession();
        String errorMessage = (String) session.getAttribute("deleteError");
        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            session.removeAttribute("deleteError");
        }

        String searchQuery = request.getParameter("search");
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            lmf = md.searchManufacturersByName(searchQuery);
        } else {
            lmf = md.getAllManufacturers();
        }
        request.setAttribute("manuList", lmf);
        request.getRequestDispatcher("manufacter-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("delete".equals(action)) {
            int manufacturerID = Integer.parseInt(request.getParameter("ManufacturerID"));
            ManufacterDAO md = new ManufacterDAO();

            // Get the manufacturer to check product count
            List<Manufacturer> manufacturers = md.getAllManufacturers();
            Manufacturer manufacturerToDelete = manufacturers.stream()
                    .filter(m -> m.getManufacturerID() == manufacturerID)
                    .findFirst()
                    .orElse(null);

            if (manufacturerToDelete != null && manufacturerToDelete.getProductCount() >= 1) {
                // Set error message in session for display after redirect
                session.setAttribute("deleteError",
                        "Cannot delete manufacturer '" + manufacturerToDelete.getName() +
                                "' because it has " + manufacturerToDelete.getProductCount() +
                                " associated product(s).");
                response.sendRedirect("manulist");
                return;
            }

            boolean success = md.deleteManufacturer(manufacturerID);
            if (!success) {
                session.setAttribute("deleteError", "Failed to delete manufacturer.");
            }
            response.sendRedirect("manulist");

        } else {
            // Handle other actions (like adding or updating manufacturers)
            String name = request.getParameter("name");
            String introduce = request.getParameter("introduce");

            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(name);
            manufacturer.setIntroduce(introduce);

            ManufacterDAO md = new ManufacterDAO();
            Integer manufacturerID = md.addManufacturer(manufacturer);

            if (manufacturerID != null) {
                response.sendRedirect("manulist");
            } else {
                request.setAttribute("error", "Failed to add manufacturer.");
                request.getRequestDispatcher("manufacter-list.jsp").forward(request, response);
            }
        }
    }
}