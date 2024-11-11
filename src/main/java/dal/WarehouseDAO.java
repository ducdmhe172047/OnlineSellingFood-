package dal;

import model.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarehouseDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Warehouse(rs.getInt("WarehouseID"), rs.getInt("ContactInformationID"), rs.getInt("StatusID"), rs.getString("Name"));
    }

    public Warehouse getWarehouse(int warehouseID) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from Warehouse where WarehouseID=?");
            ps.setInt(1, warehouseID);
            return (Warehouse) getObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }
    public boolean isWarehouseExists(String name) {
        String sql = "SELECT COUNT(*) FROM Warehouse WHERE Name = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
    public int countWarehouseBySearch(String search) {
        String sql = "SELECT COUNT(*) FROM Warehouse WHERE Name LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return 0;
    }
    public List<Warehouse> searchWarehouses(String search, int start, int recordsPerPage) {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM Warehouse WHERE Name LIKE ? ORDER BY WarehouseID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + search + "%");
            ps.setInt(2, start);
            ps.setInt(3, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int warehouseID = rs.getInt("WarehouseID");
                int contactInformationID = rs.getInt("ContactInformationID");
                int statusID = rs.getInt("StatusID");
                String name = rs.getString("Name");

                Warehouse warehouse = new Warehouse(warehouseID, contactInformationID, statusID, name);
                warehouses.add(warehouse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouses;
    }

    public List<Warehouse> getAllWarehouse() {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from Warehouse");
            return (List<Warehouse>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Warehouse> getAllWarehouseActivity() {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from Warehouse where StatusID=1");
            return (List<Warehouse>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Warehouse> getAllWarehouseClose() {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from Warehouse where StatusID=2");
            return (List<Warehouse>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public boolean updateWarehouse(Warehouse warehouse) {
        try {
            PreparedStatement ps = connection.prepareStatement("update Warehouse set ContactInformationID=?, StatusID=?, Name=? where WarehouseID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, warehouse.getContactInformationID());
            ps.setInt(2, warehouse.getStatusID());
            ps.setNString(3, warehouse.getName());
            ps.setInt(4, warehouse.getWarehouseID());
            ResultSet rs = executeUpdate(ps);
            if (rs != null)
                return rs.next();
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public Integer addWarehouse(Warehouse warehouse) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert Warehouse (ContactInformationID,StatusID,Name) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, warehouse.getContactInformationID());
            ps.setInt(2, warehouse.getStatusID());
            ps.setNString(3, warehouse.getName());
            ResultSet rs = executeUpdate(ps);
            if (rs != null && rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public List<Warehouse> searchWarehouses(String keyword) {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT WarehouseID, Name, ContactInformationID, StatusID FROM Warehouse WHERE Name LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int warehouseID = rs.getInt("WarehouseID");
                String name = rs.getString("Name");
                int contactInformationID = rs.getInt("ContactInformationID");
                int statusID = rs.getInt("StatusID");
                Warehouse warehouse = new Warehouse(warehouseID, contactInformationID, statusID, name);
                warehouses.add(warehouse);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return warehouses;
    }

    public List<Warehouse> getAllWarehouses() throws SQLException {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM Warehouse"; // Thay đổi tên bảng nếu cần

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouseID(rs.getInt("WarehouseID")); // Thay đổi tên cột nếu cần
                warehouse.setName(rs.getString("Name")); // Thay đổi tên cột nếu cần
                warehouse.setContactInformationID(rs.getInt("ContactInformationID")); // Thay đổi tên cột nếu cần
                warehouse.setStatusID(rs.getInt("StatusID")); // Thay đổi tên cột nếu cần
                warehouses.add(warehouse);
            }
        }
        return warehouses;
    }

    public List<Warehouse> getAllWareHouseActive() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM Warehouse WHERE StatusID=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWarehouseID(rs.getInt("WarehouseID"));
                warehouse.setName(rs.getString("Name"));
                warehouse.setContactInformationID(rs.getInt("ContactInformationID"));
                warehouse.setStatusID(rs.getInt("StatusID"));
                warehouses.add(warehouse);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return warehouses;
    }

    public int getTotalWarehouses() {
        int totalWarehouses = 0;
        String query = "SELECT COUNT(*) AS totalWarehouses FROM Warehouse";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalWarehouses = resultSet.getInt("totalWarehouses");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalWarehouses;
    }

}
