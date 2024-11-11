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

@WebServlet(name = "VoucherModifyServlet", urlPatterns = {"/voucher"})
public class VoucherModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
        VoucherDAO vDao = new VoucherDAO();

        String indexPage=request.getParameter("index");
        if(indexPage==null){
            indexPage="1";
        }
        int index=Integer.parseInt(indexPage);
        List<VoucherResponse> voucherResponse=vDao.getListVoucher(index);

        int count=vDao.getToTalVoucher();
        int endPage=count/5;
        if(count%5!=0){
            endPage++;
        }
        int itemsPerPage = 5;
        int startCount = (index - 1) * itemsPerPage + 1;
        request.setAttribute("startCount", startCount);
        request.setAttribute("voucher", voucherResponse);
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);

        request.getRequestDispatcher("create-voucher.jsp").forward(request, respone);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int discountPercent = Integer.parseInt(request.getParameter("discountPercent"));


            // Định dạng datetime theo tiêu chuẩn ISO 8601 (format của input datetime-local)
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

            // Lấy và chuyển đổi startDate và endDate thành LocalDateTime
            String startDateString = request.getParameter("startDate");
            LocalDateTime startDate = LocalDateTime.parse(startDateString, formatter);
            //response.getWriter().println(startDate);
            String endDateString = request.getParameter("endDate");
            LocalDateTime endDate = LocalDateTime.parse(endDateString, formatter);

            int quantity = Integer.parseInt(request.getParameter("quantity"));

           VoucherDAO vDao = new VoucherDAO();
           DiscountDAO dDao = new DiscountDAO();
           int discountID=vDao.checkExistPercent(discountPercent,startDate,endDate);
           if(discountID==-1){
               discountID=dDao.addDiscount(discountPercent,startDate,endDate);
           }
            Voucher voucher=new Voucher(discountID,quantity,quantity);
           vDao.addVoucher(voucher);
            response.sendRedirect("voucher");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


    }

}
