package dal;

import model.ProductImg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductImgDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new ProductImg(rs.getInt("ProductID"), rs.getInt("ImgID"), rs.getInt("IsDefault"));
    }

    public ProductImg getDefaultImg(int productID){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from ProductImg where ProductID=? and IsDefault=1");
            ps.setInt(1,productID);
            return (ProductImg) getObject(ps);
        }catch(SQLException e){
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return null;
    }

    public List<ProductImg> getNotDefaultImg(int productID){
        try{
            PreparedStatement ps = connection.prepareStatement("select * from ProductImg where ProductID=? and IsDefault=0");
            ps.setInt(1,productID);
            return (List<ProductImg>) (Object) getListObject(ps);
        }catch(SQLException e) {
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public boolean addProductImg(int productID, int imgID){
        try{
            PreparedStatement ps = connection.prepareStatement("insert into ProductImg(ProductID, ImgID, IsDefault) values(?,?,0)");
            ps.setInt(1,productID);
            ps.setInt(2,imgID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return false;
    }

    public boolean updateProductImg(ProductImg productImg){
        try {
            PreparedStatement ps = connection.prepareStatement("update ProductImg set IsDefault=? where ProductID=? and ImgID=?");
            ps.setInt(1,productImg.getIsDefault());
            ps.setInt(2,productImg.getProductID());
            ps.setInt(3,productImg.getImgID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return false;
    }

    public boolean setDefaultProductImg(int productID, int imgID){
        try {
            connection.setAutoCommit(false);
            try{
                ProductImg productImg = getDefaultImg(productID);
                if(productImg!=null){
                    productImg.setIsDefault(0);
                    if(!updateProductImg(productImg)){
                        throw new RuntimeException("Fail to update " + productImg.getProductID() + " " + productImg.getImgID() + " to 0");
                    }
                }
                if(updateProductImg(new ProductImg(productID,imgID,1))){
                    connection.commit();
                    connection.setAutoCommit(true);
                    return true;
                }
                else throw new RuntimeException("Fail to update " + productImg.getProductID() + " " + productImg.getImgID() + " to 1");
            }catch(RuntimeException ex){
                logger.info(getClass().getName() + " " + ex.getMessage());
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return false;
    }

    public boolean deleteProductImg(int productID, int imgID){
        try{
            PreparedStatement ps = connection.prepareStatement("delete from ProductImg where ProductID=? and ImgID=?");
            ps.setInt(1,productID);
            ps.setInt(2,imgID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return false;
    }

    public List<ProductImg> deleteProductImg(int productID){
        try{
            PreparedStatement ps = connection.prepareStatement("delete from ProductImg where ProductID=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,productID);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            List<ProductImg> productImgs = new ArrayList<>();
            while(rs.next()){
                productImgs.add(new ProductImg(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
            }
            return productImgs;
        } catch (SQLException e) {
            logger.info(getClass().getName() + " " + e.getMessage());
        }
        return Collections.emptyList();
    }
}