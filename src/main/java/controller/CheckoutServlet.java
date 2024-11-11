package controller;

import dal.*;
import dto.ApplyVoucherToCheckoutResponse;
import dto.CheckoutContactDetailResponse;
import dto.ProductCheckoutResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.AccountContact;
import model.ContactInformation;
import model.Customer;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})

public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        CheckoutDAO checkoutDAO = new CheckoutDAO();
        CheckoutContactDetailResponse checkoutContactDetailResponse=checkoutDAO.getCheckoutContactDetail(account.getAccountID());
        CustomerDAO customerDAO = new CustomerDAO();
        CartDAO cartDAO = new CartDAO();
        Customer customer = customerDAO.getCustomerByAccountID(account.getAccountID());
        if (!cartDAO.hasProductInCart(customer.getCustomerID())) {
            session.setAttribute("msg", "Không có sản phẩm trong giỏ hàng, hãy quay trở về mua sắm");
            response.sendRedirect("shop-cart.jsp");
            return;
        }

        request.setAttribute("c", checkoutContactDetailResponse);
        Customer c=customerDAO.getCustomerByAccountID(account.getAccountID());
        List<ProductCheckoutResponse> list=checkoutDAO.getProductCheckout(c.getCustomerID());
        int subTotalPrice = 0;
        for(ProductCheckoutResponse p:list){
            subTotalPrice+=p.getPriceAfterDiscount()*p.getQuantity();
        }
        List<ApplyVoucherToCheckoutResponse> listVoucher=checkoutDAO.getApplyVoucherToCheckout(c.getCustomerID());
        request.setAttribute("list", list);
        request.setAttribute("subTotalPrice", subTotalPrice);
        request.setAttribute("listVoucher", listVoucher);
        request.setAttribute("selectedVoucherID", null);
        request.getRequestDispatcher("shop-checkout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        CheckoutDAO checkoutDAO = new CheckoutDAO();
        CheckoutContactDetailResponse checkoutContactDetailResponse=checkoutDAO.getCheckoutContactDetail(account.getAccountID());
        request.setAttribute("c", checkoutContactDetailResponse);

        String voucherData = request.getParameter("voucherData");
        int subtotalPrice = Integer.parseInt(request.getParameter("subTotalPrice"));

        if (voucherData != null && !voucherData.isEmpty()) {
            int discountPercent=checkoutDAO.getDiscountPercentByVoucherID(Integer.parseInt(voucherData));
            int discountedSubtotal = subtotalPrice - (subtotalPrice * discountPercent / 100);
            request.setAttribute("selectedVoucherID", Integer.parseInt(voucherData));
            request.setAttribute("discountedSubtotal", discountedSubtotal);
        }

        CustomerDAO customerDAO = new CustomerDAO();

        Customer c = customerDAO.getCustomerByAccountID(account.getAccountID());
        List<ProductCheckoutResponse> list = checkoutDAO.getProductCheckout(c.getCustomerID());
        List<ApplyVoucherToCheckoutResponse> listVoucher = checkoutDAO.getApplyVoucherToCheckout(c.getCustomerID());

        request.setAttribute("list", list);
        request.setAttribute("subTotalPrice", subtotalPrice);
        request.setAttribute("listVoucher", listVoucher);
        request.getRequestDispatcher("shop-checkout.jsp").forward(request, response);



    }


}