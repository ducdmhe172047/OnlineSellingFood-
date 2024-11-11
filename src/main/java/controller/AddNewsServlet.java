package controller;

import common.ImgFile;
import dal.ImgDAO;
import dal.NewsDAO;
import dal.StaffDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import model.News;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@MultipartConfig
@WebServlet(name = "AddNewsServlet", value = "/addNew")
public class AddNewsServlet extends HttpServlet {
    private NewsDAO newsDAO;
    private ImgDAO imgDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        newsDAO = new NewsDAO();
        imgDAO = new ImgDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("list", newsDAO.getAll());
        request.getRequestDispatcher("add-new.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        int staffID = new StaffDAO().getStaffByAccountID(account.getAccountID()).getStaffID();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Integer imgID = ImgFile.importImg(request.getPart("img"),"news");
        String status = request.getParameter("status");
        boolean isActive = "active".equals(status);
        News news = new News(staffID, title, imgID, LocalDateTime.now(), content, isActive);
        String msg;
        if(new NewsDAO().insert(news)){
            msg = "Thêm thành công";
        }
        else msg = "Thêm thất bại";
        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("addNew");
    }
}