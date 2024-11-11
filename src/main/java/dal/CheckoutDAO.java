package dal;

import dto.ApplyVoucherToCheckoutResponse;
import dto.CheckoutContactDetailResponse;
import dto.ProductCheckoutResponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
//        return new Discount(rs.getObject("DiscountID",Integer.class),rs.getObject("DiscountPercent",Integer.class),rs.getObject("St"))
        return null;
    }

    public CheckoutContactDetailResponse getCheckoutContactDetail(int accounID){
        String sql="select c.ContactInformationID,a.[Name],c.PhoneNumber,c.[Address],ac.IsDefault\n" +
                "from Account a join AccountContact ac on a.AccountID=ac.AccountID\n" +
                "join ContactInformation c  on ac.ContactInformationID=c.ContactInformationID\n" +
                "where a.AccountID=? and IsDefault !=0";

        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, accounID);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                CheckoutContactDetailResponse checkoutContactDetailResponse=new CheckoutContactDetailResponse();
                checkoutContactDetailResponse.setContactInformationID(rs.getInt("ContactInformationID"));
                checkoutContactDetailResponse.setName(rs.getString("Name"));
                checkoutContactDetailResponse.setPhone(rs.getString("PhoneNumber"));
                checkoutContactDetailResponse.setAddress(rs.getString("Address"));
                checkoutContactDetailResponse.setIsDefault(rs.getInt("IsDefault"));
                return checkoutContactDetailResponse;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<ProductCheckoutResponse> getProductCheckout(int customerID){
        List<ProductCheckoutResponse> list=new ArrayList<>();
        String sql="select p.ProductID,i.Imglink,p.[Name],c.Quantity,p.Price,COALESCE(d.DiscountPercent, 0) AS DiscountPercent,\n" +
                "AVG(fp.Star) AS AverageStar,COUNT(fp.Feedback) AS TotalFeedback\n" +
                "from Cart c join Product p on c.ProductID=p.ProductID\n" +
                " left join Discount d on p.DiscountID=d.DiscountID\n" +
                " left join FeedbackProduct fp on p.ProductID=fp.ProductID\n" +
                " left join ProductImg pi on p.ProductID=pi.ProductID\n" +
                " left join Img i on pi.ImgID=i.ImgID\n" +
                "where  c.CustomerID=?\n" +
                "group by p.ProductID,i.Imglink,p.[Name],c.Quantity,p.Price,d.DiscountPercent";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                ProductCheckoutResponse productCheckoutResponse=new ProductCheckoutResponse();
                productCheckoutResponse.setProductID(rs.getInt("ProductID"));
                productCheckoutResponse.setImgLink(rs.getString("Imglink"));
                productCheckoutResponse.setName(rs.getString("Name"));
                productCheckoutResponse.setQuantity(rs.getInt("Quantity"));
                productCheckoutResponse.setPrice(rs.getInt("Price"));
                productCheckoutResponse.setDiscountPercent(rs.getInt("DiscountPercent"));
                productCheckoutResponse.setPriceAfterDiscount(productCheckoutResponse.getPrice() - productCheckoutResponse.getPrice() * productCheckoutResponse.getDiscountPercent() / 100);
                productCheckoutResponse.setAverageStar(rs.getInt("AverageStar"));
                productCheckoutResponse.setTotalFeedback(rs.getInt("TotalFeedback"));
                list.add(productCheckoutResponse);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ApplyVoucherToCheckoutResponse> getApplyVoucherToCheckout(int customerID){
        List<ApplyVoucherToCheckoutResponse> list=new ArrayList<>();
        String sql="SELECT \n" +
                "    cv.VoucherID,\n" +
                "    d.DiscountPercent,\n" +
                "    v.Inventory,\n" +
                "    d.StartTime,\n" +
                "    d.EndTime,\n" +
                "    DATEDIFF(DAY, d.StartTime, d.EndTime) AS RemainingDay\n" +
                "FROM CustomerVoucher cv  \n" +
                "JOIN  Voucher v ON cv.VoucherID = v.VoucherID \n" +
                "JOIN Discount d ON v.DiscountID = d.DiscountID\n" +
                "where cv.CustomerID=? and d.EndTime>GETDATE() and d.StartTime<=GETDATE()";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                ApplyVoucherToCheckoutResponse avt=new ApplyVoucherToCheckoutResponse();
                avt.setVoucherID(rs.getInt("VoucherID"));
                avt.setDiscountPercent(rs.getInt("DiscountPercent"));
                avt.setInventory(rs.getInt("Inventory"));
                avt.setStartDate(rs.getString("StartTime"));
                avt.setEndDate(rs.getString("EndTime"));
                avt.setRemainingDay(rs.getInt("RemainingDay"));
                list.add(avt);

            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getDiscountPercentByVoucherID(int voucherID){
        String sql="select d.DiscountPercent from Discount d join Voucher v on d.DiscountID=v.DiscountID\n" +
                "where v.VoucherID=?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, voucherID);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return rs.getInt("DiscountPercent");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    public static void main(String[] args) {
        CheckoutDAO checkoutDAO=new CheckoutDAO();
        List<ApplyVoucherToCheckoutResponse> list=checkoutDAO.getApplyVoucherToCheckout(1);
        System.out.println(list);
    }
}
