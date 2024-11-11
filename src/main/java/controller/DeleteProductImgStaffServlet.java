package controller;
import java.io.IOException;

import common.ImgFile;
import dal.ImgDAO;
import dal.ProductImgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProductImgStaffServlet", value = "/DeleteProductImgStaffServlet")
public class DeleteProductImgStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productid"));
        int imgID = Integer.parseInt(request.getParameter("imgid"));
        ImgDAO imgDAO = new ImgDAO();
        String msg;
        if(new ProductImgDAO().deleteProductImg(productID,imgID)){
            msg = "Delete image successful";
            String filePath = imgDAO.getImgById(imgID).getImglink();
            if(imgDAO.deleteImg(imgID)){
                ImgFile.deleteImg(filePath);
            }
        }
        else msg = "Fail to delete image";
        response.sendRedirect("updateproduct.jsp?productid="+productID+"&msg="+msg);
    }
}