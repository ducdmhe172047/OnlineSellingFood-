
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class DBContext {
    public static Connection connection;
    protected Logger logger = Logger.getLogger(getClass().getName());

    protected DBContext() {
        if(connection==null){
            // Edit URL , username, password to authenticate with your MS SQL Server
            String username = "sa", password = "123", port="1433", dataBaseName="SalesManagement1", url = "jdbc:sqlserver://localhost:"+port+";databaseName="+dataBaseName;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException | ClassNotFoundException ex) {
                logger.info(ex.getMessage());
            }
        }
    }

    protected ResultSet executeUpdate(PreparedStatement ps){
        try {
            ps.executeUpdate();
            return ps.getGeneratedKeys();
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    protected Object getObject(PreparedStatement ps){
        try {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return getObjectByRs(rs);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    protected List<Object> getListObject(PreparedStatement ps){
        List<Object> objects = new ArrayList<>();
        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                objects.add(getObjectByRs(rs));
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return objects;
    }

    protected abstract Object getObjectByRs(ResultSet rs) throws SQLException;
}