import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import dal.ManufacterDAO;
import model.Manufacturer;

@WebServlet(name = "UpdateInformationManufacterServlet", value = "/updateManuForAdmin")
public class UpdateInformationManufacterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manufacturerID_raw = request.getParameter("ManufacturerID");
        int manufacturerID;

        try {
            manufacturerID = Integer.parseInt(manufacturerID_raw);

            ManufacterDAO manufacturerDAO = new ManufacterDAO();
            Manufacturer manufacturer = manufacturerDAO.getManufacturerByID(manufacturerID);

            // Pass manufacturer data to the JSP
            request.setAttribute("manuListDetail", manufacturer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manufacter-detail.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp"); // Handle error scenarios
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String manufacturerID_raw = request.getParameter("ManufacturerID");
        String name = request.getParameter("name");
        String introduce = request.getParameter("introduce");
        boolean active = Boolean.parseBoolean(request.getParameter("active")); // Retrieve active status from dropdown

        try {
            int manufacturerID = Integer.parseInt(manufacturerID_raw);

            ManufacterDAO manufacturerDAO = new ManufacterDAO();
            Manufacturer manufacturer = manufacturerDAO.getManufacturerByID(manufacturerID);

            // Check if product count is greater than 0 and user tries to set it as non-active
            if (!active && manufacturer.getProductCount() > 0) {
                // Prevent status change and display error message
                request.setAttribute("errorMessage", "Không thể chọn Non-Active. Nhà sản xuất vẫn tồn tại sản phẩm");
                request.setAttribute("manuListDetail", manufacturer); // Pass the manufacturer data back to the JSP
                request.getRequestDispatcher("manufacter-detail.jsp").forward(request, response);
                return; // Exit without further processing
            }

            // Update manufacturer data
            manufacturer.setName(name);
            manufacturer.setIntroduce(introduce);
            manufacturer.setActive(active);

            // Call DAO to update in the database
            boolean updateSuccess = manufacturerDAO.updateManufacturer(manufacturer);

            if (updateSuccess) {
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", "Manufacturer information updated successfully.");
                response.sendRedirect(request.getContextPath() + "/manulist");
            } else {
                request.setAttribute("errorMessage", "Update failed. The manufacturer name may already exist.");
                request.setAttribute("manuListDetail", manufacturer);
                request.getRequestDispatcher("manufacter-detail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while updating the manufacturer information.");
            request.getRequestDispatcher("manufacter-detail.jsp").forward(request, response);
        }
    }

}
