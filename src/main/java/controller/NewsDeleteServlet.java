package controller;

import dal.NewsDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NewsDeleteServlet", urlPatterns = {"/newsDelete"})
@MultipartConfig
public class NewsDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newsID = Integer.parseInt(request.getParameter("newsid"));
        NewsDAO newsDAO = new NewsDAO();
        boolean result = newsDAO.delete(newsID);

        if (result) {
            request.getSession().setAttribute("msg", "Xóa tin tức thành công.");
        } else {
            request.getSession().setAttribute("msg", "Có lỗi xảy ra khi xóa tin tức.");
        }

        response.sendRedirect("addNew");
    }
}
