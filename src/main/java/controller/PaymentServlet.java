package controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.PaymentDAO;
import dal.VoucherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PaymentServlet", urlPatterns = "/payment")
public class PaymentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
    }

    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VoucherDAO  voucherDAO = new VoucherDAO();
        String voucher = req.getParameter("voucherID");
        int voucherID = 0;
        if(voucher!=null && !voucher.isEmpty()){
            voucherID = Integer.parseInt(voucher);
        }
        if(voucherDAO.updateInventoryVoucher(voucherID) || voucherID == 0){
            PaymentDAO dao = new PaymentDAO();
            String vnp_Version = "2.1.0";
            String vnp_Command = "querydr";
            String orderType = "other";
            long amount = Integer.parseInt(req.getParameter("amount")) * 100;
            String bankCode = "NCB";

            String vnp_TxnRef = dao.getRandomNumber(8);
            String vnp_IpAddr = dao.getIpAddress(req);

            String vnp_TmnCode = dao.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            if (bankCode != null && !bankCode.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bankCode);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Nap tien vao tai khoan:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = req.getParameter("language");
            if (locate != null && !locate.isEmpty()) {
                vnp_Params.put("vnp_Locale", locate);
            } else {
                vnp_Params.put("vnp_Locale", "vn");
            }
            vnp_Params.put("vnp_ReturnUrl", dao.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = dao.hmacSHA512(dao.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = dao.vnp_PayUrl + "?" + queryUrl;
            com.google.gson.JsonObject job = new JsonObject();
            job.addProperty("code", "00");
            job.addProperty("message", "success");
            job.addProperty("data", paymentUrl);
            Gson gson = new Gson();
            String voucherID_raw = req.getParameter("voucherID");
            req.getSession().setAttribute("voucherID", voucherID_raw);

//        resp.getWriter().write(gson.toJson(job));
            resp.sendRedirect(paymentUrl);
        }
        else{
            req.getSession().setAttribute("msg","Voucher đã hết lượt sử dụng");
            resp.sendRedirect("checkout");
        }
    }
}