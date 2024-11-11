package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import model.Manufacturer;
import dal.ManufacterDAO;

@WebServlet(name = "RegisterManufacterServlet", value = "/registerManu")
public class RegisterManufacterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the form page
        request.getRequestDispatcher("add-manufacter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String introduce = request.getParameter("introduce");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));

        ManufacterDAO manufacterDAO = new ManufacterDAO();

        // Check if the manufacturer name already exists
        if (manufacterDAO.isManufacturerNameExists(name)) {
            request.setAttribute("errorMessage", "Nhà sản xuất đã tồn tại");
            request.getRequestDispatcher("add-manufacter.jsp").forward(request, response);
        } else {
            // Validate if active status can be set to nonactive based on product count
            int productCount = manufacterDAO.getProductCountByManufacturerName(name);
            if (!active && productCount > 0) {
                request.setAttribute("errorMessage", "Không thể chọn Non-Active. Nhà sản xuất vẫn tồn tại sản phẩm");
                request.getRequestDispatcher("add-manufacter.jsp").forward(request, response);
                return;
            }

            // Create a new Manufacturer object and add it to the database
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(name);
            manufacturer.setIntroduce(introduce);
            manufacturer.setActive(active);

            Integer manufacturerID = manufacterDAO.addManufacturer(manufacturer);

            if (manufacturerID != null) {
                response.sendRedirect("manulist");
            } else {
                request.setAttribute("errorMessage", "Failed to add the manufacturer.");
                request.getRequestDispatcher("add-manufacter.jsp").forward(request, response);
            }
        }
    }
}
