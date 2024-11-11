package dal;

import model.Certification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CertificationDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Certification(rs.getInt(5), rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));
    }

    public List<Certification> getAllCertification() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Certification");
            return (List<Certification>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Collections.emptyList();
    }
}
