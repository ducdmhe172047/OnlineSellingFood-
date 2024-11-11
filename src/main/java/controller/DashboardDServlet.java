package controller;

import dal.OrderDAO;
import dto.OrderResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardDServlet", value = "/DashboardD")
public class DashboardDServlet extends HttpServlet {
    OrderDAO dao = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = 0;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        int indexb = 0;
        if (request.getParameter("indexb") != null) {
            indexb = Integer.parseInt(request.getParameter("indexb"));
        }
        int totalRecords = dao.countOrdersComplete();
        int endPage = totalRecords / 5;
        if (totalRecords % 5 != 0) {
            endPage++;
        }
        int totalRecordsb = dao.countOrdersBeingDelivery();
        int endPageb = totalRecords / 5;
        if (totalRecords % 5 != 0) {
            endPageb++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("endPageb", endPageb);
        List<OrderResponse> orderList = dao.getAllOrdersdComplete(index);
        List<OrderResponse> orderListb = dao.getAllOrdersdBeing(index);
            int completedOrders = dao.countOrdersComplete();
            int beingDeliveredOrders = dao.countOrdersBeingDelivery();
            int otherOrders = dao.countOtherOrders();


            request.setAttribute("orderListb", orderListb);
            request.setAttribute("orderList", orderList);
            request.setAttribute("completedOrders", completedOrders);
            request.setAttribute("beingDeliveredOrders", beingDeliveredOrders);
            request.setAttribute("otherOrders", otherOrders);


            request.getRequestDispatcher("DashboardDelivery.jsp").forward(request, response);


    }
}
