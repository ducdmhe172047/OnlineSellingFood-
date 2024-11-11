package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dal.*;
import dto.StaffDetailRespone;
import dto.StaffListResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "StaffListDetailServletServlet", urlPatterns = {"/staffListDetail"})
public class StaffListDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaffDAO sd = new StaffDAO();
        String accountID_raw=request.getParameter("accountID");
        int accountID = Integer.parseInt(accountID_raw);
        StaffDetailRespone sdr=sd.getStaffDetail(accountID);
        WarehouseDAO wd = new WarehouseDAO();
        List<Warehouse> list = wd.getAllWareHouseActive();

        if (sdr.getBirth() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedBirth = sdr.getBirth().format(formatter);
            request.setAttribute("formattedBirth", formattedBirth);
        }
        request.setAttribute("warehouses",list);
        request.setAttribute("staffListDetail", sdr);
        request.setAttribute("accountID", accountID);
        request.getRequestDispatcher("staff-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        int roleID=Integer.parseInt(request.getParameter("roleID"));
        String name=request.getParameter("name");
        int genderID=Integer.parseInt(request.getParameter("gender"));
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String birth=request.getParameter("birth");
        int salary=Integer.parseInt(request.getParameter("salary"));
        int warehouseID=Integer.parseInt(request.getParameter("warehouseID"));
        int statusID=Integer.parseInt(request.getParameter("statusID"));
        String phone=request.getParameter("phone");
        if (!phone.matches("^0[0-9]{9}$")) {
            request.setAttribute("msg", "Số điện thoại bắt đầu phải bằng chữ số 0!");
            loadData(request, response);
            request.getRequestDispatcher("staff-detail.jsp").forward(request, response);

        }

        LocalDateTime birthFormat;
        birthFormat= LocalDate.parse(birth, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();

        AccountDAO accountDAO = new AccountDAO();
        ContactInformationDAO contactInfoDAO = new ContactInformationDAO();
        StaffDAO staffDAO = new StaffDAO();
        AccountContactDAO accountContactDAO = new AccountContactDAO();

        Account account = new Account(accountID, email, roleID, name, genderID, birthFormat, statusID);
        accountDAO.updateAccountInformation(account);

        ContactInformation contactInformation=contactInfoDAO.getContactInformationByAccountID(accountID);
        if (contactInformation!=null){
            contactInformation.setAddress(address);
            contactInformation.setPhoneNumber(phone);
            contactInfoDAO.update(contactInformation);
        }
        Staff staff=new Staff(staffDAO.getStaffIDbyAccountID(accountID), accountID,salary,warehouseID);
        staffDAO.updateStaffInformation(staff);
        response.sendRedirect("staffList");

    }

    public void loadData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StaffDAO sd = new StaffDAO();
        String accountID_raw=request.getParameter("accountID");
        int accountID = Integer.parseInt(accountID_raw);
        StaffDetailRespone sdr=sd.getStaffDetail(accountID);
        WarehouseDAO wd = new WarehouseDAO();
        List<Warehouse> list = wd.getAllWareHouseActive();

        if (sdr.getBirth() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedBirth = sdr.getBirth().format(formatter);
            request.setAttribute("formattedBirth", formattedBirth);
        }
        request.setAttribute("warehouses",list);
        request.setAttribute("staffListDetail", sdr);
        request.setAttribute("accountID", accountID);
        request.getRequestDispatcher("staff-detail.jsp").forward(request, response);
    }
}