package controller;

import dal.CustomerDAO;
import dal.OrderProductDAO;
import dto.OrderProductResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Customer;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/orderHistory"})
public class OrderHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderProductDAO opd = new OrderProductDAO();
        CustomerDAO cd = new CustomerDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account != null) {
            Customer customer = cd.getCustomerByAccountID(account.getAccountID());
            if (customer != null) {
                List<OrderProductResponse> orderHistory = opd.getOrderProductByCutomerID(customer.getCustomerID());
                request.setAttribute("list", orderHistory);
            }
        }

        request.getRequestDispatcher("view-order-history.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
