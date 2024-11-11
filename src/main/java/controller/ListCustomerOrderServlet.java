package controller;

import dal.OrderDAO;
import dto.CustomerOrderResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListCustomerOrderServlet", urlPatterns = {"/customerOrder"})
public class ListCustomerOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException {
        OrderDAO od = new OrderDAO();
        String statusIDStr = request.getParameter("statusID");
        int statusID = 0;
        if (statusIDStr != null && !statusIDStr.isEmpty()) {
            try {
                statusID = Integer.parseInt(statusIDStr);
            } catch (NumberFormatException e) {
                statusID = 0;
            }
        }
        String searchName=request.getParameter("searchName");
        if(searchName==null)searchName="";
        String sortPrice = request.getParameter("sortPrice");
        if (sortPrice == null) sortPrice = "asc";
        String indexPage=request.getParameter("index");
        if(indexPage==null){
            indexPage="1";
        }
        int index=Integer.parseInt(indexPage);
        int itemsPerPage = 5;
        List<CustomerOrderResponse> customerOrderList=od.getListCustomerOrders(index,searchName,statusID,sortPrice);

        int count=od.getTotalCustomerOrders(searchName,statusID);
        int endPage=count/5;
        if(count%5!=0){
            endPage++;
        }
        int startCount = (index - 1) * itemsPerPage + 1;
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("searchName", searchName);
        request.setAttribute("customerOrderList", customerOrderList);
        request.setAttribute("startCount", startCount);
        request.setAttribute("statusID", statusID);
        request.setAttribute("sortPrice", sortPrice);
        request.getRequestDispatcher("page-orders-list.jsp").forward(request, respose);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
