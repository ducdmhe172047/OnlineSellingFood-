package dal;

import dto.StaffDetailRespone;
import dto.StaffListResponse;
import model.Account;
import model.ContactInformation;
import model.Staff;
import model.Warehouse;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Staff(rs.getInt("StaffID"),rs.getInt("AccountID"),rs.getInt("Salary"),rs.getInt("WarehouseID"));
    }

    public Staff getStaffByrID(int staffID){
        try{
            PreparedStatement ps = connection.prepareStatement("select StaffID,AccountID,Salary,WarehouseID from Staff where StaffID=?");
            ps.setInt(1, staffID);
            return (Staff)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public Staff getStaffByAccountID(int accountID){
        try{
            PreparedStatement ps = connection.prepareStatement("select StaffID,AccountID,Salary,WarehouseID from Staff where AccountID=?");
            ps.setInt(1, accountID);
            return (Staff)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public void updateStaffInformation(Staff staff){
        String sql="update Staff set Salary=?, WarehouseID=? where StaffID=?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setDouble(1, staff.getSalary());
            st.setInt(2, staff.getWarehouseID());
            st.setInt(3, staff.getStaffID());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addStaff(Staff staff){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into Staff(AccountID,Salary,WarehouseID) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, staff.getAccountID());
            ps.setInt(2, staff.getSalary());
            ps.setInt(3, staff.getWarehouseID());
            ResultSet rs = executeUpdate(ps);
            if(rs!=null&&rs.next()) return rs.getInt(1);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public List<StaffListResponse> getAllStaff(int index, String searchName) {
        List<StaffListResponse> listStaff = new ArrayList<>();
        String sql;
        if (searchName != null && !searchName.isEmpty()) {
            sql = "select a.AccountID,a.Name,a.Email,ast.Detail,a.[Time]\n" +
                    "from Account a join AccountStatus ast on a.StatusID = ast.StatusID\n" +
                    "where a.RoleID != 6 and a.RoleID != 1 and a.[Name]  like ?\n" +
                    "order by AccountID\n" +
                    "offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        } else {
            sql = "select a.AccountID,a.Name,a.Email,ast.Detail,a.[Time]\n" +
                    "from Account a join AccountStatus ast on a.StatusID = ast.StatusID\n" +
                    "where a.RoleID != 6 and a.RoleID != 1 \n" +
                    "order by AccountID\n" +
                    "offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (searchName != null && !searchName.isEmpty()) {
                st.setString(1, "%" + searchName + "%");
                st.setInt(2, (index - 1) * 5);
            } else {
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
                listStaff.add(slr);
            }
            return listStaff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStaffIDbyAccountID(int accountID){
        String sql="Select StaffID  from Staff where AccountID = ?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return rs.getInt("StaffID");
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    public List<StaffListResponse> getAllStaff(){
        List<StaffListResponse> listStaff = new ArrayList<StaffListResponse>();
        String sql = "\tselect a.AccountID,a.Name,a.Email,ast.Detail,a.[Time]\n" +
                "\tfrom Account a join AccountStatus ast on a.StatusID = ast.StatusID\n" +
                "\twhere a.RoleID != 6 and a.RoleID != 1";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                StaffListResponse slr=new StaffListResponse();
                slr.setAccountID(rs.getInt("AccountID"));
                slr.setName(rs.getString("Name"));
                slr.setEmail(rs.getString("Email"));
                slr.setDetail(rs.getString("Detail"));
                slr.setTime(rs.getDate("Time"));
                listStaff.add(slr);
            }
            return listStaff;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StaffDetailRespone getStaffDetail(int accountID){
        String sql = "select r.[RoleID],[as].StatusID,a.Name,a.Email,ci.PhoneNumber,ci.[Address],a.Birth,a.GenderID,s.Salary,w.[WarehouseID],w.[Name]as NameWarehouse\n" +
                "from Account a join Staff s on a.AccountID = s.AccountID\n" +
                "join [Role] r on r.RoleID = a.RoleID\n" +
                "join Warehouse w on w.WarehouseID = s.WarehouseID\n" +
                "join AccountContact ac on ac.AccountID = a.AccountID\n" +
                "join ContactInformation ci on ci.ContactInformationID = ac.ContactInformationID\n" +
                "join AccountStatus [as] on a.StatusID=[as].StatusID\n" +
                "where (a.RoleID != 6 and a.RoleID != 1 and a.AccountID=?)";

        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs=st.executeQuery();
            StaffDetailRespone sdr=new StaffDetailRespone();

            while(rs.next()){
                sdr.setRoleID(rs.getInt("RoleID"));
                sdr.setStatusID(rs.getInt("StatusID"));
                sdr.setGender(rs.getInt("GenderID"));
                sdr.setName(rs.getString("Name"));
                sdr.setEmail(rs.getString("Email"));
                sdr.setPhoneNumber(rs.getString("PhoneNumber"));
                sdr.setAddress(rs.getString("Address"));
                sdr.setBirth(rs.getObject("Birth", LocalDateTime.class));
                sdr.setSalary(rs.getInt("Salary"));
                sdr.setWarehouseID(rs.getInt("WarehouseID"));
                sdr.setNameWarehouse(rs.getString("NameWarehouse"));

            }
            return sdr;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }



    public int getTotalAccountStaff(String searchName) {
        String sql = "select count(*) from Account where RoleID!=1 And RoleID!=6 and Name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%" + searchName + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }



}

