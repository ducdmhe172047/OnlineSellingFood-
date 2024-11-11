package controller;

import java.io.IOException;

import common.Mail;
import common.RandomPasswordGenerator;
import dal.AccountDAO;
import dal.OtpDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import model.Otp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SendOtpForgotpasswordStaffController", urlPatterns = "/forgotpasswordstaff")
public class SendOtpForgotpasswordStaffController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountDAO daoAccount = new AccountDAO();
        OtpDAO daoOtp = new OtpDAO();
        Account account = daoAccount.getAccountByEmail(email);
        String msg = "msg";
        String errorMsg = "Không thể gửi email, vui lòng thử lại.";
        String completeMsg = "Mã otp mới đã được gửi đến email của bạn.";

        // Kiểm tra email có tồn tại trong database không
        if (account == null) {
            request.setAttribute(msg, errorMsg);
            return;
        }
        else{
            // Tạo otp
            String newOtp = RandomPasswordGenerator.generateRandomString();

            Otp otp =  new Otp(account.getAccountID(), newOtp, LocalDateTime.now().plusMinutes(5));
            // Kiểm tra OTP trong DB và lưu hoặc cập nhật OTP
            if (daoOtp.addOtp(otp)==null) {
                daoOtp.updateOtp(otp);
            }

            // Gửi email
            boolean emailSent = Mail.sendEmail(email, newOtp);
            if (emailSent) {
                request.setAttribute(msg, completeMsg);
            } else {
                request.setAttribute(msg, errorMsg);
            }
        }
        request.getRequestDispatcher("ForgotPasswordStaff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}