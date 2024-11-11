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

@WebServlet(name = "UpdateUnitServlet", urlPatterns = {"/updateunit"})
public class UpdateUnitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int baseUnitId = Integer.parseInt(request.getParameter("baseunitid"));
        int conversionRate = Integer.parseInt(request.getParameter("conversionrate"));
        String msg = "Cập nhật thất bại";
        if(new UnitDAO().addUnit(name, baseUnitId, conversionRate)!=null){
            msg = "Cập nhật thành công";
        }
        response.sendRedirect("unitlist?msg="+msg);
    }
}