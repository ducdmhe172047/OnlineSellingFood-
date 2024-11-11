package controller;

import java.io.IOException;
import java.util.List;
import dal.CategoryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

@WebServlet(name = "CategorySearchServlet", value = "/categorySearch")
public class CategorySearchServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        String searchKeyword = request.getParameter("searchKeyword");
        List<Category> categoryList = categoryDAO.searchCategories(searchKeyword);

        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("page-origin-category.jsp");
        dispatcher.forward(request, response);
    }
}
