package common;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    // Phương thức gửi email
    public static boolean sendEmail(String recipientEmail, String otp) {
        final String from = "anhnhhe163978@fpt.edu.vn"; // Địa chỉ email gửi
        final String password = "lnkdyhvhirskerrc"; // Mật khẩu ứng dụng

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host
        props.put("mail.smtp.port", "587"); // TLS port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo Authenticator
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            // Tạo tin nhắn
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            msg.setSubject("OnlineSellingFood OTP");
            msg.setText("New OTP: " + otp);

            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
