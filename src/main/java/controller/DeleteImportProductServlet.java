package controller;

import dal.ImportProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteImportProductServlet", value = "/importProductDelete")
public class DeleteImportProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String importIDParam = request.getParameter("importID");


        if (importIDParam != null && !importIDParam.trim().isEmpty() )
                 {
            int importID = Integer.parseInt(importIDParam);

            ImportProductDAO dao = new ImportProductDAO();

            // Call the method to delete the import product
            boolean success = dao.deleteImportProduct(importID);

            if (success) {
                // Redirect to the import product page after successful deletion
                response.sendRedirect("ImportProduct?id=" + importID);
            } else {
                // Handle the error case (e.g., show an error message)
                request.setAttribute("errorMessage", "Unable to delete the import product. Please try again.");
                response.sendRedirect("ImportProduct?id=" + importID);
            }
        } else {
            System.out.println("Import ID hoặc Product ID không hợp lệ.");
            response.sendRedirect("ImportProduct?id=" + (importIDParam != null ? importIDParam : "0")); // Gán giá trị mặc định
        }
    }
}
