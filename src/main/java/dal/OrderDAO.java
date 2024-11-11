package dal;

import dto.CustomerOrderResponse;
import dto.ImportProductResponse;
import dto.OrderResponse;
import model.Order;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends DBContext{
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return null;
    }

    public int countOrdersComplete() {
        int totalexrOrders = 0;
        try {
        String query = "SELECT COUNT(*) AS total FROM [Order] o " +
                "WHERE o.StatusID =  4";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalexrOrders = resultSet.getInt("total");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalexrOrders;
    }



    public int countOrdersBeingDelivery()  {
        int totalexrOrders = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM [Order] o " +
                    "WHERE o.StatusID = 3";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalexrOrders = resultSet.getInt("total");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalexrOrders;
    }


    public int countOtherOrders()  {
        int totalOtherOrders = 0;
        try {
        String query = "SELECT COUNT(*) AS total FROM [Order] o"+
           " WHERE o.StatusID NOT IN (3,4)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalOtherOrders = resultSet.getInt("total");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalOtherOrders;


    }


    public int getTotalOrders() {
        int totalOrders = 0;
        try {

            String query = "SELECT COUNT(*) AS total FROM dbo.[Order]";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalOrders = resultSet.getInt("total");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalOrders;
    }

    public List<OrderResponse> getAllOrders() {
        List<OrderResponse> orderList = new ArrayList<>();

        String query = "SELECT o.OrderID, a.Name AS CustomerName, o.OrderTime AS OrderDate, " +
                "o.Price, os.Detail AS OrderStatusName, ps.Name AS OrderPaymentName " +
                "FROM [Order] o " +
                "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                "JOIN Account a ON c.AccountID = a.AccountID " +
                "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                "LEFT JOIN PaymentStatement ps ON o.PaymentStatementID = ps.PaymentStatementID";

        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                orderList.add(new OrderResponse(resultSet.getInt(1)
                        ,resultSet.getString(2)
                        ,resultSet.getTimestamp(3).toLocalDateTime()
                        ,resultSet.getInt(4)
                        ,resultSet.getString(5)
                        ,resultSet.getString(6)));
            }
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<OrderResponse> getAllOrdersdComplete(int index) {
        List<OrderResponse> orderList = new ArrayList<>();


        if (index == 1) {
            index = 0;
        } else if (index != 0) {
            index = (index - 1) * 5;
        }


        String query = "SELECT o.OrderID, a.Name AS CustomerName, o.OrderTime AS OrderDate, " +
                "o.Price, os.Detail AS OrderStatusName, ps.Name AS OrderPaymentName " +
                "FROM [Order] o " +
                "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                "JOIN Account a ON c.AccountID = a.AccountID " +
                "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                "LEFT JOIN PaymentStatement ps ON o.PaymentStatementID = ps.PaymentStatementID "+
                "WHERE o.StatusID = 4";
        query += "ORDER BY o.OrderID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            int paramIndex = 1;


            statement.setInt(paramIndex, index);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderList.add(new OrderResponse(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    public List<OrderResponse> getAllOrdersdBeing(int index) {
        List<OrderResponse> orderList = new ArrayList<>();


        if (index == 1) {
            index = 0;
        } else if (index != 0) {
            index = (index - 1) * 5;
        }


        String query = "SELECT o.OrderID, a.Name AS CustomerName, o.OrderTime AS OrderDate, " +
                "o.Price, os.Detail AS OrderStatusName, ps.Name AS OrderPaymentName " +
                "FROM [Order] o " +
                "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                "JOIN Account a ON c.AccountID = a.AccountID " +
                "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                "LEFT JOIN PaymentStatement ps ON o.PaymentStatementID = ps.PaymentStatementID "+
                "WHERE o.StatusID = 3";
        query += "ORDER BY o.OrderID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            int paramIndex = 1;


            statement.setInt(paramIndex, index);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderList.add(new OrderResponse(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    public List<OrderResponse> getAllOrdersd(int index, String search,String orderStatus) {
        List<OrderResponse> orderList = new ArrayList<>();


        if (index == 1) {
            index = 0;
        } else if (index != 0) {
            index = (index - 1) * 5;
        }


        String query = "SELECT o.OrderID, a.Name AS CustomerName, o.OrderTime AS OrderDate, " +
                "o.Price, os.Detail AS OrderStatusName, ps.Name AS OrderPaymentName " +
                "FROM [Order] o " +
                "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                "JOIN Account a ON c.AccountID = a.AccountID " +
                "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                "LEFT JOIN PaymentStatement ps ON o.PaymentStatementID = ps.PaymentStatementID ";


        if (search != null && !search.trim().isEmpty()) {
            query += "WHERE a.Name LIKE ? ";
        }
        if (orderStatus != null && !orderStatus.trim().isEmpty()) {
            if (search != null && !search.trim().isEmpty()) {
                query += "AND os.Detail = ? ";
            } else {
                query += "WHERE os.Detail = ? ";
            }
        }


        query += "ORDER BY o.OrderID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            int paramIndex = 1;
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(paramIndex++, "%" + search + "%");
            }

            if (orderStatus != null && !orderStatus.trim().isEmpty()) {
                statement.setString(paramIndex++, orderStatus);
            }

            statement.setInt(paramIndex, index);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderList.add(new OrderResponse(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public int getTotalOrdersWithSearch(String search) {
        String query = "SELECT COUNT(*) FROM [Order] o " +
                "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                "JOIN Account a ON c.AccountID = a.AccountID ";
        if (search != null && !search.trim().isEmpty()) {
            query += "WHERE a.Name LIKE ?";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (search != null && !search.trim().isEmpty()) {
                statement.setString(1, "%" + search + "%");
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Total count
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public int getTotalOrdersWithStatus(String orderStatus) {
        String query = "SELECT COUNT(*) FROM [Order] o " +
                "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                "JOIN Account a ON c.AccountID = a.AccountID " +
                "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                "WHERE os.Detail = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, orderStatus); // Set the orderStatus parameter
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1); // Return the count of orders
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addOrder(Order order) {
        int orderID = -1;
        String sql = "INSERT INTO [Order] (CustomerID, PaymentStatementID, ContactInformationID, VoucherID, Price, OrderTime, StatusID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(order.getCustomerID());
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, order.getCustomerID());
            st.setInt(2, order.getPaymentStatementID());
            st.setInt(3, order.getContactInformationID());
            if (order.getVoucherID() != null) {
                st.setInt(4, order.getVoucherID());
            } else {
                st.setNull(4, java.sql.Types.INTEGER);
            }
            st.setInt(5, order.getPrice());
            if (order.getOrderTime() != null) {
                st.setTimestamp(6, java.sql.Timestamp.valueOf(order.getOrderTime()));
            } else {
                st.setNull(6, java.sql.Types.TIMESTAMP);
            }
            st.setInt(7, order.getStatusID());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                orderID = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderID;
    }

    public List<CustomerOrderResponse> getListCustomerOrders(int index, String searchName, int statusID,String sortPrice) {
        List<CustomerOrderResponse> customerOrderList = new ArrayList<>();
        String sql;

        if (searchName != null && !searchName.isEmpty() && statusID > 0) {
            sql = "select o.OrderID, a.[Name], a.Email, o.Price, o.OrderTime, os.Detail,o.StatusID, ci.PhoneNumber, ci.[Address] " +
                    "from [Order] o " +
                    "join Customer c on o.CustomerID = c.CustomerID " +
                    "join Account a on c.AccountID = a.AccountID " +
                    "join ContactInformation ci on o.ContactInformationID = ci.ContactInformationID " +
                    "join OrderStatus os on o.StatusID = os.StatusID " +
                    "where a.[Name] like ? and o.StatusID = ? " +
                    "order by o.Price " + (sortPrice.equals("asc") ? "ASC" : "DESC") + " " +
                    "offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        } else if (searchName != null && !searchName.isEmpty()) {
            sql = "select o.OrderID, a.[Name], a.Email, o.Price, o.OrderTime, os.Detail,o.StatusID, ci.PhoneNumber, ci.[Address] " +
                    "from [Order] o " +
                    "join Customer c on o.CustomerID = c.CustomerID " +
                    "join Account a on c.AccountID = a.AccountID " +
                    "join ContactInformation ci on o.ContactInformationID = ci.ContactInformationID " +
                    "join OrderStatus os on o.StatusID = os.StatusID " +
                    "where a.[Name] like ? " +
                    "order by o.Price " + (sortPrice.equals("asc") ? "ASC" : "DESC") + " " +
                    "offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        } else if (statusID > 0) {
            sql = "select o.OrderID, a.[Name], a.Email, o.Price, o.OrderTime, os.Detail,o.StatusID, ci.PhoneNumber, ci.[Address] " +
                    "from [Order] o " +
                    "join Customer c on o.CustomerID = c.CustomerID " +
                    "join Account a on c.AccountID = a.AccountID " +
                    "join ContactInformation ci on o.ContactInformationID = ci.ContactInformationID " +
                    "join OrderStatus os on o.StatusID = os.StatusID " +
                    "where o.StatusID = ? " +
                    "order by o.Price " + (sortPrice.equals("asc") ? "ASC" : "DESC") + " " +
                    "offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        } else {
            sql = "select o.OrderID, a.[Name], a.Email, o.Price, o.OrderTime, os.Detail,o.StatusID, ci.PhoneNumber, ci.[Address] " +
                    "from [Order] o " +
                    "join Customer c on o.CustomerID = c.CustomerID " +
                    "join Account a on c.AccountID = a.AccountID " +
                    "join ContactInformation ci on o.ContactInformationID = ci.ContactInformationID " +
                    "join OrderStatus os on o.StatusID = os.StatusID " +
                    "order by o.Price " + (sortPrice.equals("asc") ? "ASC" : "DESC") + " " +
                    "offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            int paramIndex = 1;
            if (searchName != null && !searchName.isEmpty()) {
                st.setString(paramIndex++, "%" + searchName + "%");
            }
            if (statusID > 0) {
                st.setInt(paramIndex++, statusID);
            }
            st.setInt(paramIndex, (index - 1) * 5);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CustomerOrderResponse customerOrderResponse = new CustomerOrderResponse();
                customerOrderResponse.setOrderID(rs.getInt(1));
                customerOrderResponse.setCustomerName(rs.getString(2));
                customerOrderResponse.setEmail(rs.getString(3));
                customerOrderResponse.setPrice(rs.getInt(4));
                customerOrderResponse.setOrderTime(rs.getDate(5));
                customerOrderResponse.setStatusDetail(rs.getString(6));
                customerOrderResponse.setStatusID(rs.getInt(7));
                customerOrderResponse.setPhoneNumber(rs.getString(8));
                customerOrderResponse.setAddress(rs.getString(9));
                customerOrderList.add(customerOrderResponse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerOrderList;
    }

    public int getTotalCustomerOrders(String searchName, int statusID) {
        String sql;
        if (statusID > 0) {
            sql = "SELECT COUNT(*) FROM [Order] o " +
                    "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                    "JOIN Account a ON c.AccountID = a.AccountID " +
                    "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                    "WHERE a.[Name] LIKE ? AND o.StatusID = ?";
        } else {
            sql = "SELECT COUNT(*) FROM [Order] o " +
                    "JOIN Customer c ON o.CustomerID = c.CustomerID " +
                    "JOIN Account a ON c.AccountID = a.AccountID " +
                    "JOIN OrderStatus os ON o.StatusID = os.StatusID " +
                    "WHERE a.[Name] LIKE ?";
        }

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + searchName + "%");
            if (statusID > 0) {
                st.setInt(2, statusID);
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Return the count of orders
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0; // Return 0 if no results found
    }

    public void updateStatusOrderAfterPayment(int orderID){
        String sql="UPDATE [dbo].[Order]\n" +
                "   SET [StatusID] =2\n" +
                " WHERE  OrderID=?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, orderID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatusID(int orderID,int statusID){
        String sql="UPDATE [dbo].[Order]\n" +
                "   SET \n" +
                "      [StatusID] =?\n" +
                " WHERE OrderID=?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, statusID);
            st.setInt(2, orderID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
       dao.updateStatusID(34,3);


    }


}