package common;

import dal.ImgDAO;
import jakarta.servlet.http.Part;
import model.Img;
import model.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ImgFile {
    private static final Logger logger = Logger.getLogger(ImgFile.class.getName());
    // Thư mục lưu file
    private static final String FOLDER = "E:/Semester5/SWP_Project2/SwpImgDriver/";
    private static final String IMG_FOLDER = FOLDER+"Img/";


    private static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }

    public static Integer importImg(Part filePart, String name) throws IOException {
        String fileName = getFileName(filePart);
        if (fileName != null && !fileName.isEmpty()) {
            InputStream fileContent = filePart.getInputStream();
            byte[] imageBytes = fileContent.readAllBytes();
            File uploadDir = new File(IMG_FOLDER);
            if (!uploadDir.exists()) {
                boolean dirCreated = uploadDir.mkdir();
                logger.info("Img folder created: " + dirCreated);
            }
            String[] fileNameFull = fileName.split("\\.");
            try {
                String fileNameLast = Encrypt.toHexString(Encrypt.getSHA(fileNameFull[0]+name+ LocalDateTime.now()))+"."+fileNameFull[fileNameFull.length-1];
                String filePath = IMG_FOLDER + fileNameLast;
                Path path = Paths.get(filePath);
                if (!Files.exists(path)) {
                    try (FileOutputStream fos = new FileOutputStream(filePath)) {
                        fos.write(imageBytes);
                        logger.info("New file saved at: " + filePath);
                    }
                    autoCommit();
                    return new ImgDAO().addImg(new Img(fileNameLast));
                }
            } catch (NoSuchAlgorithmException e) {
                logger.info(e.getMessage());
            }
        }
        return null;
    }

    public static boolean deleteImg(String filePath){
        try {
            Files.delete(Paths.get(IMG_FOLDER+filePath));
            autoCommit();
            return true;
        } catch (IOException e) {
            logger.info("Can not delete file at path: " + filePath);
            return false;
        }
    }

    public static boolean autoCommit(){
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd /d "+FOLDER+" && git add -A && git commit -m \"Your Message\" && git push");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            p.waitFor();
            /*
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                System.out.println(line);
            }
            */
            return true;
        } catch(IOException | InterruptedException e) {
            logger.info(e.getMessage());
        }
        return false;
    }
}