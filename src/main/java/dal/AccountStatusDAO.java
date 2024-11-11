package dal;

import model.AccountStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountStatusDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new AccountStatus(rs.getInt("StatusID"),rs.getString("Detail"));
    }

    public Integer addStatus(AccountStatus status){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into AccountStatus (Detail) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setNString(1, status.getDetail());
            ResultSet rs = executeUpdate(ps);
            if(rs!=null&&rs.next()) return rs.getInt(1);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public AccountStatus getStatusByStatusID(int status){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from AccountStatus where StatusID=?");
            ps.setInt(1, status);
            return (AccountStatus)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public List<AccountStatus> getAllStatus(){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from AccountStatus");
            return (List<AccountStatus>) (Object) getListObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return Collections.emptyList();
    }
}
