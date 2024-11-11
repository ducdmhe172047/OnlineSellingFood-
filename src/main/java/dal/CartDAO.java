package dal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.CartItem;
import model.Cart;


public class CartDAO extends DBContext {


    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setCustomerID(rs.getInt("CustomerID"));
        cart.setProductID(rs.getInt("ProductID"));
        cart.setQuantity(rs.getInt("quantity"));
        return cart;
    }


    public int insert(Cart cart) {
        int affectedRows = 0;
        try {
            String sql = "INSERT INTO [dbo].[Cart] (CustomerID, ProductID, Quantity) VALUES (?, ?, ?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cart.getCustomerID());
            pre.setInt(2, cart.getProductID());
            pre.setInt(3, cart.getQuantity());
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }


    public int deleteByCustomerId(int customerId) {
        int affectedRows = 0;
        try {
            String sql = "DELETE FROM [dbo].[Cart] WHERE CustomerID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }


    public int getCartIdByCustomerId(int customerId) {
        int cartId = -1;
        try {
            String sql = "SELECT id FROM [dbo].[Cart]\n"
                    + " WHERE customerId = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                cartId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartId;
    }
    public int clearCartByCustomerId(int customerId) {
        int affectedRows = 0;
        try {
            String sql = "DELETE FROM [dbo].[Cart] WHERE CustomerID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }



    public List<Cart> getCartByCustomerId(int customerId) {
        List<Cart> cartItems = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Cart WHERE CustomerID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCustomerID(rs.getInt("CustomerID"));
                cart.setProductID(rs.getInt("ProductID"));
                cart.setQuantity(rs.getInt("Quantity"));
                cartItems.add(cart);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartItems;
    }


    public Cart getCartByCustomerIdAndProductId(int customerId, int productId) {
        Cart cart = null;
        try {
            String sql = "SELECT * FROM Cart WHERE CustomerID = ? AND ProductID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            pre.setInt(2, productId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setCustomerID(rs.getInt("CustomerID"));
                cart.setProductID(rs.getInt("ProductID"));
                cart.setQuantity(rs.getInt("Quantity"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cart;
    }

    public boolean hasProductInCart(int customerID) {
        String query = "SELECT COUNT(*) FROM Cart WHERE customerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public int update(Cart cart) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE Cart SET Quantity = ? WHERE CustomerID = ? AND ProductID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cart.getQuantity());
            pre.setInt(2, cart.getCustomerID());
            pre.setInt(3, cart.getProductID());
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }
    public int deleteByCustomerIdAndProductId(int customerId, int productId) {
        int affectedRows = 0;
        try {
            String sql = "DELETE FROM Cart WHERE CustomerID = ? AND ProductID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, customerId);
            pre.setInt(2, productId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    public int updateCartQuantity(int customerId, int productId, int quantity) {
        int affectedRows = 0;
        try {
            String sql = "UPDATE Cart SET Quantity = ? WHERE CustomerID = ? AND ProductID = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(2, customerId);
            pre.setInt(3, productId);
            affectedRows = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return affectedRows;
    }

    public List<CartItem> getCartItemsByCustomerID(int customerID) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql="select d.DiscountID,d.DiscountPercent,p.price as PriceOrigin,p.ProductID,  p.Price * (1 - COALESCE(d.DiscountPercent/100.0, 0)) AS Price,c.Quantity,p.UnitID from Cart c  join Product p on c.ProductID=p.ProductID \n" +
                "left join Discount d on p.DiscountID=d.DiscountID where CustomerID=?";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                CartItem cartItem=new CartItem();
                cartItem.setProductID(rs.getInt("ProductID"));
                cartItem.setPrice(rs.getInt("Price"));
                cartItem.setQuantity(rs.getInt("Quantity"));
                cartItem.setUnitID(rs.getInt("UnitID"));
                cartItems.add(cartItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartItems;
    }

    public static void main(String[] args) {
        CartDAO cartDAO = new CartDAO();
        List<CartItem> ci = cartDAO.getCartItemsByCustomerID(1);
        for (CartItem cartItem : ci) {
            System.out.println(cartItem.getPrice());
        }

    }
}
