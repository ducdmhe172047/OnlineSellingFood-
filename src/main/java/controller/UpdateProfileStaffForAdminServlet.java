package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dal.AccountContactDAO;
import dal.AccountDAO;
import dal.ContactInformationDAO;
import dal.StaffDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import model.AccountContact;
import model.ContactInformation;
import model.Staff;

@WebServlet(name = "UpdateProfileStaffForAdminServletServlet", urlPatterns = {"/updateProfileStaffForAdmin"})
public class UpdateProfileStaffForAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}