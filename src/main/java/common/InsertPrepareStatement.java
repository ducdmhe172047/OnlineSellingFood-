package common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

public class InsertPrepareStatement {
    public static void insertInteger(Integer ob, PreparedStatement ps, int index) throws SQLException {
        if(ob==null) ps.setNull(index, Types.INTEGER);
        else ps.setInt(index, ob);
    }

    public static void insertNvarchar(String ob, PreparedStatement ps, int index) throws SQLException {
        if(ob==null) ps.setNull(index,Types.NVARCHAR);
        else ps.setNString(index, ob);
    }

    public static void insertVarchar(String ob, PreparedStatement ps, int index) throws SQLException {
        if(ob==null) ps.setNull(index,Types.VARCHAR);
        else ps.setString(index, ob);
    }

    public static void insertDateTime(LocalDateTime ob, PreparedStatement ps, int index) throws SQLException {
        if(ob==null) ps.setNull(index,Types.TIMESTAMP);
        else ps.setTimestamp(index, Timestamp.valueOf(ob));
    }
    
}
