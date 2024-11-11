package dal;

import dto.CustomerVoucherResponse;
import model.CustomerVoucher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerVoucherDAO  extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new CustomerVoucher(rs.getInt("CustomerID"), rs.getInt("VoucherID"));
    }

    public List<CustomerVoucher> getCustomerVoucher(int customerID) {
        String sql = "SELECT * FROM CustomerVoucher WHERE customerID = ?";
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            return (List<CustomerVoucher>) (Object) getListObject(ps);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return Collections.emptyList();
    }

    public boolean addCustomerVoucher(int customerID, int voucherID) {
        String sql = "INSERT INTO CustomerVoucher (customerID, voucherID) VALUES (?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, customerID);
            st.setInt(2, voucherID);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return false;
    }

    public void deleteCustomerVoucher(int customerID,int voucherID) {
        String sql = "DELETE FROM CustomerVoucher WHERE VoucherID = ? AND CustomerID = ?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, voucherID);
            st.setInt(2, customerID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkExistCustomerVoucher(int customerID, int voucherID) {
        String sql = "SELECT * FROM CustomerVoucher WHERE customerID = ? AND voucherID = ?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, customerID);
            st.setInt(2, voucherID);
            ResultSet rs=st.executeQuery();
            if(rs.next()) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CustomerVoucherResponse> getCustomerVoucher()  {
        List<CustomerVoucherResponse> list = new ArrayList<CustomerVoucherResponse>();
        String sql = "select c.CustomerID,a.[Name],c.Point,c.[Level] from Customer" +
                " c join Account a on c.AccountID=a.AccountID";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                CustomerVoucherResponse cvr = new CustomerVoucherResponse();
                cvr.setCustomerID(rs.getInt(1));
                cvr.setCustomerName(rs.getString(2));
                cvr.setPoint(rs.getInt(3));
                cvr.setLevel(rs.getInt(4));
                list.add(cvr);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Integer> getCustomerIDWithVoucher(int voucherID) {
        List<Integer> customerIds = new ArrayList<>();
        String sql = "SELECT CustomerID FROM CustomerVoucher WHERE voucherID = ?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, voucherID);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                customerIds.add(rs.getInt(1));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerIds;

    }

    public static void main(String[] args) {
        CustomerVoucherDAO dao = new CustomerVoucherDAO();
        List<Integer> customerIDs = dao.getCustomerIDWithVoucher(1);
        System.out.println(customerIDs);
    }

}
