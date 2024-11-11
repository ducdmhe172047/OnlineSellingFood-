package dal;

import model.Certification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CertificateDAO extends DBContext {

    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Certification(
                rs.getInt("CertificationID"),
                rs.getInt("CertificateIssuerID"),
                rs.getString("Name"),
                rs.getString("Detail"),
                rs.getInt("ImgID")
        );
    }

    public List<Certification> getAllCertifications() {
        List<Certification> certifications = new ArrayList<>();
        String sql = "SELECT CertificationID, CertificateIssuerID, Name, Detail, ImgID FROM Certification";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Certification certification = new Certification(
                        rs.getInt("CertificateIssuerID"),
                        rs.getInt("CertificationID"),
                        rs.getString("Name"),
                        rs.getString("Detail"),
                        rs.getInt("ImgID")
                );
                certifications.add(certification);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return certifications;
    }

    public boolean deleteCertification(int certificationID) {
        String sql = "DELETE FROM Certification WHERE CertificationID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, certificationID);
            ResultSet rs = executeUpdate(ps);
            if(rs!=null) return rs.next();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        CertificateDAO dao = new CertificateDAO();
        dao.deleteCertification(18);
    }


    public boolean updateCertification(int certificationID, String name, String detail, int certificateIssuerID, int imgID) {
        String sql = "UPDATE Certification SET Name = ?, Detail = ?, CertificateIssuerID = ?, ImgID = ? WHERE CertificationID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, detail);
            ps.setInt(3, certificateIssuerID);
            ps.setInt(4, imgID);
            ps.setInt(5, certificationID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return false;
        }
    }
    public boolean isCertificationUsed(int certificationID) {
        String sql = "SELECT COUNT(*) FROM Product WHERE CertificationID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, certificationID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }


    public void addCertification(Certification certification) {
        String sql = "INSERT INTO Certification (Name, Detail, CertificateIssuerID, ImgID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, certification.getName());
            ps.setString(2, certification.getDetail());
            ps.setInt(3, certification.getCertificateIssuerID());
            ps.setInt(4, certification.getImgID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
    }


    public Certification getCertificationById(int certificationID) {
        String sql = "SELECT CertificateIssuerID,CertificationID,  Name, Detail, ImgID FROM Certification WHERE CertificationID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, certificationID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Certification(
                        rs.getInt("CertificateIssuerID"),
                        rs.getInt("CertificationID"),
                        rs.getString("Name"),
                        rs.getString("Detail"),
                        rs.getInt("ImgID")
                );
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    public List<Certification> searchCertifications(String keyword) {
        List<Certification> certifications = new ArrayList<>();
        String sql = "SELECT CertificationID, CertificateIssuerID, Name, Detail, ImgID FROM Certification WHERE Name LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Certification certification = new Certification(
                        rs.getInt("CertificationID"),
                        rs.getInt("CertificateIssuerID"),
                        rs.getString("Name"),
                        rs.getString("Detail"),
                        rs.getInt("ImgID")
                );
                certifications.add(certification);
            }
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }

        return certifications;
    }

    public boolean createCertification(String name, String detail, String imgID, int certificateIssuerID) {
        String sql = "INSERT INTO Certification (Name, Detail, ImgID, CertificateIssuerID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, detail);
            ps.setString(3, imgID);
            ps.setInt(4, certificateIssuerID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean createCertification1(String name, String detail, String certificateIssuerID, Integer imgID) {
        String sql = "INSERT INTO Certification (name, detail, certificateIssuerID, imgID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, detail);
            stmt.setString(3, certificateIssuerID);

            if (imgID != null) {
                stmt.setInt(4, imgID);
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Certification> getCertificationsByPage(int page, int pageSize) {
        List<Certification> certifications = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        String query = "SELECT CertificationID, CertificateIssuerID, Name, Detail, ImgID FROM Certification ORDER BY CertificationID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Certification certification = (Certification) getObjectByRs(rs);
                certifications.add(certification);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return certifications;
    }

    public int getTotalCertificationCount() {
        String query = "SELECT COUNT(*) FROM Certification";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return 0;
    }


}
