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
import dal.OriginDAO;
import model.Origin;

@WebServlet(name = "OriginListServlet", value = "/originList")
public class OriginListServlet extends HttpServlet {
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OriginDAO originDAO = new OriginDAO();
        ProductDAO productDAO = new ProductDAO();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * RECORDS_PER_PAGE;

        List<Origin> originList = originDAO.getOriginsWithPagination(start, RECORDS_PER_PAGE);

        Map<Integer, Integer> originCounts = productDAO.countProductsByOrigin();

        int totalRecords = originDAO.getTotalOriginCount();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / RECORDS_PER_PAGE);

        request.setAttribute("originList", originList);
        request.setAttribute("originCounts", originCounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("page-origin.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST if needed
    }
}
