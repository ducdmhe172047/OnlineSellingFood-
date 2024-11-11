package controller;

import dal.CustomerDAO;
import dal.FeedbackProductDAO;
import dal.ProductDAO;
import dto.FeedbackResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Customer;
import model.Product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "ReplyCommentServlet", urlPatterns = {"/replyComment"})
public class ReplyCommentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int replyID = Integer.parseInt(request.getParameter("replyID"));
//        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();
//        List<FeedbackResponse> list = feedbackProductDAO.getReplyComment(replyID);
//        request.setAttribute("replyComment", list);
//        request.getRequestDispatcher("shop-product-full.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productID);

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.setAttribute("errorMessage", "You need to login first to write feedback!.");
            loadProductDetails(request, response, productID);
            return;
        }


        CustomerDAO customerDAO = new CustomerDAO();
        Customer c = customerDAO.getCustomerByAccountID(account.getAccountID());
        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();

        String comment = request.getParameter("replyContent");
        LocalDateTime currentTime = LocalDateTime.now();
        Integer replyID = request.getParameter("replyID") != null ? Integer.parseInt(request.getParameter("replyID")) : null;
        Integer rating=null;
        feedbackProductDAO.addFeedbackProduct(productID, c.getCustomerID(), rating, comment, currentTime, replyID);
        response.sendRedirect("ProductDetail?productID=" + productID);
    }

    private void loadProductDetails(HttpServletRequest request, HttpServletResponse response, int productID) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productID);
        if (product != null) {
            request.setAttribute("product", product);
        } else {
            request.setAttribute("errorMessage", "Product not found.");
        }

        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();
        List<FeedbackResponse> feedbackList = feedbackProductDAO.getAllFeedbackProduct(productID);
        request.setAttribute("list", feedbackList);
        request.setAttribute("productID", productID);

        request.getRequestDispatcher("shop-product-full.jsp").forward(request, response);
    }
}
