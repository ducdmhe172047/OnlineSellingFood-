package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import common.Mail;
import common.RandomPasswordGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

@WebServlet(name = "SendAuthenOtpServlet", value = "/SendAuthenOtpServlet")
public class SendAuthenOtpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
        String otp = RandomPasswordGenerator.generateRandomString();
        Mail.sendEmail(account.getEmail(), otp);
        session.setAttribute("otp", otp);
        session.setAttribute("datetime", LocalDateTime.now().plusMinutes(5));
        response.sendRedirect("register-authen.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}