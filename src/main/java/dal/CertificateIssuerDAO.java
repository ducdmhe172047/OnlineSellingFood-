package dal;

import model.CertificateIssuer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CertificateIssuerDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new CertificateIssuer(
                rs.getInt("CertificateIssuerID"),rs.getString("Name"), rs.getString("Detail")
        );
    }

    public List<CertificateIssuer> getAllCertificateIssuers() {
        List<CertificateIssuer> issuers = new ArrayList<>();
        String sql = "SELECT CertificateIssuerID, Name, Detail FROM CertificateIssuer";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CertificateIssuer issuer = new CertificateIssuer(
                        rs.getInt("CertificateIssuerID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
                issuers.add(issuer);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return issuers;
    }

    public void deleteCertificateIssuer(int certificateIssuerID) {
        String sql = "DELETE FROM CertificateIssuer WHERE CertificateIssuerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, certificateIssuerID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }

    public boolean updateCertificateIssuer(int certificateIssuerID, String name, String detail) {
        String sql = "UPDATE CertificateIssuer SET Name = ?, Detail = ? WHERE CertificateIssuerID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, detail);
            ps.setInt(3, certificateIssuerID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return false;
        }
    }
    public List<CertificateIssuer> getCertificateIssuersWithPagination(int start, int limit) {
        List<CertificateIssuer> issuers = new ArrayList<>();
        String sql = "SELECT CertificateIssuerID, Name, Detail FROM CertificateIssuer ORDER BY CertificateIssuerID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CertificateIssuer issuer = new CertificateIssuer(
                        rs.getInt("CertificateIssuerID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
                issuers.add(issuer);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return issuers;
    }
    public boolean isNameExists(String name) {
        String sql = "SELECT COUNT(*) FROM CertificateIssuer WHERE Name = ?";
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
    public boolean isCertificateIssuerUsed(int certificateIssuerID) {
        String sql = "SELECT COUNT(*) FROM Certification WHERE CertificateIssuerID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, certificateIssuerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }



    public int getTotalCertificateIssuerCount() {
        String sql = "SELECT COUNT(*) AS Total FROM CertificateIssuer";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return 0;
    }


    public void addCertificateIssuer(CertificateIssuer issuer) {
        String sql = "INSERT INTO CertificateIssuer (Name, Detail) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, issuer.getName());
            ps.setString(2, issuer.getDetail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }

    public CertificateIssuer getCertificateIssuerById(int certificateIssuerID) {
        String sql = "SELECT CertificateIssuerID, Name, Detail FROM CertificateIssuer WHERE CertificateIssuerID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, certificateIssuerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CertificateIssuer(
                        rs.getInt("CertificateIssuerID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    public List<CertificateIssuer> searchCertificateIssuers(String keyword) {
        List<CertificateIssuer> issuers = new ArrayList<>();
        String sql = "SELECT CertificateIssuerID, Name, Detail FROM CertificateIssuer WHERE Name LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CertificateIssuer issuer = new CertificateIssuer(
                        rs.getInt("CertificateIssuerID"),
                        rs.getString("Name"),
                        rs.getString("Detail")
                );
                issuers.add(issuer);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return issuers;
    }
    public boolean createCertificateIssuer(String name, String detail) {
        String sql = "INSERT INTO CertificateIssuer (Name, Detail) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, detail);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return false;
        }
    }

}
