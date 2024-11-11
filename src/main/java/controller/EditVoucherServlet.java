package controller;

import dal.DiscountDAO;
import dal.VoucherDAO;
import dto.VoucherResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Voucher;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "EditVoucherServlet", urlPatterns = {"/editvoucher"})
public class EditVoucherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
        VoucherDAO vDao = new VoucherDAO();
        VoucherResponse voucherResponse=vDao.getVoucher(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("voucher", voucherResponse);

        request.getRequestDispatcher("voucher-detail.jsp").forward(request, respone);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           int quantity = Integer.parseInt(request.getParameter("quantity"));
           int voucherID = Integer.parseInt(request.getParameter("voucherID"));
           VoucherDAO vDao = new VoucherDAO();
           vDao.updateQuantityVoucher(voucherID,quantity);
           response.sendRedirect("voucher");
    }

}
