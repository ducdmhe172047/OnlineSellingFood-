package dal;

import model.AccountStatus;
import model.Origin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OriginDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Origin(rs.getInt("OriginID"),rs.getString("OriginName"));
    }
    public List<Origin> getAllOrigins() {
        List<Origin> origins = new ArrayList<>();
        String sql = "SELECT OriginID, Name FROM Origin";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int originID = rs.getInt("OriginID");
                String name = rs.getString("Name");

                Origin origin = new Origin(originID, name);
                origins.add(origin);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return origins;
    }

    public void deleteOrigin(int originID) {
        String sql = "DELETE FROM Origin WHERE OriginID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, originID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }

    public boolean updateOrigin(int originID, String name) {
        String sql = "UPDATE Origin SET Name = ? WHERE OriginID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setNString(1, name);
            ps.setInt(2, originID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return false;
        }
    }

    public void addOrigin(String name) {
        String sql = "INSERT INTO Origin (Name) VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }

    public Origin getOriginById(int originID) {
        String sql = "SELECT OriginID, Name FROM Origin WHERE OriginID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, originID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Origin(rs.getInt("OriginID"), rs.getString("Name"));
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    public List<Origin> searchOrigins(String keyword) {
        List<Origin> origins = new ArrayList<>();
        String sql = "SELECT OriginID, Name FROM Origin WHERE Name LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int originID = rs.getInt("OriginID");
                String name = rs.getString("Name");

                Origin origin = new Origin(originID, name);
                origins.add(origin);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return origins;
    }
    public List<Origin> getOriginsWithPagination(int start, int limit) {
        List<Origin> origins = new ArrayList<>();
        String sql = "SELECT OriginID, Name FROM Origin ORDER BY OriginID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int originID = rs.getInt("OriginID");
                String name = rs.getString("Name");

                Origin origin = new Origin(originID, name);
                origins.add(origin);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return origins;
    }

    public int getTotalOriginCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM Origin";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return count;
    }

}
