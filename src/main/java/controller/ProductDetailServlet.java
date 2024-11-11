package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import dal.*;
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

@WebServlet("/ProductDetail")
public class ProductDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadProductDetails(request, response);
    }
    private void loadProductDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));


        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productID);

        if (product != null) {
            request.setAttribute("product", product);
        } else {
            request.setAttribute("errorMessage", "Product not found.");
        }

        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();
        List<FeedbackResponse> list = feedbackProductDAO.getAllFeedbackProduct(productID);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer c = customerDAO.getCustomerByAccountID(account.getAccountID());
            request.setAttribute("currentID", c.getCustomerID());
        } else{
           request.setAttribute("currentID", null);
        }
        for (FeedbackResponse feedback : list) {
            List<FeedbackResponse> replies = feedbackProductDAO.getReplyComment(feedback.getFeedbackID());
            feedback.setReplies(replies); // set danh sách replies vào feedback
        }

        int countFeedbackInProduct=feedbackProductDAO.countFeedbackInProduct(productID);
        request.setAttribute("count", countFeedbackInProduct);
        request.setAttribute("list", list);
        request.setAttribute("productID", productID);
        request.getRequestDispatcher("shop-product-full.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productID);

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.setAttribute("errorMessage", "Bạn cần đănh nhập trước khi muốn viết đánh giá!");
            loadProductDetails(request, response);
//            response.sendRedirect("login");

            return;
        }


        CustomerDAO customerDAO = new CustomerDAO();
        Customer c = customerDAO.getCustomerByAccountID(account.getAccountID());
        FeedbackProductDAO feedbackProductDAO = new FeedbackProductDAO();
        if (!feedbackProductDAO.existOrderProduct(productID, c.getCustomerID())) {
            request.setAttribute("errorMessage", "Bạn cần phải đặt hàng sản phẩm " + product.getName() + " trước khi viết đánh giá!. ");
            loadProductDetails(request, response);
            //request.getRequestDispatcher("shop-product-full.jsp").forward(request, response);
        } else {
            String comment = request.getParameter("comment");
            int rating = Integer.parseInt(request.getParameter("rating"));
            LocalDateTime currentTime = LocalDateTime.now();
            Integer replyID = null;
            feedbackProductDAO.addFeedbackProduct(productID, c.getCustomerID(), rating, comment, currentTime, replyID);
            response.sendRedirect("ProductDetail?productID=" + productID);
        }


    }
}

