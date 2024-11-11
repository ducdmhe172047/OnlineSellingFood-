package controller;

import dal.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

import java.io.IOException;

@WebServlet(name = "CategoryCreateUpdateServlet", value = "/categoryCU")
public class CategoryCreateUpdateServlet extends HttpServlet {
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryID = request.getParameter("categoryID");
        if (categoryID != null) {
            Category category = categoryDAO.getCategoryById(Integer.parseInt(categoryID));
            request.setAttribute("categoryID", category.getCategoryID());
            request.setAttribute("categoryName", category.getName());
        }
        request.getRequestDispatcher("page-origin-category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryID = request.getParameter("categoryID");
        String name = request.getParameter("name");

        CategoryDAO categoryDAO = new CategoryDAO();

        // Kiểm tra tên category đã tồn tại hay chưa
        if (categoryDAO.isCategoryNameExist(name)) {
            request.getSession().setAttribute("msg", "Tên danh mục đã tồn tại. Vui lòng chọn tên khác.");
            response.sendRedirect("categoryList");
            return;
        }

        if (categoryID != null && !categoryID.isEmpty()) {
            if (name != null && !name.isEmpty()) {
                boolean isUpdated = categoryDAO.updateCategory(categoryID, name);
                if (isUpdated) {
                    response.sendRedirect("categoryList");
                } else {
                    request.setAttribute("error", "Cập nhật không thành công. Vui lòng thử lại.");
                    request.getRequestDispatcher("categoryList").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tên không thể để trống.");
                request.getRequestDispatcher("categoryList").forward(request, response);
            }
        } else {
            if (name != null && !name.isEmpty()) {
                boolean isCreated = categoryDAO.createCategory(name);
                if (isCreated) {
                    response.sendRedirect("categoryList");
                } else {
                    request.setAttribute("error", "Tạo mới không thành công. Vui lòng thử lại.");
                    request.getRequestDispatcher("categoryList").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tên không thể để trống.");
                request.getRequestDispatcher("categoryList").forward(request, response);
            }
        }
    }
}
