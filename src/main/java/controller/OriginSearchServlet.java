package controller;

import java.io.IOException;
import java.util.List;
import dal.OriginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Origin;

@WebServlet(name = "OriginSearchServlet", value = "/originSearch")
public class OriginSearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchKeyword = request.getParameter("searchKeyword");

        OriginDAO originDAO = new OriginDAO();
        List<Origin> originList = originDAO.searchOrigins(searchKeyword);
        request.setAttribute("originList", originList);
        request.getRequestDispatcher("page-origin.jsp").forward(request, response);
    }
}
