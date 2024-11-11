package controller;

import dal.CertificateDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Certification;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CertificationSearchServlet", value = "/certificationSearch")
public class CertificationSearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CertificateDAO certificationDAO = new CertificateDAO();
        String searchKeyword = request.getParameter("searchKeyword");
        List<Certification> certificationList = certificationDAO.searchCertifications(searchKeyword);

        request.setAttribute("certificationList", certificationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("page-certification.jsp");
        dispatcher.forward(request, response);
    }
}
