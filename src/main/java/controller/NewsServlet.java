package controller;

import dal.NewsDAO;
import dal.ProductDAO;
import model.News;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.CategoryDAO;
import model.Category;
import model.Product;

@WebServlet(name = "NewsServlet", value = "/news")
public class NewsServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private ProductDAO productDAO = new ProductDAO(); // Assuming you have ProductDAO for fetching products

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search");

        // Fetch news
        List<News> newsList;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            newsList = newsDAO.searchByTitle(searchQuery);
        } else {
            newsList = newsDAO.getAll();
        }

        // Retrieve categories from CategoryDAO
        List<Category> categories = categoryDAO.getAllCategories();

        // Create a map to store product counts by categoryID
        Map<Integer, Integer> categoryProductCounts = new HashMap<>();
        for (Category category : categories) {
            int productCount = categoryDAO.getProductCountByCategory(category.getCategoryID());
            categoryProductCounts.put(category.getCategoryID(), productCount);
        }

        // Fetch popular products (top 5 with discount)
        List<Product> popularProducts = productDAO.get5ProductByDiscount(); // Get the top 5 discounted products

        // Set attributes for JSP
        request.setAttribute("list", newsList);
        request.setAttribute("categories", categories);
        request.setAttribute("categoryProductCounts", categoryProductCounts);
        request.setAttribute("popularProducts", popularProducts); // Add popular products

        // Forward to JSP page
        request.getRequestDispatcher("news.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
