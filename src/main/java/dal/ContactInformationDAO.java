package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ContactInformation;

public class ContactInformationDAO extends DBContext{

    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new ContactInformation(rs.getInt("ContactInformationID"), rs.getString("Address"), rs.getString("PhoneNumber"));
    }

    public Integer addContact(ContactInformation ci){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into ContactInformation (Address, PhoneNumber) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setNString(1, ci.getAddress());
            ps.setString(2, ci.getPhoneNumber());
            ResultSet rs = executeUpdate(ps);
            if(rs!=null&&rs.next()) return rs.getInt(1);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }

    public boolean deleteContact(int contactInformationID){
        try{
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ContactInformation WHERE ContactInformationID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, contactInformationID);
            ResultSet rs = executeUpdate(ps);
            if(rs!=null)return rs.next();
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return false;
    }

    public ContactInformation getContactInformationByAccountID(int accountID){
        ContactInformation ci = new ContactInformation();
        String sql="SELECT ci.ContactInformationID, ci.Address, ci.PhoneNumber\n" +
                "FROM ContactInformation ci \n" +
                "JOIN AccountContact ac ON ci.ContactInformationID = ac.ContactInformationID\n" +
                "WHERE ac.AccountID = ?";
        try {
         PreparedStatement st=connection.prepareStatement(sql);
         st.setInt(1, accountID);
         ResultSet rs = st.executeQuery();
         if(rs!=null&&rs.next()){
             ci.setContactInformationID(rs.getInt("ContactInformationID"));
             ci.setAddress(rs.getString("Address"));
             ci.setPhoneNumber(rs.getString("PhoneNumber"));

         }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ci;
    }

    public void update(ContactInformation contactInformation){
        String sql="UPDATE ContactInformation SET Address = ?, PhoneNumber = ? WHERE ContactInformationID = ?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, contactInformation.getAddress());
            st.setString(2, contactInformation.getPhoneNumber());
            st.setInt(3, contactInformation.getContactInformationID());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ContactInformation getContactInformationByContactID(Integer contactInformationID){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from ContactInformation where ContactInformationID=?");
            ps.setInt(1, contactInformationID);
            return (ContactInformation)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }


    public ContactInformation getContactInformationByAddressAndPhone(String Adress, String phoneNumber){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from ContactInformation where PhoneNumber=? and Address=?");
            ps.setNString(2, Adress);
            ps.setString(1, phoneNumber);
            return (ContactInformation)getObject(ps);
        }catch (SQLException e){
            logger.info(getClass().getName()+": "+e.getMessage());
        }
        return null;
    }
    public List<ContactInformation> getAllContactInformation() {
        List<ContactInformation> contactList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ContactInformation");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contactList.add(new ContactInformation(rs.getInt("ContactInformationID"), rs.getString("Address"), rs.getString("PhoneNumber")));
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + ": " + e.getMessage());
        }
        return contactList;
    }
    public Integer updateContact(String address, String phoneNumber) {
        ContactInformation contact = getContactInformationByAddressAndPhone(address, phoneNumber);
        if (contact == null) {
            contact = new ContactInformation(address,phoneNumber);
            contact.setContactInformationID(addContact(contact));
        }
        return contact.getContactInformationID();
    }

}