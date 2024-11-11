package controller;

import java.io.IOException;

import common.ImgFile;
import dal.ProductDAO;
import dal.ProductImgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(name = "AddProductStaffServlet", value = "/AddProductStaffServlet")
@MultipartConfig
public class AddProductStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Integer productID = new ProductDAO().addProduct(new Product(price,discountID,weight,categoryID,manufacturerID,originID,unitID,certificationID,statusID,name,detail));
        String msg = "";
        if(productID!=null){
            msg = "Add product success";
            Integer imgID = ImgFile.importImg(request.getPart("img"),productID+"-product-");
            ProductImgDAO productImgDAO = new ProductImgDAO();
            if(imgID!=null && productImgDAO.addProductImg(productID,imgID)){
                productImgDAO.setDefaultProductImg(productID,imgID);
            }
            else{
                msg += " ,fail to add product img";
            }
        }else{
            msg = "Fail to add new product";
        }
        response.sendRedirect("addproduct.jsp?msg="+msg);
    }
}