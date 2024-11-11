package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

@WebServlet(name = "CheckAuthenRegister", value = "/CheckAuthenRegister")
public class CheckAuthenRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("home-page.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String otpSession = (String)session.getAttribute("otp"), otp = request.getParameter("otp");
        LocalDateTime dateTime = (LocalDateTime) session.getAttribute("datetime");
        if(otp.equals(otpSession) && !LocalDateTime.now().isAfter(dateTime)){
            ContactInformationDAO contactInfoDAO = new ContactInformationDAO();
            // Find contact information in the database
            ContactInformation contactSession = (ContactInformation)session.getAttribute("contact");
            contactSession.setContactInformationID(contactInfoDAO.updateContact(contactSession.getAddress(),contactSession.getPhoneNumber()));
            AccountDAO accDAO = new AccountDAO();
            Account account = (Account) session.getAttribute("account");
            account.setStatusID(1);
            Integer accountID = accDAO.addAccount(account);
            if (accountID != null) {
                new AccountContactDAO().addAccountContact(new AccountContact(accountID,contactSession.getContactInformationID(),1));
                new CustomerDAO().addCustomer(new Customer(accountID, 0, 0));
                request.getSession().invalidate();
                response.sendRedirect("home-page.jsp");
            } else {
                // Rollback the contact information in case of failure
                contactInfoDAO.deleteContact(contactSession.getContactInformationID());
                request.setAttribute("msg", "Email đã tồn tại");
                request.getSession().invalidate();
                request.getRequestDispatcher("register").forward(request, response);
            }
        }
        else{
            request.setAttribute("msg", "Wrong otp or expired otp");
            request.getRequestDispatcher("authen-account.jsp").forward(request,response);
        }

    }
}