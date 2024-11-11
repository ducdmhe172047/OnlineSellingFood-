import dal.ManufacterDAO;
import dal.ProductDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Manufacturer;
import model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManufacturerDetailProductServlet", value = "/manuDetail")
public class ManufacturerDetailProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManufacterDAO md = new ManufacterDAO();
        ProductDAO pd = new ProductDAO();
        String ManufacturerID_raw = request.getParameter("ManufacturerID");
        try {
            int ManufacturerID = Integer.parseInt(ManufacturerID_raw);
            Manufacturer m = md.getManufacturerByID(ManufacturerID);
            List<Manufacturer> manufacturers = md.getAllManufacturers();
            List<Product> products = pd.getProductsByManufacturerID(ManufacturerID);

            if (m != null) {
                request.setAttribute("manuDetail", m);
                request.setAttribute("products", products);
                request.setAttribute("ManufacturerID", ManufacturerID);
                request.getRequestDispatcher("page-manu-detail.jsp").forward(request, response);
            } else {
                response.sendRedirect("errorPage.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("errorPage.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}