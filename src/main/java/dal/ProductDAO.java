package dal;

import common.InsertPrepareStatement;
import model.Product;
import model.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class ProductDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("productID"),rs.getInt("price"),rs.getInt("discountID"),rs.getInt("weight"),rs.getInt("categoryID"),rs.getInt("manufacturerID"),rs.getInt("originID"),rs.getInt("unitID"),rs.getInt("certificationID"),rs.getInt("statusID"),rs.getString("name"),rs.getString("detail"));
    }

    public Product getProductByID(int productID){
        try{
            PreparedStatement ps = connection.prepareStatement("select ProductID,Price,DiscountID,Weight,CategoryID,ManufacturerID,OriginID,UnitID,CertificationID,StatusID,Name,Detail from Product where ProductID=?");
            ps.setInt(1, productID);
            return (Product)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public List<Product> getAllProduct(){
        try{
            PreparedStatement ps = connection.prepareStatement("select ProductID,Price,DiscountID,Weight,CategoryID,ManufacturerID,OriginID,UnitID,CertificationID,StatusID,Name,Detail from Product");
            return (List<Product>) (Object) getListObject(ps);
        }catch(SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return Collections.emptyList();
    }

    public boolean deleteProduct(int prodcutID) {
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, prodcutID);
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET Price = ?, Weight =?, CategoryID =?, ManufacturerID =?, OriginID =?, UnitID =?, CertificationID =?, StatusID =?, Name =?, Detail =? WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1,product.getPrice());
            ps.setInt(2,product.getWeight());
            ps.setInt(3,product.getCategoryID());
            ps.setInt(4,product.getManufacturerID());
            ps.setInt(5,product.getOriginID());
            ps.setInt(6,product.getUnitID());
            ps.setInt(7,product.getCertificationID());
            ps.setInt(8,product.getStatusID());
            ps.setNString(9,product.getName());
            ps.setNString(10,product.getDetail());
            ps.setInt(11,product.getProductID());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }

    public Integer addProduct(Product product) {
        String sql = "INSERT INTO Product (Price,DiscountID,Weight,CategoryID,ManufacturerID,OriginID,UnitID,CertificationID,StatusID,Name,Detail) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, product.getPrice());
            InsertPrepareStatement.insertInteger(product.getDiscountID(),ps,2);
            ps.setInt(3,product.getWeight());
            ps.setInt(4,product.getCategoryID());
            ps.setInt(5,product.getManufacturerID());
            ps.setInt(6,product.getOriginID());
            ps.setInt(7,product.getUnitID());
            ps.setInt(8,product.getCertificationID());
            ps.setInt(9,product.getStatusID());
            ps.setNString(10,product.getName());
            ps.setNString(11,product.getDetail());
            ResultSet rs = executeUpdate(ps);
            if(rs!=null && rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    public void deleteDiscount(int productID){
        String sql="UPDATE [dbo].[Product]\n" +
                "   SET \n" +
                "      [DiscountID] =NULL\n" +
                "      \n" +
                " WHERE ProductID=?";

        try{
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Product> getAllProductActivity(){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from Product ");
            return (List<Product>) (Object) getListObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return Collections.emptyList();
    }
    public int getTotalProducts() {
        int totalProducts = 0;
        try {
            // Establish connection


            // SQL query to count the total number of products
            String query = "SELECT COUNT(*) AS total FROM dbo.Product";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Retrieve total product count
            if (resultSet.next()) {
                totalProducts = resultSet.getInt("total");
            }

            // Close resources
            resultSet.close();
            statement.close();
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProducts;
    }
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getInt("Price"),
                        rs.getInt("DiscountID"),
                        rs.getInt("Weight"),
                        rs.getInt("CategoryID"),
                        rs.getInt("ManufacturerID"),
                        rs.getInt("OriginID"),
                        rs.getInt("UnitID"),
                        rs.getInt("CertificationID"),
                        rs.getInt("StatusID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM Product WHERE ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                            rs.getInt("ProductID"),
                            rs.getInt("Price"),
                            rs.getInt("DiscountID"),
                            rs.getInt("Weight"),
                            rs.getInt("CategoryID"),
                            rs.getInt("ManufacturerID"),
                            rs.getInt("OriginID"),
                            rs.getInt("UnitID"),
                            rs.getInt("CertificationID"),
                            rs.getInt("StatusID"),
                            rs.getString("Name"),
                            rs.getString("Detail")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
    public List<String> getProductImages(int productId) {
        List<String> images = new ArrayList<>();
        String sql = "SELECT i.ImgLink FROM ProductImg pi " +
                "JOIN Img i ON pi.ImgID = i.ImgID " +
                "WHERE pi.ProductID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    images.add(rs.getString("ImgLink"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return images;
    }
    public int countProductsByCategory(int categoryID) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Product";

        // Nếu categoryID khác 0, thêm điều kiện vào truy vấn
        if (categoryID != 0) {
            sql += " WHERE CategoryID = ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (categoryID != 0) {
                ps.setInt(1, categoryID);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    // In ProductDAO class
    // In ProductDAO class
    public List<Product> getProductsByPageAndSort(int categoryID, int page, int pageSize, String sortOption, boolean ascending, String searchTerm) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        // Add WHERE conditions based on category and search term
        if (categoryID != 0) {
            sql += " WHERE CategoryID = ?";
        }
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql += (categoryID != 0 ? " AND" : " WHERE") + " Name LIKE ?";
        }

        // Determine the sort column and direction based on sortOption
        String sortColumn = "Name";
        if (sortOption.equals("priceLowToHigh") || sortOption.equals("priceHighToLow")) {
            sortColumn = "Price";
        } else if (sortOption.equals("nameAscending") || sortOption.equals("nameDescending")) {
            sortColumn = "Name";
        }

        // Add sorting to the query
        sql += " ORDER BY " + sortColumn + (ascending ? " ASC" : " DESC");

        // Add pagination
        sql += " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int paramIndex = 1;

            // Set parameters
            if (categoryID != 0) {
                ps.setInt(paramIndex++, categoryID);
            }
            if (searchTerm != null && !searchTerm.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchTerm + "%");
            }
            ps.setInt(paramIndex++, (page - 1) * pageSize);  // OFFSET
            ps.setInt(paramIndex++, pageSize);               // FETCH NEXT

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("ProductID"),
                            rs.getInt("Price"),
                            rs.getInt("DiscountID"),
                            rs.getInt("Weight"),
                            rs.getInt("CategoryID"),
                            rs.getInt("ManufacturerID"),
                            rs.getInt("OriginID"),
                            rs.getInt("UnitID"),
                            rs.getInt("CertificationID"),
                            rs.getInt("StatusID"),
                            rs.getString("Name"),
                            rs.getString("Detail")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public Map<Integer, Integer> countProductsByOrigin() {
        Map<Integer, Integer> originCounts = new HashMap<>();
        String sql = "SELECT OriginID, COUNT(*) AS Count FROM Product GROUP BY OriginID";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int originID = rs.getInt("OriginID");
                int count = rs.getInt("Count");
                originCounts.put(originID, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return originCounts;
    }
    public Map<Integer, Integer> countProductsByCategory() {
        Map<Integer, Integer> categoryCounts = new HashMap<>();
        String sql = "SELECT CategoryID, COUNT(*) AS Count FROM Product GROUP BY CategoryID";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                int count = rs.getInt("Count");
                categoryCounts.put(categoryID, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryCounts;
    }

    public int countProductsByCategoryAndSearch(int categoryID, String searchTerm) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Product";

        // Nếu categoryID khác 0, thêm điều kiện vào truy vấn
        if (categoryID != 0) {
            sql += " WHERE CategoryID = ?";
        }

        // Thêm điều kiện tìm kiếm theo tên
        if (searchTerm != null && !searchTerm.isEmpty()) {
            sql += (categoryID != 0 ? " AND" : " WHERE") + " Name LIKE ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            if (categoryID != 0) {
                ps.setInt(paramIndex++, categoryID);
            }
            if (searchTerm != null && !searchTerm.isEmpty()) {
                ps.setString(paramIndex++, "%" + searchTerm + "%"); // Tìm kiếm với wildcard
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public List<Product> get5RelatedProductsByManufacturer(int manufacturerID) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP 5 * FROM Product WHERE ManufacturerID = ? ORDER BY ProductID DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, manufacturerID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("ProductID"),
                            rs.getInt("Price"),
                            rs.getInt("DiscountID"),
                            rs.getInt("Weight"),
                            rs.getInt("CategoryID"),
                            rs.getInt("ManufacturerID"),
                            rs.getInt("OriginID"),
                            rs.getInt("UnitID"),
                            rs.getInt("CertificationID"),
                            rs.getInt("StatusID"),
                            rs.getString("Name"),
                            rs.getString("Detail")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public List<Product> get5ProductByDiscount() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP 5 * FROM Product WHERE DiscountID IS NOT NULL ORDER BY DiscountID DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getInt("Price"),
                        rs.getInt("DiscountID"),
                        rs.getInt("Weight"),
                        rs.getInt("CategoryID"),
                        rs.getInt("ManufacturerID"),
                        rs.getInt("OriginID"),
                        rs.getInt("UnitID"),
                        rs.getInt("CertificationID"),
                        rs.getInt("StatusID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public List<Product> get4ProductByDiscount() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP 4 * FROM Product WHERE DiscountID IS NOT NULL ORDER BY DiscountID ASC";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getInt("Price"),
                        rs.getInt("DiscountID"),
                        rs.getInt("Weight"),
                        rs.getInt("CategoryID"),
                        rs.getInt("ManufacturerID"),
                        rs.getInt("OriginID"),
                        rs.getInt("UnitID"),
                        rs.getInt("CertificationID"),
                        rs.getInt("StatusID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public List<Product> getProductsByManufacturerID(int manufacturerID) {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Product WHERE ManufacturerID = ?"
            );
            ps.setInt(1, manufacturerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add((Product) getObjectByRs(rs));
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return products;
    }

    public int getTotalProduct(String searchName){
        String sql = "select count(*) from Product where  Name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + searchName + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
