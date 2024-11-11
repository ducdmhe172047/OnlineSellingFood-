package dal;

import common.Encrypt;
import model.Otp;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;

public class OtpDAO extends  DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Otp(rs.getInt("AccountID"), rs.getString("Code"), rs.getObject("ExpiryDateTime", LocalDateTime.class));
    }

    public boolean checkOtp(int accountID, String otp) {
        try{
            PreparedStatement ps = connection.prepareStatement("select * from OTP where Code=? and AccountID=?");
            ps.setString(1, Encrypt.toHexString(Encrypt.getSHA(otp)));
            ps.setInt(2, accountID);
            Otp o = (Otp)getObject(ps);
            if(o!=null && o.getExpiryDateTime().compareTo(LocalDateTime.now())>=0){
                return true;
            }
        }catch (SQLException | NoSuchAlgorithmException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return false;
    }

    public Integer addOtp(Otp otp) {
        try{
            PreparedStatement ps = connection.prepareStatement("insert into OTP (AccountID, Code, ExpiryDateTime) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, otp.getAccountID());
            ps.setString(2, Encrypt.toHexString(Encrypt.getSHA(otp.getCode())));
            ps.setTimestamp(3, Timestamp.valueOf(otp.getExpiryDateTime()));
            ResultSet rs = executeUpdate(ps);
            if(rs!=null&&rs.next()) return rs.getInt(1);
        }catch (SQLException | NoSuchAlgorithmException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public boolean deleteOtp(int accountID){
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM OTP WHERE AccountID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accountID);
            ResultSet rs = executeUpdate(ps);
            if(rs!=null)return rs.next();
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return false;
    }

    public boolean updateOtp(Otp otp) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE OTP SET Code = ?, ExpiryDateTime = ? WHERE AccountID = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(3, otp.getAccountID());
            ps.setString(1, Encrypt.toHexString(Encrypt.getSHA(otp.getCode())));
            ps.setTimestamp(2, Timestamp.valueOf(otp.getExpiryDateTime()));
            ResultSet rs = executeUpdate(ps);
            if(rs!=null)return rs.next();
        } catch (SQLException | NoSuchAlgorithmException e) {
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return false;
    }
}
