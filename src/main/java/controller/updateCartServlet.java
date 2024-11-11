package controller;

import dal.CartDAO;
import dal.ImportProductDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cart;
import java.io.IOException;

@WebServlet(name = "updateCartServlet", value = "/updateCartServlet")
public class updateCartServlet extends HttpServlet {

    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        cartDAO = new CartDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        ImportProductDAO importProductDAO =new ImportProductDAO();
        int quantityMax = importProductDAO.countProductQuantity(productId);
        if(quantity > quantityMax){
            request.getSession().setAttribute("msg1", "Không thể đặt quá số lượng sản phẩm trong kho");
            response.sendRedirect("cart?customerId="+customerId);
            return;
        }
        Cart cart = cartDAO.getCartByCustomerIdAndProductId(customerId, productId);
        if (cart != null) {
            cart.setQuantity(quantity);
            cartDAO.updateCartQuantity(customerId, productId, quantity);
        }

        String referer = request.getHeader("referer");
        if (referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("home-page.jsp");
        }
    }
}
