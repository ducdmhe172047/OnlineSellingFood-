package controller;
import dal.CartDAO;
import dal.CustomerDAO;
import dal.ImportProductDAO;
import dal.ProductDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;


import java.io.IOException;


@WebServlet(name = "AddToCartServlet", value = "/addtocart")
public class AddtoCartServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        // Lấy customerID từ accountID

        if (account == null) {
            response.sendRedirect("page-login.jsp");
            return;
        }
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomerByAccountID(account.getAccountID());
        if (customer == null) {
            response.sendRedirect("logout");
            return;
        }
        ImportProductDAO importProductDAO = new ImportProductDAO();
        int quantityMax = importProductDAO.countProductQuantity(productID);
        int customerID = customer.getCustomerID();

        // Sử dụng CartDAO để thêm sản phẩm vào giỏ hàng
        CartDAO cartDAO = new CartDAO();
        Cart existingCart = cartDAO.getCartByCustomerIdAndProductId(customerID, productID);
        int quantity = 1;
        if (existingCart != null) {
            quantity = existingCart.getQuantity() + 1;
        }
        if(quantity > quantityMax){
            request.getSession().setAttribute("msg", "Không thể đặt quá số lượng sản phẩm trong kho");
            response.sendRedirect("cart?customerId=" + customerID);
            return;
        }
        if (existingCart != null) {
            existingCart.setQuantity(quantity);
            cartDAO.update(existingCart);
        } else {
            Cart newCart = new Cart();
            newCart.setCustomerID(customerID);
            newCart.setProductID(productID);
            newCart.setQuantity(quantity);
            cartDAO.insert(newCart);
        }

        // Lấy URL của trang trước đó từ header "referer" và chuyển hướng về đó
        String referer = request.getHeader("referer");
        if (referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("home-page.jsp");
        }
    }


}
