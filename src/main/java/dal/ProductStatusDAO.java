package dal;

import model.ProductStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ProductStatusDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new ProductStatus(rs.getInt(1),rs.getString(2));
    }

    public ProductStatus getProductStatusById(int id) {
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ProductStatus WHERE StatusID = ?");
            ps.setInt(1, id);
            return (ProductStatus) getObject(ps);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public List<ProductStatus> getAllProductStatus() {
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ProductStatus");
            return (List<ProductStatus>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Collections.emptyList();
    }
}
