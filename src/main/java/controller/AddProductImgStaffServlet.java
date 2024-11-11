package controller;

import java.io.IOException;

import common.ImgFile;
import dal.ProductImgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddProductImgStaffServlet", value = "/AddProductImgStaffServlet")
@MultipartConfig
public class AddProductImgStaffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productid"));
        Integer imgID = ImgFile.importImg(request.getPart("img"),productID+"-product-");
        ProductImgDAO productImgDAO = new ProductImgDAO();
        String msg;
        if(imgID!=null && productImgDAO.addProductImg(productID,imgID)){
            msg = "Add image successful";
        }
        else{
            msg = "Fail to add product img";
        }
        response.sendRedirect("updateproduct.jsp?msg="+msg+"&productid="+productID);
    }
}