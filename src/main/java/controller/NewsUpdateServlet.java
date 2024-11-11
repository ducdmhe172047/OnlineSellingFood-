package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Date;

import common.ImgFile;
import dal.NewsDAO;
import dal.ImgDAO;
import dal.StaffDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Account;
import model.News;
import model.Img;


@WebServlet(name = "NewsUpdateServlet", value = "/newsupdate")
@MultipartConfig
public class NewsUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        int newsID = Integer.parseInt(request.getParameter("newsid"));
        int staffID = new StaffDAO().getStaffByAccountID(account.getAccountID()).getStaffID();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String status = request.getParameter("status");
        boolean isActive = "active".equals(status);
        News news = new News(newsID, staffID, title, LocalDateTime.now(), content, isActive);
        String msg;
        if(new NewsDAO().update(news)){
            msg = "Cập nhật tin tức thành công.";
        }
        else{
            msg = "Có lỗi xảy ra khi cập nhật tin tức.";
        }
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("addNew");
    }
}
