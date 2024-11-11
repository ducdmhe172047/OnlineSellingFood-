package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dal.AccountContactDAO;
import dal.ContactInformationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.AccountContact;
import model.ContactInformation;

@WebServlet(name = "UpdateContactServlet", value = "/UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
    private AccountContactDAO accountContactDAO = new AccountContactDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int contactID = Integer.parseInt(request.getParameter("contactID"));
        Account account = (Account)request.getSession().getAttribute("account");
        try{
            int isDefault = Integer.parseInt(request.getParameter("isdefault"));
            if(isDefault==1){
                AccountContact accountContact = accountContactDAO.getAccountContact(account.getAccountID());
                int oldContact;
                while(accountContact!=null) {
                    oldContact = accountContact.getContactInformationID();
                    if (oldContact == contactID) {
                        response.sendRedirect("AccountContact");
                        return;
                    }
                    accountContactDAO.updateDefaultAccountContact(0,account.getAccountID(),oldContact);
                    accountContact = accountContactDAO.getAccountContact(account.getAccountID());
                }
                accountContactDAO.updateDefaultAccountContact(1,account.getAccountID(),contactID);
            }
        }catch(NumberFormatException e){
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phone");

            Integer newContactID = new ContactInformationDAO().updateContact(address,phoneNumber);
            if(contactID!=newContactID){
                accountContactDAO.updateAccountContact(newContactID,contactID, account.getAccountID());
            }
            else{
                request.setAttribute("msg","Contact has existed");
                request.getRequestDispatcher("page-account-update-contact.jsp").forward(request,response);
                return;
            }
        }
        response.sendRedirect("AccountContact");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone");
        Account account = (Account)request.getSession().getAttribute("account");
        Integer contactID = new ContactInformationDAO().updateContact(address,phoneNumber);
        if(accountContactDAO.addAccountContact(new AccountContact(account.getAccountID(),contactID,0))){
            response.sendRedirect("AccountContact");
        }
        else{
            request.setAttribute("msg","Contact has existed");
            request.getRequestDispatcher("page-account-update-contact.jsp").forward(request,response);
        }
    }
}