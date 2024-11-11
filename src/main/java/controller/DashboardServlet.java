package controller;


import dal.OrderDAO;
import dal.OrderProductDAO;
import dal.ProductDAO;
import dto.OrderResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@WebServlet(name = "DashboardServlet", value = "/Dashboard")
public class DashboardServlet extends HttpServlet {

    private OrderProductDAO orderProductDAO;





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = 0;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        String search = request.getParameter("search");
        String orderStatus = request.getParameter("orderStatus");
        OrderProductDAO dao = new OrderProductDAO();
        double totalRevenue = dao.getTotalRevenue();
        OrderDAO dao1 = new OrderDAO();
        int totalOrders = dao1.getTotalOrders();
        ProductDAO dao2 = new ProductDAO();
        int totalProducts = dao2.getTotalProducts();
        int totalRecords;
        List<OrderResponse> orderList = dao1.getAllOrdersd(index,search,orderStatus);

        if(search != null && !search.trim().isEmpty() ){
              totalRecords =    dao1.getTotalOrdersWithSearch(search);}
         else if(orderStatus != null && !orderStatus.trim().isEmpty()){
              totalRecords=  dao1.getTotalOrdersWithStatus(orderStatus);}
         else{

             totalRecords=  dao1.getTotalOrders();
         }

         int endPage = totalRecords / 5;
        if (totalRecords % 5 != 0) {
            endPage++;
        }

        String sortBy = request.getParameter("sortBy");
        String sortOrder = request.getParameter("sortOrder");

        if ("orderDate".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(orderList, Comparator.comparing(OrderResponse::getOrderDate));
            } else {
                Collections.sort(orderList, Comparator.comparing(OrderResponse::getOrderDate).reversed());
            }
        } else if ("price".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(orderList, Comparator.comparing(OrderResponse::getPrice));
            } else {
                Collections.sort(orderList, Comparator.comparing(OrderResponse::getPrice).reversed());
            }
        }
        request.setAttribute("endPage", endPage);




        request.setAttribute("orderList", orderList);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalProducts", totalProducts);
        request.getRequestDispatcher("DashboardSale.jsp").forward(request, response);

    }


}
