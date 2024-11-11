package dal;

import model.AccountStatus;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Category(rs.getInt("CategoryID"),rs.getString("CategoryName"));
    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT CategoryID, Name FROM Category";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String name = rs.getString("Name");

                Category category = new Category(categoryID, name);
                categories.add(category);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return categories;
    }
    public String getCategoryName(int categoryID) {
        String sql = "SELECT Name FROM Category WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Name");
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }
    public boolean isCategoryNameExist(String name) {
        String sql = "SELECT COUNT(*) FROM Category WHERE Name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }
    public boolean isCategoryInUse(int categoryID) {
        String sql = "SELECT COUNT(*) FROM Product WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }




    public void deleteCategory(int categoryID) {
        String sql = "DELETE FROM Category WHERE CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }
    public boolean updateCategory(String categoryID, String name) {
        String sql = "UPDATE Category SET Name = ? WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, categoryID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return false;
        }
    }

    public void addCategory(Category category) {
        String sql = "INSERT INTO Category (Name) VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }
    public Category getCategoryById(int categoryID) {
        String sql = "SELECT CategoryID, Name FROM Category WHERE CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("CategoryID"), rs.getString("Name"));
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }
    public boolean createCategory(String name) {
        String sql = "INSERT INTO Category (Name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return false;
        }
    }

        public List<Category> searchCategories(String keyword) {
            List<Category> categories = new ArrayList<>();
            String sql = "SELECT CategoryID, Name FROM Category WHERE Name LIKE ?";

            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("CategoryID");
                    String name = rs.getString("Name");

                    Category category = new Category(categoryID, name);
                    categories.add(category);
                }
            } catch (SQLException ex) {
                logger.info(ex.getMessage());
            }

            return categories;
        }
    public int getProductCountByCategory(int categoryID) {
        String sql = "SELECT COUNT(*) FROM Product WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Return the count of products
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return 0;
    }





}
