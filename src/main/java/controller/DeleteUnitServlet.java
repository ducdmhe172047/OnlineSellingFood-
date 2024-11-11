package controller;

import dal.UnitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Unit;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteUnitServlet", urlPatterns = {"/deleteunit"})
public class DeleteUnitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int unitId = Integer.parseInt(request.getParameter("unitid"));
        String msg;
        if(new UnitDAO().deleteUnit(unitId)){
            msg = "Xóa thất bại";
        }
        else msg = "Xóa thành công";
        response.sendRedirect("unitlist?msg="+msg);
    }
}