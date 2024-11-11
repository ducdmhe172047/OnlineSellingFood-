package dal;

import dto.ProductDiscountResponse;
import model.Discount;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

public class DiscountDAO extends DBContext{

    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new Discount(rs.getInt("DiscountID"), rs.getInt("DiscountPercent"),rs.getTimestamp("StartTime").toLocalDateTime(), rs.getTimestamp("EndTime").toLocalDateTime());
    }

    public Discount getDiscountById(int discountID){
        String sql = "SELECT * FROM Discount WHERE DiscountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, discountID);
            return (Discount) getObject(ps);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    public List<ProductDiscountResponse> getProductDiscount(int index,String searchName) {
        List<ProductDiscountResponse> res = new Vector<ProductDiscountResponse>();
        String sql ;
        if (searchName != null && !searchName.isEmpty()) {
            sql = "SELECT p.ProductID, d.DiscountID, p.[Name], c.[Name] AS CategoryName, p.Price, d.DiscountPercent, d.StartTime, d.EndTime " +
                    "FROM Product p LEFT JOIN Discount d ON p.DiscountID = d.DiscountID " +
                    "JOIN Category c ON p.CategoryID = c.CategoryID " +
                    "WHERE p.[Name] LIKE ? " +
                    "ORDER BY ProductID " +
                    "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
        } else {
            sql = "SELECT p.ProductID, d.DiscountID, p.[Name], c.[Name]AS CategoryName, p.Price, d.DiscountPercent, d.StartTime, d.EndTime " +
                    "FROM Product p LEFT JOIN Discount d ON p.DiscountID = d.DiscountID " +
                    "JOIN Category c ON p.CategoryID = c.CategoryID " +
                    "ORDER BY ProductID " +
                    "OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY;";
        }


        try {
            PreparedStatement st=connection.prepareStatement(sql);
            if (searchName != null && !searchName.isEmpty()) {
                st.setNString(1, "%" + searchName + "%");
                st.setInt(2, (index - 1) * 5);
            } else {
                st.setInt(1, (index - 1) * 5);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // Lấy các giá trị thời gian bằng Timestamp và chuyển sang LocalDateTime
                Timestamp startTimestamp = rs.getTimestamp("StartTime");
                LocalDateTime startTime = startTimestamp != null ? startTimestamp.toLocalDateTime() : null;

                Timestamp endTimestamp = rs.getTimestamp("EndTime");
                LocalDateTime endTime = endTimestamp != null ? endTimestamp.toLocalDateTime() : null;

                res.add(new ProductDiscountResponse(
                        rs.getInt("ProductID"),
                        rs.getInt("DiscountID"),
                        rs.getString("Name"),
                        rs.getString("CategoryName"),
                        rs.getInt("Price"),
                        rs.getInt("DiscountPercent"),
                        startTime,
                        endTime
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return res;
    }
public int checkExistPercent(int discountPercent, LocalDateTime startTime, LocalDateTime endTime) {
    String sql = "select * from Discount where DiscountPercent=? and StartTime=? and EndTime=?";
    int exist = -1;
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, discountPercent);
        ps.setTimestamp(2,Timestamp.valueOf(startTime));
        ps.setTimestamp(3,Timestamp.valueOf(endTime));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            exist = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return exist;
}
    public void  modifyDiscount(int discountPercent, int productID, LocalDateTime startTime, LocalDateTime endTime){
        int discountID = checkExistPercent(discountPercent, startTime, endTime);

        int insertedKey = 0;
        String sql ="";
        if (discountID >=0){
            sql = "update Product set DiscountID=? where ProductID=?";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, discountID);
                ps.setInt(2, productID);
                ps.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            sql = "insert into Discount values(?,?,?)";
            try {

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, discountPercent);

                // Chuyển LocalDateTime thành Timestamp để lưu vào cơ sở dữ liệu
                Timestamp startTimestamp = Timestamp.valueOf(startTime);
                Timestamp endTimestamp = Timestamp.valueOf(endTime);

                ps.setTimestamp(2, startTimestamp);
                ps.setTimestamp(3, endTimestamp);
                ps.executeUpdate();
                if (ps.getGeneratedKeys().next()){
                    insertedKey = ps.getGeneratedKeys().getInt(1);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            sql = "update Product set DiscountID=? where ProductID=?";
            try {
                PreparedStatement   ps = connection.prepareStatement(sql);
                ps.setInt(1, insertedKey);
                ps.setInt(2, productID);
                ps.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int addDiscount(int discountPercent, LocalDateTime startTime, LocalDateTime endTime){
        String sql = "insert into Discount values(?,?,?)";
        try {

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, discountPercent);

            // Chuyển LocalDateTime thành Timestamp để lưu vào cơ sở dữ liệu
            Timestamp startTimestamp = Timestamp.valueOf(startTime);
            Timestamp endTimestamp = Timestamp.valueOf(endTime);

            ps.setTimestamp(2, startTimestamp);
            ps.setTimestamp(3, endTimestamp);
            ps.executeUpdate();
            if (ps.getGeneratedKeys()!=null && ps.getGeneratedKeys().next()){
                return ps.getGeneratedKeys().getInt(1);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteDiscount(int discountID){
        String sql = "delete from Discount where DiscountID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, discountID);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
       DiscountDAO dd=new DiscountDAO();
       List<ProductDiscountResponse> list=dd.getProductDiscount(1,"Product");
        System.out.println(list.size());
    }
    public Discount getDiscountByDiscountId(int discountID) {
        Discount discount = null;
        String sql = "SELECT * FROM Discount WHERE DiscountID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, discountID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDateTime startDate = rs.getTimestamp("StartTime").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("EndTime").toLocalDateTime();

                discount = new Discount(
                        rs.getInt("DiscountID"),
                        rs.getInt("DiscountPercent"),
                        startDate,
                        endDate
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }



    }
