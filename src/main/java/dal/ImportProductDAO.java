package dal;


import dto.ImportProductResponse;
import dto.ImportRespone;
import model.ImportProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ImportProductDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new ImportProduct();
    }

    public int countProductQuantity (int productId) {
        int count = 0;
        String query ="SELECT ProductID, SUM(InventoryQuantity)\n" +
                "FROM ImportProduct ip WHERE ip.Exp > GETDATE() and ProductID=?\n" +
                "GROUP BY ProductID";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(2);
            }
        } catch (SQLException e){
            logger.info(e.getMessage());
        }
        return count;
    }

    public static void main(String[] args) {
        ImportProductDAO dao = new ImportProductDAO();
        System.out.println(dao.countProductQuantity(1));
    }

    public List<ImportProductResponse> getAllImportProducts(int importID) {
        List<ImportProductResponse> importProducts = new ArrayList<>();
        String query = "SELECT ip.ImportID, p.Name, ip.Mfg, ip.Exp, ip.Price, ip.ImportQuantity, "
                + "ip.InventoryQuantity, u.Name as UnitName "
                + "FROM ImportProduct ip "
                + "JOIN Product p ON ip.ProductID = p.ProductID "
                + "JOIN Unit u ON ip.UnitID = u.UnitID "
                + "WHERE ip.ImportID = " + importID;

        try (
                PreparedStatement stmt = connection.prepareStatement(query);

                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                importProducts.add(new ImportProductResponse(rs.getInt(1)
                        , rs.getString(2)
                        , rs.getTimestamp(3).toLocalDateTime()
                        , rs.getTimestamp(4).toLocalDateTime()
                        , rs.getInt(5)
                        , rs.getInt(6)
                        , rs.getInt(7)
                        , rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return importProducts;
    }
    public List<ImportProductResponse> getAllImportProducts() {
        List<ImportProductResponse> importProducts = new ArrayList<>();
        String query = "SELECT ip.ImportID, p.Name, ip.Mfg, ip.Exp, ip.Price, ip.ImportQuantity, "
                + "ip.InventoryQuantity, u.Name as UnitName "
                + "FROM ImportProduct ip "
                + "JOIN Product p ON ip.ProductID = p.ProductID "
                + "JOIN Unit u ON ip.UnitID = u.UnitID ";

        try (
                PreparedStatement stmt = connection.prepareStatement(query);

                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                importProducts.add(new ImportProductResponse(rs.getInt(1)
                        , rs.getString(2)
                        , rs.getTimestamp(3).toLocalDateTime()
                        , rs.getTimestamp(4).toLocalDateTime()
                        , rs.getInt(5)
                        , rs.getInt(6)
                        , rs.getInt(7)
                        , rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return importProducts;
    }
    public int getTotalImportProductWithSearch(String search) {
        String query = "SELECT COUNT(*) FROM ImportProduct ip "
                + "JOIN Product p ON ip.ProductID = p.ProductID "
                + "JOIN Unit u ON ip.UnitID = u.UnitID ";
        if (search != null && !search.trim().isEmpty()) {
            query += "WHERE p.Name LIKE ?";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(1, "%" + search + "%");
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Total count
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ImportProductResponse> getAllImportProductsd(int index,String search) {
        List<ImportProductResponse> importProducts = new ArrayList<>();
        if (index ==1 ){index = 0;}
        if(index != 0){
            index = (index-1)*5;}
        String query = "SELECT ip.ImportID, p.Name, ip.Mfg, ip.Exp, ip.Price, ip.ImportQuantity, "
                + "ip.InventoryQuantity, u.Name as UnitName "
                + "FROM ImportProduct ip "
                + "JOIN Product p ON ip.ProductID = p.ProductID "
                + "JOIN Unit u ON ip.UnitID = u.UnitID ";

        if (search != null && !search.trim().isEmpty()) {
            query += "WHERE p.Name LIKE ? ";
        }
        query += "order by ip.ImportID offset ? rows fetch next 5 rows only";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            int paramIndex = 1;
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(paramIndex++, "%" + search + "%");
            }


            statement.setInt(paramIndex, index);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    importProducts.add(new ImportProductResponse(rs.getInt(1)
                            , rs.getString(2)
                            , rs.getTimestamp(3).toLocalDateTime()
                            , rs.getTimestamp(4).toLocalDateTime()
                            , rs.getInt(5)
                            , rs.getInt(6)
                            , rs.getInt(7)
                            , rs.getString(7)));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return importProducts;
    }

    public int getTotalImports() {
        int totalImports = 0;
        String query = "SELECT COUNT(*) AS TotalImports FROM ImportProduct ip "
                + "JOIN Product p ON ip.ProductID = p.ProductID "
                + "JOIN Unit u ON ip.UnitID = u.UnitID";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalImports = resultSet.getInt("TotalImports");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalImports;
    }

    public boolean isProductImported(int importID, int productID) {
        String sql = "SELECT COUNT(*) FROM ImportProduct WHERE ImportID = ? AND ProductID = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, importID);
            stmt.setInt(2, productID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu đếm lớn hơn 0, sản phẩm đã được nhập
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Nếu không tìm thấy sản phẩm đã nhập
    }

    public boolean addImportProduct(int ImportID, int ProductID, String mfg, String exp, int price, int importQuantity, int inventoryQuantity, int UnitID) {
        String sql = "INSERT INTO ImportProduct (ImportID, ProductID, Mfg, Exp, Price, ImportQuantity, InventoryQuantity, UnitID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ImportID);
            stmt.setInt(2, ProductID);
            stmt.setString(3, mfg);
            stmt.setString(4, exp);
            stmt.setInt(5, price);
            stmt.setInt(6, importQuantity);
            stmt.setInt(7, inventoryQuantity);
            stmt.setInt(8, UnitID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có bản ghi được thêm

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean deleteImportProduct(int importID) {
        String sql = "DELETE FROM ImportProduct WHERE ImportID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, importID);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }

    }
    
    public void updateInventoryQuantity(int productID){


    }
}



