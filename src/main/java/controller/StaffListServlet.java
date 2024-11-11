package controller;

import dal.AccountDAO;
import dal.StaffDAO;
import dto.StaffListResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StaffListServlet", urlPatterns = {"/staffList"})
public class StaffListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchName=request.getParameter("searchName");
        if(searchName==null)searchName="";

       String indexPage=request.getParameter("index");
        if(indexPage==null){
            indexPage="1";
        }

       int index=Integer.parseInt(indexPage);
        StaffDAO sd = new StaffDAO();
        List<StaffListResponse> sls=sd.getAllStaff(index,searchName);

        int count=sd.getTotalAccountStaff(searchName);
        int endPage=count/5;
        if(count%5!=0){
            endPage++;
        }

        request.setAttribute("staffList", sls);
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("searchName", searchName);
        request.getRequestDispatcher("staff-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}