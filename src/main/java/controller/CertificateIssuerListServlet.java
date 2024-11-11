package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.CertificateIssuerDAO;
import model.CertificateIssuer;

@WebServlet(name = "CertificateIssuerListServlet", value = "/certificateIssuerList")
public class CertificateIssuerListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CertificateIssuerDAO certificateIssuerDAO = new CertificateIssuerDAO();
        List<CertificateIssuer> CIList = certificateIssuerDAO.getAllCertificateIssuers();
        request.setAttribute("certificateList", CIList);
        request.getRequestDispatcher("page-certificateIssuer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
