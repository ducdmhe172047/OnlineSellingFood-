package dal;


import dto.ImportRespone;
import dto.ProductDiscountResponse;
import model.Import;
import model.Staff;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ImportDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Import(rs.getInt("inportID"),rs.getInt("staffID"),rs.getInt("warehouseID"),rs.getInt("supplierID"),rs.getTimestamp("time").toLocalDateTime());
    }


    public List<ImportRespone> getImportList(int index) {
        List<ImportRespone> importList = new Vector<ImportRespone>();
        if (index ==1 ){index = 0;}
        if(index != 0){
            index = (index-1)*5;}
        String sql = "SELECT i.ImportID, a.Name AS AccountName, w.Name AS WarehouseName, " +
                "s.Name AS SupplierName, i.Time " +
                "FROM Import i " +
                "JOIN Staff st ON i.StaffID = st.StaffID " +
                "JOIN Account a ON st.AccountID = a.AccountID " +
                "JOIN Warehouse w ON i.WarehouseID = w.WarehouseID " +
                "JOIN Supplier s ON i.SupplierID = s.SupplierID "+
                "order by i.ImportID offset " + index +" rows fetch next 5 rows only";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                importList.add(new ImportRespone(rs.getInt(1)
                        ,rs.getString(2)
                        ,rs.getString(3)
                        ,rs.getString(4)
                        , rs.getTimestamp(5).toLocalDateTime()));
            }
            return importList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addImport(int staffID, int warehouseID, int supplierID, String time) {
        String query = "INSERT INTO Import (StaffID, WarehouseID, SupplierID, Time) VALUES (?, ?, ?, ?)";
        try (
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, staffID);
            ps.setInt(2, warehouseID);
            ps.setInt(3, supplierID);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.parse(time,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có bản ghi được thêm
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi để biết nguyên nhân
        }
        return false; // Trả về false nếu có lỗi
    }


    public Import getImportID(int importID){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from Import where ImportID=?");
            ps.setInt(1, importID);
            return (Import)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public boolean deleteImport(int importID) {
        // First delete from the ImportProduct table
        String deleteProductsSql = "DELETE FROM ImportProduct WHERE ImportID = ?";
        String deleteImportSql = "DELETE FROM Import WHERE ImportID = ?";

        try {
            connection.setAutoCommit(false); // Start transaction

            try (PreparedStatement productStmt = connection.prepareStatement(deleteProductsSql)) {
                productStmt.setInt(1, importID);
                productStmt.executeUpdate();
            }

            try (PreparedStatement importStmt = connection.prepareStatement(deleteImportSql)) {
                importStmt.setInt(1, importID);
                int rowsAffected = importStmt.executeUpdate();
                connection.commit(); // Commit transaction if both deletions are successful
                return rowsAffected > 0; // Return true if the import was deleted
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Rollback transaction if there's an error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false; // Return false if an error occurred
        } finally {
            try {
                connection.setAutoCommit(true); // Reset auto-commit mode
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public int getTotalImport() {
        String sql = "SELECT COUNT(*) " +
                "FROM Import i " +
                "JOIN Staff st ON i.StaffID = st.StaffID " +
                "JOIN Account a ON st.AccountID = a.AccountID " +
                "JOIN Warehouse w ON i.WarehouseID = w.WarehouseID " +
                "JOIN Supplier s ON i.SupplierID = s.SupplierID " ;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {


            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về tổng số bản ghi
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi
    }

}
