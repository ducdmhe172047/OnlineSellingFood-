package dal;

import model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO extends  DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Role(rs.getInt("RoleID"),rs.getString("Name"));
    }

    public Role getRole(Integer roleID){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from Role where RoleID=?");
            ps.setInt(1,roleID);
            return (Role)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }
}
