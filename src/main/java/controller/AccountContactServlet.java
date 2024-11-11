package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dal.AccountContactDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

@WebServlet(name = "AccountContactServlet", value = "/AccountContact")
public class AccountContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account)request.getSession().getAttribute("account");
        request.setAttribute("contacts", new AccountContactDAO().getAllAccountContact(account.getAccountID()));
        request.getRequestDispatcher("page-account-contact.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}