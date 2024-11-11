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

@WebServlet(name = "CreateUnitServlet", urlPatterns = {"/createunit"})
public class CreateUnitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("unitname");
        int baseUnitId = Integer.parseInt(request.getParameter("baseunitid"));
        int conversionRate = Integer.parseInt(request.getParameter("conversionrate"));
        String msg = "Tạo đơn vị thất bại";
        if(new UnitDAO().addUnit(name, baseUnitId, conversionRate)!=null){
            msg = "Tạo đơn vị thành công";
        }
        response.sendRedirect("unitlist?msg="+msg);
    }
}