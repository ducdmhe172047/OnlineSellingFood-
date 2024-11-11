package controller;

import dal.ImportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteImportServlet", value = "/importDelete")
public class DeleteImportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String importIDParam = request.getParameter("importID");

        if (importIDParam != null) {
            int importID = Integer.parseInt(importIDParam);
            ImportDAO dao = new ImportDAO();

            // Call the method to delete the import
            boolean success = dao.deleteImport(importID);

            if (success) {
                // Redirect to the imports page after successful deletion
                response.sendRedirect("Import");
            } else {
                // Handle the error case (e.g., show an error message)
                request.setAttribute("errorMessage", "Unable to delete the import. Please try again.");
                request.getRequestDispatcher("imports.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("Import"); // Redirect if no importID is provided
        }
    }
}
