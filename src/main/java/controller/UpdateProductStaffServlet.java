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

@WebServlet(name = "UpdateProductStaffServlet", value = "/UpdateProductStaffServlet")
public class UpdateProductStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productid"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        Integer discountID = null;
        int weight = Integer.parseInt(request.getParameter("weight"));
        int categoryID = Integer.parseInt(request.getParameter("categoryid"));
        int manufacturerID = Integer.parseInt(request.getParameter("manufacturerid"));
        int originID = Integer.parseInt(request.getParameter("originid"));
        int unitID = Integer.parseInt(request.getParameter("unitid"));
        int certificationID = Integer.parseInt(request.getParameter("certificationid"));
        int statusID = Integer.parseInt(request.getParameter("statusid"));
        String detail = request.getParameter("detail");
        String msg;
        if(new ProductDAO().updateProduct(new Product(productID,price,discountID,weight,categoryID,manufacturerID,originID,unitID,certificationID,statusID,name,detail))){
            msg = "Update product successful";
        }else msg = "Fail to update product";
        response.sendRedirect("updateproduct.jsp?msg="+msg+"&productid="+productID);
    }
}