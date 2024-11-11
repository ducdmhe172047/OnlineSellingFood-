package dal;

import model.Warehouse;
import model.WarehouseStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarehouseStatusDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new WarehouseStatus(rs.getInt("StatusID"),rs.getString("Detail"));
    }
    public List<WarehouseStatus> getAllWarehouseStatuses() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM WarehouseStatus");

            return (List<WarehouseStatus>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return Collections.emptyList();
    }


    public Integer addStatus(WarehouseStatus status){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into WarehouseStatus (Detail) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setNString(1, status.getDetail());
            ResultSet rs = executeUpdate(ps);
            if(rs.next()) return rs.getInt(1);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public WarehouseStatus getStatusByStatusID(int status){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from WarehouseStatus where StatusID=?");
            ps.setInt(1, status);
            return (WarehouseStatus)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public List<WarehouseStatus> getAllStatus(){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from WarehouseStatus");
            return (List<WarehouseStatus>) (Object) getListObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return Collections.emptyList();
    }
}
