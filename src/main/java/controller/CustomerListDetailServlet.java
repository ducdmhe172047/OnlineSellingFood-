package controller;

import java.io.*;
import java.util.List;

import dal.CustomerDAO;
import dal.StaffDAO;
import dto.CustomerDetailRespone;
import dto.StaffDetailRespone;
import dto.StaffListResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "CustomerListDetailServletServlet", urlPatterns = {"/customerListDetail"})
public class CustomerListDetailServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDAO cd = new CustomerDAO();
        String accountID_raw=request.getParameter("accountID");
        int accountID = Integer.parseInt(accountID_raw);
        CustomerDetailRespone cdr = cd.getCustomerDetail(accountID);
        request.setAttribute("customerListDetail", cdr);
        request.setAttribute("accountID", accountID);
        request.getRequestDispatcher("customer-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
