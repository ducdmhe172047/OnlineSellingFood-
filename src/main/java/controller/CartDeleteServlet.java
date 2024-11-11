package controller;

import dal.CartDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartDeleteServlet", value = "/deleteCartServlet")
public class CartDeleteServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        CartDAO cartDAO = new CartDAO();

        if ("removeItem".equals(action)) {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int affectedRows = cartDAO.deleteByCustomerIdAndProductId(customerId, productId);

            if (affectedRows > 0) {
                request.setAttribute("message", "Xóa sản phẩm thành công!");
            } else {
                request.setAttribute("message", "Không tìm thấy sản phẩm để xóa.");
            }
        } else if ("clearCart".equals(action)) {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int affectedRows = cartDAO.deleteByCustomerId(customerId); // Gọi phương thức deleteByCustomerId để xóa toàn bộ giỏ hàng

            if (affectedRows > 0) {
                request.setAttribute("message", "Xóa toàn bộ giỏ hàng thành công!");
            } else {
                request.setAttribute("message", "Không có sản phẩm nào trong giỏ hàng.");
            }
        }

        String referer = request.getHeader("referer");
        if (referer != null && !referer.isEmpty()) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("home-page.jsp");
        }
    }
}
