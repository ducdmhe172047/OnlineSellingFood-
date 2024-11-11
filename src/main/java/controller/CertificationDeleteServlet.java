package controller;

import common.ImgFile;
import dal.CertificateDAO;
import java.io.IOException;
import java.util.List;

import dal.ImgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Certification;
import model.Img;

@WebServlet(name = "CertificationDeleteServlet", value = "/certificationDelete")
public class CertificationDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String certificationIdParam = request.getParameter("certificationID");
        CertificateDAO certificationDAO = new CertificateDAO();
        System.out.println(certificationIdParam);
        if (certificationIdParam != null) {
            boolean isUsed = certificationDAO.isCertificationUsed(Integer.parseInt(certificationIdParam));
            if (isUsed) {
                request.getSession().setAttribute("msg", "Certification này đang được sử dụng và không thể cập nhật hoặc xóa.");
                List<Certification> certificationList = certificationDAO.getAllCertifications();
                request.setAttribute("certificationList", certificationList);
                request.getRequestDispatcher("page-certification.jsp").forward(request, response);
                return;
            } else {
                int certificationId = Integer.parseInt(certificationIdParam);
                Certification certification = certificationDAO.getCertificationById(certificationId);
                ImgDAO imgDAO = new ImgDAO();
                Img img = imgDAO.getImgById(certification.getImgID());
                if(certificationDAO.deleteCertification(Integer.parseInt(certificationIdParam))){
                    if(imgDAO.deleteImg(img.getImgID())){
                        ImgFile.deleteImg(img.getImglink());
                    }
                }
            }
        }
        response.sendRedirect("certificationList");
    }
}

