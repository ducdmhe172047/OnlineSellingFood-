package controller;

import dal.CertificateIssuerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CertificateIssuerDeleteServlet", value = "/certificateIssuerDelete")
public class CertificateIssuerDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String certificateIssuerIdParam = request.getParameter("certificateIssuerID");
        CertificateIssuerDAO ciDAO = new CertificateIssuerDAO();

        if (certificateIssuerIdParam != null) {
            int certificateIssuerID = Integer.parseInt(certificateIssuerIdParam);
            boolean isUsed = ciDAO.isCertificateIssuerUsed(certificateIssuerID);

            if (isUsed) {
                // Nếu đang được sử dụng, hiển thị thông báo lỗi và dừng tại đây
                request.getSession().setAttribute("msg", "Nhà phát hành này đang được sử dụng và không thể xóa.");
                response.sendRedirect("certificateIssuerList");
                return;
            } else {
                // Nếu không được sử dụng, tiến hành xóa
                ciDAO.deleteCertificateIssuer(certificateIssuerID);
                request.getSession().setAttribute("msg", "Xóa thành công nhà phát hành.");
            }
        }

        response.sendRedirect("certificateIssuerList");
    }
}
