package controller;

import java.io.IOException;
import java.util.List;
import dal.CertificateDAO;
import dal.CertificateIssuerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Certification;
import model.CertificateIssuer;

@WebServlet(name = "CertificationListServlet", value = "/certificationList")
public class CertificationListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CertificateDAO certificationDAO = new CertificateDAO();
        CertificateIssuerDAO issuerDAO = new CertificateIssuerDAO();
        List<Certification> certificationList = certificationDAO.getAllCertifications();
        List<CertificateIssuer> issuerList = issuerDAO.getAllCertificateIssuers();

        request.setAttribute("certificationList", certificationList);
        request.setAttribute("issuerList", issuerList);

        request.getRequestDispatcher("page-certification.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
