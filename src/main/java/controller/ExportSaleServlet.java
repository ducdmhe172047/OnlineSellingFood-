package controller;

import dal.OrderDAO;
import dal.OrderProductDAO;
import dal.ProductDAO;
import dto.OrderResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExportSaleServlet", value = "/ExportSale")
public class ExportSaleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrderDAO dao1 = new OrderDAO();


        List<OrderResponse> orderList = dao1.getAllOrders();

        boolean isExport = exportToExel(orderList);
        request.setAttribute("mess", isExport?"Export Success":"Export Failed");



        request.getRequestDispatcher("Dashboard").forward(request, response);

    }

    public boolean exportToExel(List<OrderResponse> orderList){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("chọn thư mục muốn lưu");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int userSelection = chooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File directionToSave = chooser.getSelectedFile();
        if(directionToSave == null){
            return false;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Báo cáo đơn hàng");
        String[] headerTitle={"Mã đơn hàng","Tên khách hàng","Ngày đặt hàng","Giá","Trạng thái đơn hàng","Phương thức thanh toán"};
        Row headerRow = sheet.createRow(0);
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        for (int i = 0; i < headerTitle.length; i++) {
            headerRow.createCell(i).setCellValue(headerTitle[i]);
        }
        int rowNum = 1;
        for (OrderResponse order : orderList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getOrderID());
            row.createCell(1).setCellValue(order.getCustomerName());
            Cell oderDateCell = row.createCell(2);
            oderDateCell.setCellValue(order.getOrderDate());
            oderDateCell.setCellStyle(dateCellStyle);
            row.createCell(3).setCellValue(order.getPrice());
            row.createCell(4).setCellValue(order.getOrderStatusName());
            row.createCell(5).setCellValue(order.getOrderPaymentName());
        }
        String fileName = "Báo cáo đơn hàng.xlsx";
        File exelFile = new File(directionToSave,fileName);
        try(FileOutputStream fileOut = new FileOutputStream(exelFile)){
            workbook.write(fileOut);
            System.out.println("Success");
            return true;
        }catch (IOException e){
            System.out.println(e);
            return false;
        }
    }
}
