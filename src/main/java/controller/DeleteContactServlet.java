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

@WebServlet(name = "DeleteContactServlet", value = "/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int contactID = Integer.parseInt(request.getParameter("contactID"));
        Account account = (Account)request.getSession().getAttribute("account");
        new AccountContactDAO().deleteAccountContact(account.getAccountID(),contactID);
        response.sendRedirect("AccountContact");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}