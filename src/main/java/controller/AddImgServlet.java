package controller;

import dal.ImgDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Img;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;




@MultipartConfig
@WebServlet(name = "AddImgServlet", value = "/AddImgServlet")
public class AddImgServlet extends HttpServlet {


    // Thư mục lưu file
    private static final String IMG_FOLDER = "C:/Users/admin/OneDrive/Documents/GitHub/OnlineSellingFood/src/main/webapp/Img";

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null; // Trả về null nếu không tìm thấy tên file
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgDAO idao = new ImgDAO();

        // Nhận file từ request
        Part filePart = request.getPart("img");
        String fileName = getFileName(filePart);

        if (fileName == null || fileName.isEmpty()) {
            response.getWriter().println("Không tìm thấy file ảnh.");
            return;
        }

        // Đọc dữ liệu ảnh
        InputStream fileContent = filePart.getInputStream();
        byte[] imageBytes = fileContent.readAllBytes();

        // Kiểm tra thư mục Img tồn tại
        File uploadDir = new File(IMG_FOLDER);
        if (!uploadDir.exists()) {
            boolean dirCreated = uploadDir.mkdir();
            System.out.println("Thư mục Img được tạo: " + dirCreated);
        }

        // Lưu file vào thư mục Img
        String filePath = IMG_FOLDER + File.separator + fileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageBytes);
            System.out.println("File đã được lưu tại: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file: " + e.getMessage());
            response.getWriter().println("Lỗi khi lưu file: " + e.getMessage());
            return;
        }

        // Lưu thông tin ảnh vào database
        String imgLink = "Img/" + fileName;
        Img img = new Img();
        img.setImglink(imgLink);
        idao.addImg(img);

        response.getWriter().println("Upload thành công!");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgDAO idao = new ImgDAO();
        String img = request.getParameter("img");
        Img imgg = new Img();
        imgg.setImglink(img);
        idao.addImg(imgg);

    request.getRequestDispatcher("PageProductList.jsp").forward(request, response);




    }
}
