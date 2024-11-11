package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import common.ImgFile;
import dal.CertificateDAO;
import dal.ImgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Certification;
import model.Img;

@WebServlet(name = "CertificationCreateUpdateServlet", value = "/certificationCU")
@MultipartConfig
public class CertificationCreateUpdateServlet extends HttpServlet {

    private CertificateDAO certificationDAO = new CertificateDAO();
    private ImgDAO imgDAO = new ImgDAO();

    // Thư mục lưu file
    private static final String IMG_FOLDER = "C:\\Users\\admin\\OneDrive\\Documents\\GitHub\\OnlineSellingFood\\src\\main\\webapp\\Img";

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String certificationID = request.getParameter("certificationID");
        String name = request.getParameter("name");
        String detail = request.getParameter("detail");
        String certificateIssuerID = request.getParameter("certificateIssuerID");

        /*
        int imgID = -1;

        Part filePart = request.getPart("img");
        String fileName = getFileName(filePart);
        if (fileName != null && !fileName.isEmpty()) {
            InputStream fileContent = filePart.getInputStream();
            byte[] imageBytes = fileContent.readAllBytes();
            File uploadDir = new File(IMG_FOLDER);
            if (!uploadDir.exists()) {
                boolean dirCreated = uploadDir.mkdir();
                System.out.println("Img folder created: " + dirCreated);
            }

            String filePath = IMG_FOLDER + "\\" + fileName;
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(imageBytes);
                System.out.println("New file saved at: " + filePath);
            }

            Img img = new Img();
            img.setImglink(fileName);
            imgID = imgDAO.addImg1(img);
        }

         */
        int imgID = ImgFile.importImg(request.getPart("img"), "Certification");

        Certification existingCertification = null;
        if (certificationID != null && !certificationID.isEmpty()) {
            existingCertification = certificationDAO.getCertificationById(Integer.parseInt(certificationID));

            boolean isUsed = certificationDAO.isCertificationUsed(Integer.parseInt(certificationID));
            if (isUsed) {
                request.getSession().setAttribute("msg", "Certification này đang được sử dụng và không thể cập nhật hoặc xóa.");
                List<Certification> certificationList = certificationDAO.getAllCertifications();
                request.setAttribute("certificationList", certificationList);
                request.getRequestDispatcher("page-certification.jsp").forward(request, response);
                return;
            }

            if (existingCertification != null) {
                // Xóa chứng chỉ cũ
                certificationDAO.deleteCertification(existingCertification.getCertificationID());
                // Lấy ImgID từ chứng chỉ cũ
                int oldImgID = existingCertification.getImgID();

                // Xóa ảnh cũ sau khi xóa chứng chỉ
                if (oldImgID != -1) {
                    Img oldImg = imgDAO.getImgById(oldImgID);
                    if (oldImg != null) {
                        ImgFile.deleteImg(oldImg.getImglink());
                    }
                }
            }
        }

        if (name != null && !name.isEmpty() && detail != null && !detail.isEmpty()) {
            Integer imgIDToUse = imgID != -1 ? imgID : existingCertification != null ? existingCertification.getImgID() : null;

            boolean isCreated = certificationDAO.createCertification1(name, detail, certificateIssuerID, imgIDToUse);
            if (isCreated) {
                request.getSession().setAttribute("msg", "Chứng chỉ đã được tạo thành công.");
                response.sendRedirect("certificationList");
            } else {
                System.out.println("Failed to create new certification.");
                request.getSession().setAttribute("msg", "Không thể tạo chứng chỉ mới.");
                response.sendRedirect("warehouseList");  // Chuyển hướng về danh sách chứng chỉ
            }
        } else {
            System.out.println("Invalid certification information.");
            request.getSession().setAttribute("msg", "Thông tin chứng chỉ không hợp lệ.");
            response.sendRedirect("warehouseList");  // Chuyển hướng về danh sách chứng chỉ
        }
    }

}
