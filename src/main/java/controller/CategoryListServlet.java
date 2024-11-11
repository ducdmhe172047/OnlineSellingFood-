package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

@WebServlet(name = "CategoryListServlet", value = "/categoryList")
public class CategoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Category> categoryList = categoryDAO.getAllCategories();
        Map<Integer, Integer> productCountByCategory = productDAO.countProductsByCategory();

        request.setAttribute("categoryList", categoryList);
        request.setAttribute("productCountByCategory", productCountByCategory);
        request.getRequestDispatcher("page-origin-category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Do something for POST if needed
    }
}
