package dal;

import dto.CustomerDetailRespone;
import dto.StaffDetailRespone;
import dto.StaffListResponse;
import model.Account;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Customer(rs.getInt("CustomerID"), rs.getInt("AccountID"), rs.getInt("Point"), rs.getInt("Level"));
    }

    public Customer getCustomerByCustomerID(int customerID) {
        try {
            PreparedStatement ps = connection.prepareStatement("select CustomerID,AccountID,Point,Level from Customer where CustomerID=?");
            ps.setInt(1, customerID);
            return (Customer) getObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Customer getCustomerByAccountID(int accountID) {
        try {
            PreparedStatement ps = connection.prepareStatement("select CustomerID,AccountID,Point,Level from Customer where AccountID=?");
            ps.setInt(1, accountID);
            return (Customer) getObject(ps);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) {
        try {
            PreparedStatement ps = connection.prepareStatement("update Customer set Point=?, Level=? where CustomerID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(3, customer.getCustomerID());
            ps.setInt(1, customer.getPoint());
            ps.setInt(2, customer.getLevel());
            ResultSet rs = executeUpdate(ps);
            if (rs != null) return rs.next();
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
    public Integer getCustomerIDByAccountID(int accountID) {
        String sql = "SELECT CustomerID FROM Customer WHERE AccountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("CustomerID");
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }


    public Integer addCustomer(Customer customer) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into Customer(AccountID,Point,Level) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customer.getAccountID());
            ps.setInt(2, customer.getPoint());
            ps.setInt(3, customer.getLevel());
            ResultSet rs = executeUpdate(ps);
            if (rs != null && rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public List<StaffListResponse> getAllCustomer(int index,String searchName) {
        List<StaffListResponse> listCustomer = new ArrayList<StaffListResponse>();
        String sql;
        if (searchName != null && !searchName.isEmpty()) {
          sql="select a.AccountID,a.Name,a.Email,ast.Detail,a.[Time]\n" +
                  "from Account a join AccountStatus ast on a.StatusID = ast.StatusID\n" +
                  "where a.RoleID = 6 and a.RoleID != 1 and a.[Name]  like ?\n" +
                  "order by AccountID\n" +
                  "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        }else{
            sql="select a.AccountID,a.Name,a.Email,ast.Detail,a.[Time]\n" +
                    "from Account a join AccountStatus ast on a.StatusID = ast.StatusID\n" +
                    "where a.RoleID = 6 and a.RoleID != 1 \n" +
                    "order by AccountID\n" +
                    "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (searchName != null && !searchName.isEmpty()) {
                st.setString(1, "%" + searchName + "%");
                st.setInt(2, (index - 1) * 5);
            }else{
                st.setInt(1, (index - 1) * 5);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StaffListResponse slr = new StaffListResponse();
                slr.setAccountID(rs.getInt("AccountID"));
                slr.setName(rs.getString("Name"));
                slr.setEmail(rs.getString("Email"));
                slr.setDetail(rs.getString("Detail"));
                slr.setTime(rs.getDate("Time"));
                listCustomer.add(slr);
            }
            return listCustomer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerDetailRespone getCustomerDetail(int accountID) {
        String sql = "select [as].StatusID,a.Name,a.Email,ci.PhoneNumber,ci.[Address],a.Birth,c.Point,c.[Level]\n" +
                "from Account a join AccountStatus [as] on a.StatusID = [as].StatusID\n" +
                "join [Role] r on r.RoleID=a.RoleID\n" +
                "join [Customer] c on c.AccountID = a.AccountID\n" +
                "join AccountContact ac on ac.AccountID = a.AccountID\n" +
                "join ContactInformation ci on ci.ContactInformationID = ac.ContactInformationID\n" +
                "where (a.RoleID = 6 and a.RoleID != 1 and a.AccountID=?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs = st.executeQuery();
            CustomerDetailRespone cdr = new CustomerDetailRespone();

            while (rs.next()) {
                cdr.setStatusID(rs.getInt("StatusID"));
                cdr.setName(rs.getString("Name"));
                cdr.setEmail(rs.getString("Email"));
                cdr.setPhoneNumber(rs.getString("PhoneNumber"));
                cdr.setAddress(rs.getString("Address"));
                cdr.setBirth(rs.getObject("birth", LocalDateTime.class));
                cdr.setPoint(rs.getInt("Point"));
                cdr.setLevel(rs.getInt("Level"));


            }
            return cdr;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void updateProfileCustomerForAdmin(Account a, Customer c) {
        String sql = "UPDATE [dbo].[Account]\n" +
                "   SET [StatusID] =?\n" +
                " WHERE AccountID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, a.getAccountID());
            st.setInt(1, a.getStatusID());
            st.executeUpdate();
            System.out.println("after execute update status");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "UPDATE [dbo].[Customer]\n" +
                "   SET  [Point] = ?\n" +
                "      ,[Level] = ?\n" +
                " WHERE AccountID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, c.getPoint());
            st.setInt(2, c.getLevel());
            st.setInt(3, c.getAccountID());

            System.out.println(c.getPoint());
            System.out.println(c.getLevel());
            System.out.println(c.getAccountID());
            st.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int getTotalAccountCustomer(String name) {
        String sql = "select count(*) from Account where   RoleID=6 and Name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void main(String[] args) {
//        CustomerDAO cdao = new CustomerDAO();
//        List<StaffListResponse> staffListResponses = cdao.getAllCustomer(1);
//        for (StaffListResponse staffListResponse : staffListResponses) {
//            System.out.println(staffListResponse);
//        }
    }
}
