package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dal.*;
import dto.VoucherResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

@WebServlet(name = "LoadVoucher", value = "/LoadVoucher")
public class LoadVoucher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Customer customer = new CustomerDAO().getCustomerByAccountID(account.getAccountID());
        VoucherDAO voucherDAO = new VoucherDAO();
        DiscountDAO discountDAO = new DiscountDAO();
        List<CustomerVoucher> customerVouchers = new CustomerVoucherDAO().getCustomerVoucher(customer.getCustomerID());
        List<VoucherResponse> vouchers = new ArrayList<>();
        Voucher voucher;
        Discount discount;
        for(CustomerVoucher customerVoucher : customerVouchers) {
            voucher = voucherDAO.getVoucherById(customerVoucher.getVoucherID());
            discount = discountDAO.getDiscountById(voucher.getDiscountID());
            if(discount.getEndTime().isAfter(LocalDateTime.now()) && voucher.getInventory() > 0) {
                vouchers.add(new VoucherResponse(voucher.getVoucherID(),discount.getDiscountID(),discount.getDiscountPercent(),discount.getStartTime(),discount.getEndTime(),voucher.getQuantity(),voucher.getInventory()));
            }
        }
        String sort_str = request.getParameter("sort");
        if(sort_str != null) {
            Integer sort = Integer.parseInt(sort_str);
            if(sort == 0) {
                vouchers.sort(Comparator.comparing(VoucherResponse::getVoucherID));
            } else if(sort == 1) {
                vouchers.sort(Comparator.comparing(VoucherResponse::getDiscountPercent));
            } else if(sort == 2) {
                vouchers.sort(Comparator.comparing(VoucherResponse::getStartTime));
            } else if(sort == 3) {
                vouchers.sort(Comparator.comparing(VoucherResponse::getEndTime));
            }
        }
        request.setAttribute("vouchers", vouchers);
        request.getRequestDispatcher("page-account-voucher.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rawVoucherID = request.getParameter("voucherID");
        int voucherID = Integer.parseInt(rawVoucherID);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        CustomerVoucherDAO customerVoucherDAO = new CustomerVoucherDAO();
        String msg = "";
        String error = "Mã giảm giá đã hết hoặc không tồn tại";
        Voucher voucher = new VoucherDAO().getVoucherById(voucherID);
        if(voucher!=null){
            Discount discount = new DiscountDAO().getDiscountById(voucher.getDiscountID());
            if(voucher.getInventory() > 0 && discount.getEndTime().isAfter(LocalDateTime.now())) {
                if(customerVoucherDAO.addCustomerVoucher(new CustomerDAO().getCustomerIDByAccountID(account.getAccountID()), voucherID)) {
                    msg = "Thêm mã giảm giá thành công";
                } else {
                    msg = error;
                }
            }
            else msg = error;
        }
        else msg = error;
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("LoadVoucher");
    }
}