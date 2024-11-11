package controller;

import java.io.IOException;
import dal.CertificateIssuerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CertificateIssuer;

@WebServlet(name = "CICreateUpdateServlet", value = "/certificateIssuerCU")
public class CICreateUpdateServlet extends HttpServlet {
    private CertificateIssuerDAO certificateIssuerDAO = new CertificateIssuerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String certificateIssuerID = request.getParameter("certificateIssuerID");
        if (certificateIssuerID != null) {
            CertificateIssuer certificateIssuer = certificateIssuerDAO.getCertificateIssuerById(Integer.parseInt(certificateIssuerID));
            request.setAttribute("certificateIssuerID", certificateIssuer.getCertificateIssuerID());
            request.setAttribute("certificateIssuerName", certificateIssuer.getName());
            request.setAttribute("certificateIssuerDetail", certificateIssuer.getDetail());
        }
        request.getRequestDispatcher("page-certificateIssuer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String certificateIssuerID = request.getParameter("certificateIssuerID");
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");

        // Kiểm tra trùng tên
        if (certificateIssuerID == null || certificateIssuerID.isEmpty()) {
            if (certificateIssuerDAO.isNameExists(name)) {
                request.getSession().setAttribute("msg", "Tên nhà phát hành đã tồn tại, vui lòng chọn tên khác.");
                response.sendRedirect("certificateIssuerList");
                return;
            }
            // Thực hiện thêm nếu không bị trùng tên
            if (name != null && !name.isEmpty() && detail != null && !detail.isEmpty()) {
                boolean isCreated = certificateIssuerDAO.createCertificateIssuer(name, detail);
                if (isCreated) {
                    response.sendRedirect("certificateIssuerList");
                } else {
                    request.getSession().setAttribute("msg", "Không thể tạo một nhà phát hành mới");
                    response.sendRedirect("certificateIssuerList");
                }
            } else {
                request.setAttribute("error", "Name and detail cannot be empty.");
                request.getRequestDispatcher("certificateIssuerCU").forward(request, response);
            }
        } else {
            int issuerID = Integer.parseInt(certificateIssuerID);
            // Kiểm tra trùng tên trừ khi cập nhật cho chính nó
            CertificateIssuer existingIssuer = certificateIssuerDAO.getCertificateIssuerById(issuerID);
            if (!existingIssuer.getName().equals(name) && certificateIssuerDAO.isNameExists(name)) {
                request.getSession().setAttribute("msg", "Tên nhà phát hành đã tồn tại, vui lòng chọn tên khác.");
                response.sendRedirect("certificateIssuerList");
            }
            // Thực hiện cập nhật nếu không bị trùng tên
            if (name != null && !name.isEmpty() && detail != null && !detail.isEmpty()) {
                boolean isUpdated = certificateIssuerDAO.updateCertificateIssuer(issuerID, name, detail);
                if (isUpdated) {
                    response.sendRedirect("certificateIssuerList");
                } else {
                    request.getSession().setAttribute("msg", "Không thể cập nhật nhà phát hành");
                    response.sendRedirect("certificateIssuerList");
                }
            } else {
                request.setAttribute("error", "Name and detail cannot be empty.");
                request.getRequestDispatcher("certificateIssuerCU").forward(request, response);
            }
        }
    }

}
