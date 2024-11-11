package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import dal.AccountDAO;
import dal.ContactInformationDAO;
import model.ContactInformation;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet(name = "UpdateProfileServlet1", urlPatterns = {"/profile1"})
public class UpdateProfileServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("page-account-information.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone");
        Integer genderID;
        LocalDateTime birth;
        try{
            birth = LocalDate.parse(request.getParameter("birth"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        }catch(DateTimeParseException e){
            birth = null;
        }
        try{
            genderID = Integer.valueOf(request.getParameter("gender"));
        }catch (NumberFormatException e){
            genderID = null;
        }
        AccountDAO accountDAO = new AccountDAO();
        HttpSession session = request.getSession();
        ContactInformationDAO contactInfoDAO = new ContactInformationDAO();
        Account account = (Account) session.getAttribute("account");

        // Find contact information in the database
        ContactInformation contact = contactInfoDAO.getContactInformationByAddressAndPhone(address, phoneNumber);
        //if contact don't have in database, add new contact to database
        if (contact == null) {
            contact = new ContactInformation(address, phoneNumber);
            contact.setContactInformationID(contactInfoDAO.addContact(contact));
        }
        Account account1 = new Account(account.getAccountID(),account.getRoleID(),account.getEmail(),name,genderID, account.getPassword(), birth,account.getTime(),account.getStatusID());
        accountDAO.updateAccountInformation(account1);
        request.getSession().removeAttribute("account");
        request.getSession().removeAttribute("contactInformation");
        request.getSession().setAttribute("account", account1);
        request.getSession().setAttribute("contactInformation", contact);

        doGet(request, response);
    }
}