package dal;

import model.Manufacturer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacterDAO extends DBContext {

    // Converts a ResultSet row into a Manufacturer object
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Manufacturer(
                rs.getInt("ManufacturerID"),
                rs.getString("Introduce"),
                rs.getString("Name"),
                rs.getInt("ProductCount"),
                rs.getBoolean("active")
        );
    }

    // Retrieves a Manufacturer by ID
    public Manufacturer getManufacturerByID(int manufacturerID) {
        String query = """
            SELECT m.ManufacturerID, m.Introduce, m.Name, COUNT(p.ProductID) AS ProductCount, m.active
            FROM Manufacturer m
            LEFT JOIN Product p ON m.ManufacturerID = p.ManufacturerID
            WHERE m.ManufacturerID = ?
            GROUP BY m.ManufacturerID, m.Introduce, m.Name, m.active
        """;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, manufacturerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setManufacturerID(rs.getInt("ManufacturerID"));
                manufacturer.setIntroduce(rs.getString("Introduce"));
                manufacturer.setName(rs.getString("Name"));
                manufacturer.setProductCount(rs.getInt("ProductCount"));
                manufacturer.setActive(rs.getBoolean("active"));
                return manufacturer;
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    // Adds a new Manufacturer and returns the generated ID
    public Integer addManufacturer(Manufacturer manufacturer) {
        String query = "INSERT INTO Manufacturer (Introduce, Name, active) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, manufacturer.getIntroduce());
            ps.setString(2, manufacturer.getName());
            ps.setBoolean(3, manufacturer.isActive());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    // Updates a Manufacturer and checks for name uniqueness
    public boolean updateManufacturer(Manufacturer manufacturer) {
        try {
            Manufacturer existingManufacturer = getManufacturerByID(manufacturer.getManufacturerID());
            if (!existingManufacturer.getName().equals(manufacturer.getName()) &&
                    isManufacturerNameExists(manufacturer.getName())) {
                return false;
            }
            String query = "UPDATE Manufacturer SET Introduce = ?, Name = ?, active = ? WHERE ManufacturerID = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, manufacturer.getIntroduce());
                ps.setString(2, manufacturer.getName());
                ps.setBoolean(3, manufacturer.isActive());
                ps.setInt(4, manufacturer.getManufacturerID());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    // Retrieves all manufacturers with their product counts
    public List<Manufacturer> getAllManufacturers() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = """
            SELECT m.ManufacturerID, m.Introduce, m.Name, COUNT(p.ProductID) AS ProductCount, m.active
            FROM Manufacturer m
            LEFT JOIN Product p ON m.ManufacturerID = p.ManufacturerID
            GROUP BY m.ManufacturerID, m.Introduce, m.Name, m.active
        """;
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Manufacturer manufacturer = new Manufacturer(
                        rs.getInt("ManufacturerID"),
                        rs.getString("Name"),
                        rs.getString("Introduce"),
                        rs.getInt("ProductCount"),
                        rs.getBoolean("active")
                );
                manufacturers.add(manufacturer);
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return manufacturers;
    }

    // Checks if a manufacturer name already exists
    public boolean isManufacturerNameExists(String name) {
        String query = "SELECT ManufacturerID FROM Manufacturer WHERE Name = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    // Deletes a manufacturer by ID
    public boolean deleteManufacturer(int manufacturerID) {
        String query = "DELETE FROM Manufacturer WHERE ManufacturerID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, manufacturerID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    // Searches manufacturers by name
    public List<Manufacturer> searchManufacturersByName(String name) {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = """
            SELECT m.ManufacturerID, m.Introduce, m.Name, COUNT(p.ProductID) AS ProductCount, m.active
            FROM Manufacturer m
            LEFT JOIN Product p ON m.ManufacturerID = p.ManufacturerID
            WHERE m.Name LIKE ?
            GROUP BY m.ManufacturerID, m.Introduce, m.Name, m.active
        """;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + name.trim() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Manufacturer manufacturer = new Manufacturer(
                        rs.getInt("ManufacturerID"),
                        rs.getString("Name"),
                        rs.getString("Introduce"),
                        rs.getInt("ProductCount"),
                        rs.getBoolean("active")
                );
                manufacturers.add(manufacturer);
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return manufacturers;
    }

    // Text truncator utility class
    public static class TextTruncator {
        private static final int MAX_LENGTH = 40;

        public static String truncate(String text) {
            if (text == null) return "";
            if (text.length() <= MAX_LENGTH) return text;
            int lastSpace = text.substring(0, MAX_LENGTH).lastIndexOf(' ');
            int truncateIndex = (lastSpace > 0) ? lastSpace : MAX_LENGTH;
            return text.substring(0, truncateIndex) + "...";
        }
    }
    public int getProductCountByManufacturerName(String name) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Product p " +
                "JOIN Manufacturer m ON p.ManufacturerID = m.ManufacturerID " +
                "WHERE m.Name = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public String getManufacturerName(int manufacturerID) {
        String sql = "SELECT Name FROM Manufacturer WHERE ManufacturerID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, manufacturerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Name");
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

}
