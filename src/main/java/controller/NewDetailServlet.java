package controller;

import dal.CategoryDAO;
import dal.ImgDAO;
import dal.NewsDAO;
import dal.ProductDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Category;
import model.News;
import model.Product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NewDetailServlet", value = "/new-detail")
public class NewDetailServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        News news = newsDAO.getById(newsId);

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
        request.setAttribute("categories", categories);
        request.setAttribute("categoryProductCounts", categoryProductCounts);
        request.setAttribute("popularProducts", popularProducts); // Add popular products
        request.setAttribute("news", news);
        request.setAttribute("img", new ImgDAO().getImgById(news.getImgID()).getImglink());
        request.getRequestDispatcher("New-detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
