package controller;

import dal.DiscountDAO;
import dal.ProductDAO;
import dto.ProductDiscountResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class DeleteDiscountServlet {
    @WebServlet(name = "DeleteServlet", urlPatterns = {"/deleteDiscount"})
    public static class DeleteServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
        try {
            int discountID=Integer.parseInt(request.getParameter("discountID"));
            int productID=Integer.parseInt(request.getParameter("productID"));
            DiscountDAO discountDAO = new DiscountDAO();
            ProductDAO productDAO = new ProductDAO();
            productDAO.deleteDiscount(productID);
            discountDAO.deleteDiscount(discountID);
            respone.sendRedirect("discount");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }
    }
}
