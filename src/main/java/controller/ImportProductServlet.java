package controller;

import dal.*;
import dto.ImportProductResponse;
import dto.ImportRespone;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "ImportProductServlet", value = "/ImportProduct")
public class ImportProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ImportProductDAO daoP = new ImportProductDAO();
        int ID = Integer.parseInt(request.getParameter("id"));


        ProductDAO daop1 = new ProductDAO();
        List<Product> lp = daop1.getAllProductActivity();
        request.setAttribute("products", lp);

        UnitDAO daoup1 = new UnitDAO();
        List<Unit> lu = daoup1.getAllUnit();
        request.setAttribute("units", lu);
        List<ImportProductResponse> list = daoP.getAllImportProducts(ID);
        request.setAttribute("list", list);


        request.getRequestDispatcher("importProduct.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


            int importID = Integer.parseInt(request.getParameter("importId"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            String mfg = request.getParameter("mfg");
            String exp = request.getParameter("exp");


            int price = Integer.parseInt(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int inventory = Integer.parseInt(request.getParameter("inventory"));
            int unitId = Integer.parseInt(request.getParameter("unitID"));

            ImportProductDAO daop = new ImportProductDAO();


        if (daop.isProductImported(importID, productID)) {
                request.setAttribute("msg", "Product already exists in the import list.");
                response.sendRedirect("ImportProduct?id=" + importID);
                return; // Dừng thực hiện nếu sản phẩm đã được nhập
            }
            boolean isAdded = daop.addImportProduct(importID, productID, mfg, exp, price, quantity, inventory, unitId);
            response.sendRedirect("ImportProduct?id=" + importID);




        }
    }


