package dal;

import common.Encrypt;
import common.InsertPrepareStatement;
import model.Account;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Account(rs.getObject("accountid", Integer.class), rs.getObject("roleid", Integer.class), rs.getString("email"), rs.getString("name"), rs.getObject("genderid", Integer.class), rs.getString("password"), rs.getObject("birth", LocalDateTime.class), rs.getObject("time", LocalDateTime.class), rs.getObject("statusID", Integer.class));
    }

    //login check
    public Account getAccountByEmailPassword(String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("select AccountID,RoleID,Email,Name,GenderID,Birth,Password,Time,StatusID from Account where Email=? and Password=?");
            ps.setString(1, email);
            ps.setString(2, Encrypt.toHexString(Encrypt.getSHA(password)));
            return (Account) getObject(ps);
        } catch (SQLException | NoSuchAlgorithmException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }
    public boolean isEmailExist(String email) {
        String query = "SELECT COUNT(*) FROM Account WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account getAccountByEmail(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement("select AccountID,RoleID,Email,Name,GenderID,Birth,Password,Time,StatusID from Account where Email=?");
            ps.setString(1, email);
            return (Account) getObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    //get account by account id
    public Account getAccountByAccountID(int accountID) {
        try {
            PreparedStatement ps = connection.prepareStatement("select AccountID,RoleID,Email,Name,GenderID,Birth,Password,Time,StatusID from Account where AccountID=?");
            ps.setInt(1, accountID);
            return (Account) getObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public boolean updateAccountInformation(Account acc) {
//        int insertedKey = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("update Account set RoleID=?, Email=?, Name=?, GenderID=?, Birth=?, StatusID=? where AccountID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, acc.getRoleID());
            ps.setString(2, acc.getEmail());
            ps.setNString(3, acc.getName());
            InsertPrepareStatement.insertInteger(acc.getGenderID(), ps, 4);
            InsertPrepareStatement.insertDateTime(acc.getBirth(), ps, 5);
            ps.setInt(6, acc.getStatusID());
            ps.setInt(7, acc.getAccountID());
            ResultSet rs = executeUpdate(ps);

            if (rs != null) return rs.next();
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public boolean updateAccountPassword(int accountID, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("update Account set Password=? where AccountID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Encrypt.toHexString(Encrypt.getSHA(password)));
            ps.setInt(2, accountID);
            ResultSet rs = executeUpdate(ps);
            if (rs != null) return rs.next();
        } catch (SQLException | NoSuchAlgorithmException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public Integer addAccount(Account acc) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into Account (RoleID, Email, Name, GenderID, Birth, Password, Time, StatusID) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, acc.getRoleID());
            ps.setString(2, acc.getEmail());
            ps.setNString(3, acc.getName());
            InsertPrepareStatement.insertInteger(acc.getGenderID(), ps, 4);
            InsertPrepareStatement.insertDateTime(acc.getBirth(), ps, 5);
            ps.setString(6, Encrypt.toHexString(Encrypt.getSHA(acc.getPassword())));
            ps.setTimestamp(7, Timestamp.valueOf(acc.getTime()));
            ps.setInt(8, acc.getStatusID());
            ResultSet rs = executeUpdate(ps);
            if (rs != null && rs.next()) return rs.getInt(1);
        } catch (SQLException | NoSuchAlgorithmException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }



    public static void main(String[] args) {

    }
}