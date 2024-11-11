package controller;

import dal.CategoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryDeleteServlet", value = "/categoryDelete")
public class CategoryDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // No need for post processing in this case
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdParam = request.getParameter("categoryID");
        if (categoryIdParam != null) {
            int categoryID = Integer.parseInt(categoryIdParam);
            CategoryDAO categoryDAO = new CategoryDAO();

            boolean isUsed = categoryDAO.isCategoryInUse(categoryID);

            if (isUsed) {
                request.getSession().setAttribute("msg", "Category is currently in use and cannot be deleted.");
                response.sendRedirect("categoryList");
            } else {
                categoryDAO.deleteCategory(categoryID);
                request.getSession().setAttribute("msg", "Category deleted successfully.");
                response.sendRedirect("categoryList");
            }
        } else {
            response.sendRedirect("categoryList");
        }
    }
}
