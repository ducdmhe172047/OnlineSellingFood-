    package controller;

    import dal.CustomerVoucherDAO;
    import dal.DiscountDAO;
    import dal.VoucherDAO;
    import dto.CustomerVoucherResponse;
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
    import java.util.ArrayList;
    import java.util.List;

    @WebServlet(name = "CustomerVoucherServlet", urlPatterns = {"/customervoucher"})
    public class CustomerVoucherServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
            CustomerVoucherDAO cvd = new CustomerVoucherDAO();
            List<CustomerVoucherResponse> cvr = cvd.getCustomerVoucher();

            VoucherDAO vDao = new VoucherDAO();
            int voucherID = Integer.parseInt(request.getParameter("id"));
            VoucherResponse vr = vDao.getVoucher(voucherID);

            List<Integer> selectedCustomerID = cvd.getCustomerIDWithVoucher(voucherID);

            request.setAttribute("voucher", vr);
            request.setAttribute("customervoucher", cvr);
            request.setAttribute("selectedCustomerID", selectedCustomerID);

            request.getRequestDispatcher("add-customer-voucher.jsp").forward(request, respone);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String[] selectedCustomer = request.getParameterValues("selectedCustomer");
            int voucherID = Integer.parseInt(request.getParameter("voucherID"));
            CustomerVoucherDAO cvd = new CustomerVoucherDAO();
            List<Integer> customerIds = new ArrayList<>();
            if (selectedCustomer != null) {
                for (String id : selectedCustomer) {
                    customerIds.add(Integer.parseInt(id));
                }
            }
            for (Integer customerID : customerIds) {
                if(!cvd.checkExistCustomerVoucher(customerID,voucherID)){
                    cvd.addCustomerVoucher(customerID, voucherID);
                }
            }
            List<Integer> existingCustomerIDs = cvd.getCustomerIDWithVoucher(voucherID);
            for (Integer customerID : existingCustomerIDs) {
                if (!customerIds.contains(customerID)) {
                    cvd.deleteCustomerVoucher(customerID, voucherID);
                }
            }
            response.sendRedirect("voucher");
        }

    }
