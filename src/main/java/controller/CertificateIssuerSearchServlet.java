package controller;

import dal.CertificateIssuerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CertificateIssuer;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CertificateIssuerSearchServlet", value = "/certificateIssuerSearch")
public class CertificateIssuerSearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CertificateIssuerDAO certificateIssuerDAO = new CertificateIssuerDAO();
        String searchKeyword = request.getParameter("searchKeyword");
        List<CertificateIssuer> certificateList = certificateIssuerDAO.searchCertificateIssuers(searchKeyword);

        request.setAttribute("certificateList", certificateList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("page-certificateIssuer.jsp");
        dispatcher.forward(request, response);
    }
}
