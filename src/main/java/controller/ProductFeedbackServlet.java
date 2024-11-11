package controller;


import dal.FeedbackProductDAO;
import dto.FeedbackResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "ProductFeedbackServlet", urlPatterns = {"/productFeedback"})
public class ProductFeedbackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
        String searchName=request.getParameter("searchName");
        if(searchName==null)searchName="";

        String indexPage=request.getParameter("index");
        if(indexPage==null){
            indexPage="1";
        }
        int index=Integer.parseInt(indexPage);
        int productID = Integer.parseInt(request.getParameter("productid"));
        request.getSession().setAttribute("productID", productID);
        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();
        List<FeedbackResponse> list = feedbackProductDAO.getAllFeedbackProductForManage(productID,searchName,index);

        int count=feedbackProductDAO.getTotalCustomerFeedback(productID,searchName);
        int endPage=count/5;
        if(count%5!=0){
            endPage++;
        }
        int itemsPerPage = 5;
        int startCount = (index - 1) * itemsPerPage + 1;
        request.setAttribute("startCount", startCount);
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("searchName", searchName);
        request.setAttribute("list", list);
        request.getRequestDispatcher("view-feedback.jsp").forward(request, respone);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        int customerID = Integer.parseInt(request.getParameter("customerID"));
        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();
        feedbackProductDAO.deleteFeedbackProduct(productID, customerID);
        response.sendRedirect("productFeedback?productid=" + productID);
    }

}
