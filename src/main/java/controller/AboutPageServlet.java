package controller;

import dal.AccountContactDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;

@WebServlet(name = "AboutPageServlet", value = "/AboutPage")
public class AboutPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("AboutPage.jsp").forward(request,response);
    }
}
