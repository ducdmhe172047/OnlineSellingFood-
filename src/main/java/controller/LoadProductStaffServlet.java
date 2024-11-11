package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(name = "LoadProductStaffServlet", value = "/LoadProductStaffServlet")
public class LoadProductStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products",new ProductDAO().getAllProduct());
        request.getRequestDispatcher("productliststaff.jsp").forward(request,response);
    }
}