package controller;

import dal.ImportProductDAO;
import dal.OrderDAO;
import dto.ImportProductResponse;
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
@WebServlet(name = "ExportImportServlet", value = "/ExportImport")
public class ExportImportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ImportProductDAO dao = new ImportProductDAO();


        List<ImportProductResponse> importList = dao.getAllImportProducts();

        boolean isExport = exportToExel(importList);
        request.setAttribute("mess", isExport?"Export Success":"Export Failed");



        request.getRequestDispatcher("DashboardI").forward(request, response);

    }



    public boolean exportToExel(List<ImportProductResponse> importList){
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
        Sheet sheet = workbook.createSheet("Báo cáo nhập hàng");
        String[] headerTitle={"Mã nhập","Tên sản phẩm","Ngày sản xuất","Ngày hết hạn","Giá","Số lượng nhập","Số lượng hàng tồn kho","Đơn vị"};
        Row headerRow = sheet.createRow(0);
        CellStyle dateCellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        for (int i = 0; i < headerTitle.length; i++) {
            headerRow.createCell(i).setCellValue(headerTitle[i]);
        }
        int rowNum = 1;
        for (ImportProductResponse imports : importList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(imports.getImportID());
            row.createCell(1).setCellValue(imports.getProductName());
            Cell manufactureDateCell = row.createCell(2);
            manufactureDateCell.setCellValue(imports.getManufactureDate());
            manufactureDateCell.setCellStyle(dateCellStyle);
            Cell expireDateCell = row.createCell(3);
            expireDateCell.setCellValue(imports.getExpireDate());
            expireDateCell.setCellStyle(dateCellStyle);
            row.createCell(4).setCellValue(imports.getPrice());
            row.createCell(5).setCellValue(imports.getImportQuantity());
            row.createCell(6).setCellValue(imports.getInventoryQuantity());
            row.createCell(7).setCellValue(imports.getUnitName());
        }
        String fileName = "Báo cáo nhập hàng.xlsx";
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
