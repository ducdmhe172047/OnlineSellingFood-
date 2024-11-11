package controller;

import dal.OrderDAO;
import dal.OrderProductDAO;
import dto.OrderProductResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewOrderDetailForStaffServlet", urlPatterns = {"/viewOrderDetail"})
public class ViewOrderDetailForStaffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int statusID = Integer.parseInt(request.getParameter("statusID"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.updateStatusID(orderID, statusID);
        request.setAttribute("orderID", orderID);
        request.setAttribute("statusID", statusID);
        OrderProductDAO orderProductDAO = new OrderProductDAO();
        List<OrderProductResponse> list = orderProductDAO.getOrderProductByOrderID(orderID);
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String orderTime = request.getParameter("orderTime");

        request.setAttribute("customerName", customerName);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("orderTime", orderTime);
        request.setAttribute("orderID", orderID);
        request.setAttribute("statusID", statusID);
        request.setAttribute("list", list);
        request.getRequestDispatcher("page-orders-detail.jsp").forward(request, response);


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String customerName = request.getParameter("customerName");

        String email = request.getParameter("email");
        String orderTime = request.getParameter("orderTime");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int statusID = Integer.parseInt(request.getParameter("statusID"));
        OrderProductDAO orderProductDAO = new OrderProductDAO();
        List<OrderProductResponse> list = orderProductDAO.getOrderProductByOrderID(orderID);
        request.setAttribute("list", list);


        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("customerName", customerName);
        request.setAttribute("email", email);
        request.setAttribute("orderID", orderID);
        request.setAttribute("orderTime", orderTime);
        request.setAttribute("statusID", statusID);
        request.getRequestDispatcher("page-orders-detail.jsp").forward(request, response);
    }
}
