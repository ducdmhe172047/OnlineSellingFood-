package controller;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.security.SecureRandom;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterStaffServlet", urlPatterns = {"/registerstaff"})
public class RegisterStaffServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WarehouseDAO warehouseDAO = new WarehouseDAO();
        List<Warehouse> warehouses = warehouseDAO.getAllWarehouseActivity();
        request.setAttribute("warehouses",warehouses);
        request.getRequestDispatcher("page-register-staff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();

        String roleID = request.getParameter("roleID");
        String warehouseID = request.getParameter("WarehouseID");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("address");
        Integer genderID;
        LocalDateTime birth;
        try {
            birth = LocalDate.parse(request.getParameter("birth"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        } catch (DateTimeParseException e) {
            birth = null;
        }
        try {
            genderID = Integer.valueOf(request.getParameter("gender"));
        } catch (NumberFormatException e) {
            genderID = null;
        }

        if (address.length() < 5 || address.length() > 200) {
            errorMessages.add("Address must be between 5 and 200 characters.");
        }
        if (phoneNumber.length() < 6 || phoneNumber.length() > 11) {
            errorMessages.add("Phone number must be between 6 and 11 characters.");
        }

        ContactInformationDAO contactInfoDAO = new ContactInformationDAO();
        AccountDAO accountDAO = new AccountDAO();
        StaffDAO staffDAO = new StaffDAO();

        if (accountDAO.isEmailExist(email)) {
            errorMessages.add("Email already exists. Please use a different email.");
        }

        if (!errorMessages.isEmpty()) {
            request.getSession().setAttribute("msg", String.join("<br>", errorMessages));
            response.sendRedirect("registerstaff");
            return;
        }

        ContactInformation contact = contactInfoDAO.getContactInformationByAddressAndPhone(address, phoneNumber);
        if (contact == null) {
            contact = new ContactInformation(address, phoneNumber);
            contact.setContactInformationID(contactInfoDAO.addContact(contact));
        }

        try {
            Account newAccount = new Account(Integer.valueOf(roleID), email, name, genderID, "123456789", birth, LocalDateTime.now(), 3);
            Integer accountID = accountDAO.addAccount(newAccount);
            if (accountID != null) {
                Staff newStaff = new Staff(accountID, 1000, Integer.parseInt(warehouseID));
                AccountContact accountContact = new AccountContact(accountID, contact.getContactInformationID(), 1);
                new AccountContactDAO().addAccountContact(accountContact);
                staffDAO.addStaff(newStaff);
                request.getSession().setAttribute("msg", "Successfully added staff.");
            } else {
                contactInfoDAO.deleteContact(contact.getContactInformationID());
                request.getSession().setAttribute("msg", "Failed to add staff.");
            }
            response.sendRedirect("registerstaff");
        } catch (Exception e) {
            request.getSession().setAttribute("msg", "An error occurred while adding staff.");
            response.sendRedirect("registerstaff");
        }
    }
}
