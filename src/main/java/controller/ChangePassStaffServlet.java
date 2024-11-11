package controller;

import common.Encrypt;
import dal.OtpDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import model.Account;
import dal.AccountDAO;
import jakarta.servlet.http.HttpSession;
import dal.ContactInformationDAO;
import model.ContactInformation;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChangePassStaffServlet", urlPatterns = {"/changepassstaff"})
public class ChangePassStaffServlet extends HttpServlet {
    private String changePass = "page-change-pass-staff.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email"), otp = request.getParameter("otp");
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByEmail(email);
        if(new OtpDAO().checkOtp(account.getAccountID(),otp)){
            account.setStatusID(2);
            request.getSession().setAttribute("account",account);
            response.sendRedirect(changePass);
        }
        else{
            request.setAttribute("msg","OTP không hợp lệ");
            request.getRequestDispatcher("ForgotPasswordStaff.jsp?email="+email).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String np = request.getParameter("newPassword");
        String cp = request.getParameter("confirmPassword");
        AccountDAO dao = new AccountDAO();
        Account account = (Account) request.getSession().getAttribute("account");

        if(!np.matches(".*[A-Z].*") || !np.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            request.setAttribute("msg", "Mật khẩu mới phải chứa ít nhất một ký tự viết hoa và một ký tự đặc biệt!");
            request.getRequestDispatcher(changePass).forward(request, response);
            return;
        }
        if (!np.equals(cp)) {
            request.setAttribute("msg", "Xác nhận mật khẩu không khớp");
            request.getRequestDispatcher(changePass).forward(request, response);
        } else {
            if (dao.getAccountByEmailPassword(account.getEmail(), np)!=null) {
                request.setAttribute("msg", "Mật khẩu mới không được trùng với mật khẩu cũ");
                request.getRequestDispatcher(changePass).forward(request, response);
            } else {
                dao.updateAccountPassword(account.getAccountID(), np);
                account.setStatusID(1);
                dao.updateAccountInformation(account);
                response.sendRedirect("logout");
            }
        }


    }


}
