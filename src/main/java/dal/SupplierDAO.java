package dal;

import model.Import;
import model.Supplier;
import model.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SupplierDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Supplier(rs.getInt("SupplierID"),rs.getInt("ContactInformationID"),rs.getString("Name"),rs.getString("Note"));
    }
    public List<Supplier> getAllSuppliers() {
        String sql = "SELECT * FROM Supplier";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            return (List<Supplier>) (Object) getListObject(stmt);
        }catch(SQLException e){
            logger.info(e.getMessage());
        }
        return Collections.emptyList();
    }

    // Thêm nhà cung cấp mới
    public Integer insertSupplier(Supplier supplier) {
        String sql = "INSERT INTO Supplier (Name, ContactInformationID, Note) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, supplier.getName());
            stmt.setInt(2, supplier.getContactInformationID());
            stmt.setString(3, supplier.getNote());
            ResultSet rs = executeUpdate(stmt);
            if(rs!=null && rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            logger.info(e.getMessage());
        }
        return null;
    }

    // Cập nhật thông tin nhà cung cấp
    public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE Supplier SET Name = ?, ContactInformationID = ?, Note = ? WHERE SupplierID = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setInt(2, supplier.getContactInformationID());
            stmt.setString(3, supplier.getNote());
            stmt.setInt(4, supplier.getSupplierID());
            return stmt.executeUpdate()>0;
        }catch(SQLException e){
            logger.info(e.getMessage());
        }
        return false;
    }

    // Xóa nhà cung cấp
    public boolean deleteSupplier(int supplierID) {
        String sql = "DELETE FROM Supplier WHERE SupplierID = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, supplierID);
            return stmt.executeUpdate()>0;
        }catch(SQLException e){
            logger.info(e.getMessage());
        }
        return false;
    }

    public List<Supplier> getAllSupplierActivity(){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from Supplier ");
            return (List<Supplier>) (Object) getListObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return Collections.emptyList();
    }

    public int getTotalSuppliers() {
        int totalSuppliers = 0;
        String query = "SELECT COUNT(*) AS totalSuppliers FROM Supplier";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalSuppliers = resultSet.getInt("totalSuppliers");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalSuppliers;
    }
}
