package controller;

import dal.AccountContactDAO;
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
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("page-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errorMessages = new ArrayList<>();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember=request.getParameter("rem");

        Cookie cu=new Cookie("cuser",email);
        Cookie cp=new Cookie("cpass",password);
        Cookie cr=new Cookie("crem",remember);

        if(remember!=null){
            cu.setMaxAge(60*60*24*7);
            cp.setMaxAge(60*60*24*7);
            cr.setMaxAge(60*60*24*7);
        }else{
            cu.setMaxAge(0);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        response.addCookie(cu);
        response.addCookie(cp);
        response.addCookie(cr);

        AccountDAO dao=new AccountDAO();
        Account a= null;
        a = dao.getAccountByEmailPassword(email, password);

        if(a==null){
            request.setAttribute("error","Mật khẩu hoặc tài khoản không đúng!");
            request.getRequestDispatcher("page-login.jsp").forward(request, response);
        } else if (a.getStatusID()==4) {
            request.setAttribute("error","Tài khoản đã bị khóa!");
            request.getRequestDispatcher("page-login.jsp").forward(request, response);
        } else{
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            if(a.getRoleID()==6) response.sendRedirect("home-page.jsp");
            else if(a.getStatusID()==3) response.sendRedirect("page-change-pass-staff.jsp");
            else if(a.getRoleID() == 5) {response.sendRedirect("Dashboard?index=1&search=&orderStatus=");}
            else if(a.getRoleID() == 4) {response.sendRedirect("DashboardI?index=1&search=");}
            else if(a.getRoleID() == 2) {response.sendRedirect("DashboardD");}
            else{
                response.sendRedirect("home-page-staff.jsp");
            }
        }
    }
}