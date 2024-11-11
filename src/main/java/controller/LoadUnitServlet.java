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

@WebServlet(name = "LoadUnitServlet", urlPatterns = {"/unitlist"})
public class LoadUnitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Unit> units = new UnitDAO().getAllUnit();
        request.setAttribute("units",units);
        request.getRequestDispatcher("unit-list.jsp").forward(request,response);
    }
}