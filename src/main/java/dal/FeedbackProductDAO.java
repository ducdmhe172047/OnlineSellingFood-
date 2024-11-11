package dal;

import dto.FeedbackResponse;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackProductDAO extends DBContext {
    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return null;
    }

    public boolean existOrderProduct(int productID, int customerID) {
        String sql = "select * from FeedbackProduct where ProductID=? and CustomerID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            st.setInt(2, customerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void updateFeedbackProduct(int productID, int customerID, int star, String comment, LocalDateTime time, Integer replyID) {
        String sql = "UPDATE [dbo].[FeedbackProduct]\n" +
                "   SET Star=?,Feedback=?,Time=?,ReplyID=?\n" +
                " WHERE ProductID=? and CustomerID=?\n";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, star);
            st.setString(2, comment);
            st.setTimestamp(3, Timestamp.valueOf(time));
            st.setInt(4, productID);
            st.setInt(5, customerID);
            if (replyID != null) {
                st.setInt(6, replyID);
            } else {
                st.setNull(6, Types.INTEGER);
            }
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFeedbackProduct(int productID, int customerID, Integer star, String comment, LocalDateTime time, Integer replyID) {
        String sql = "INSERT INTO [dbo].[FeedbackProduct]\n" +
                "           ([ProductID],\n" +
                "           [CustomerID],\n" +
                "           [Star],\n" +
                "           [Feedback],\n" +
                "           [Time],\n" +
                "           [ReplyID])\n" +
                "     VALUES\n" +
                "           (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            st.setInt(2, customerID);
            if (star != null) {
                st.setInt(3, star);
            } else {
                st.setNull(3, Types.INTEGER);
            }
            st.setString(4, comment);
            st.setTimestamp(5, Timestamp.valueOf(time));
            if (replyID != null) {
                st.setInt(6, replyID);
            } else {
                st.setNull(6, Types.INTEGER);
            }
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFeedbackProductAfterOrder(int productID, int customerID, Integer star, String comment, LocalDateTime time, Integer replyID) {
        String sql = "INSERT INTO [dbo].[FeedbackProduct]\n" +
                "           ([ProductID]\n" +
                "           ,[CustomerID]\n" +
                "           ,[Star]\n" +
                "           ,[Feedback]\n" +
                "           ,[Time]\n" +
                "           ,[ReplyID])\n" +
                "     VALUES\n" +
                "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            st.setInt(2, customerID);
            st.setNull(3, Types.INTEGER);
            st.setNull(4, Types.VARCHAR);
            st.setNull(5, Types.TIMESTAMP);
            st.setNull(6, Types.INTEGER);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FeedbackResponse> getAllFeedbackProductForManage(int productID, String searchName, int index) {
        List<FeedbackResponse> list = new ArrayList<>();
        String sql;
        if (searchName != null && !searchName.isEmpty()) {
            sql = "SELECT f.CustomerID,a.[Name], f.Star, f.Feedback, FORMAT(f.[Time], 'yyyy-MM-dd HH:mm') AS FormattedTime, f.ReplyID, f.FeedbackID \n" +
                    "                 FROM FeedbackProduct f \n" +
                    "                 JOIN Customer c ON f.CustomerID = c.CustomerID\n" +
                    "                 JOIN Account a ON c.AccountID = a.AccountID\n" +
                    "                 WHERE f.ProductID = ?\n" +
                    "                 AND f.Star IS NOT NULL \n" +
                    "                 AND f.Feedback IS NOT NULL \n" +
                    "                 AND f.[Time] IS NOT NULL AND a.[Name] like ?\n" +
                    "                 ORDER BY f.ReplyID, f.FeedbackID \n" +
                    "             offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
            ;
        } else {
            sql = "SELECT f.CustomerID,a.[Name], f.Star, f.Feedback, FORMAT(f.[Time], 'yyyy-MM-dd HH:mm') AS FormattedTime, f.ReplyID, f.FeedbackID \n" +
                    "                 FROM FeedbackProduct f \n" +
                    "                 JOIN Customer c ON f.CustomerID = c.CustomerID\n" +
                    "                 JOIN Account a ON c.AccountID = a.AccountID\n" +
                    "                 WHERE f.ProductID = ?\n" +
                    "                 AND f.Star IS NOT NULL \n" +
                    "                 AND f.Feedback IS NOT NULL \n" +
                    "                 AND f.[Time] IS NOT NULL \n" +
                    "                 ORDER BY f.ReplyID, f.FeedbackID \n" +
                    "             offset ? ROWS FETCH NEXT 5 ROWS ONLY;";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            if (searchName != null && !searchName.isEmpty()) {
                st.setString(2, "%" + searchName + "%");
                st.setInt(3, (index - 1) * 5);
            } else {
                st.setInt(2, (index - 1) * 5);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                FeedbackResponse r = new FeedbackResponse();
                r.setCustomerID(rs.getInt("CustomerID"));
                r.setCustomerName(rs.getString("Name"));
                r.setStar(rs.getInt("Star"));
                r.setFeedback(rs.getString("Feedback"));
                r.setTime(rs.getString("FormattedTime"));
                r.setReplyID(rs.getInt("replyID"));
                r.setFeedbackID(rs.getInt("feedbackID"));
                list.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<FeedbackResponse> getAllFeedbackProduct(int productID) {
        List<FeedbackResponse> list = new ArrayList<>();
        String sql = " SELECT f.CustomerID,a.[Name], f.Star, f.Feedback, FORMAT(f.[Time], 'yyyy-MM-dd HH:mm') AS FormattedTime, f.ReplyID, f.FeedbackID \n" +
                "                                    FROM FeedbackProduct f \n" +
                "                                    JOIN Customer c ON f.CustomerID = c.CustomerID\n" +
                "                                     JOIN Account a ON c.AccountID = a.AccountID\n" +
                "                                     WHERE f.ProductID = ?\n" +
                "                                    AND f.Star IS NOT NULL \n" +
                "                                    AND f.Feedback IS NOT NULL \n" +
                "                                     AND f.[Time] IS NOT NULL \n" +
                "                                     ORDER BY f.ReplyID, f.FeedbackID ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                FeedbackResponse r = new FeedbackResponse();
                r.setCustomerID(rs.getInt("CustomerID"));
                r.setCustomerName(rs.getString("Name"));
                r.setStar(rs.getInt("Star"));
                r.setFeedback(rs.getString("Feedback"));
                r.setTime(rs.getString("FormattedTime"));
                r.setReplyID(rs.getInt("replyID"));
                r.setFeedbackID(rs.getInt("feedbackID"));
                list.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<FeedbackResponse> getReplyComment(int replyID) {
        List<FeedbackResponse> list = new ArrayList<>();
        String sql = "SELECT f.CustomerID,a.[Name], f.Star, f.Feedback, FORMAT(f.[Time], 'yyyy-MM-dd HH:mm') AS FormattedTime, f.ReplyID, f.FeedbackID \n" +
                "FROM FeedbackProduct f \n" +
                "JOIN Customer c ON f.CustomerID = c.CustomerID\n" +
                "JOIN Account a ON c.AccountID = a.AccountID\n" +
                "WHERE f.ReplyID= ?               \n" +
                "AND f.Feedback IS NOT NULL \n" +
                " AND f.[Time] IS NOT NULL \n" +
                "ORDER BY f.ReplyID, f.FeedbackID";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, replyID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                FeedbackResponse r = new FeedbackResponse();
                r.setCustomerID(rs.getInt("CustomerID"));
                r.setCustomerName(rs.getString("Name"));
                r.setStar(rs.getInt("Star"));
                r.setFeedback(rs.getString("Feedback"));
                r.setTime(rs.getString("FormattedTime"));
                r.setReplyID(rs.getInt("replyID"));
                r.setFeedbackID(rs.getInt("feedbackID"));
                list.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void deleteFeedbackProduct(int productID, int customerID) {
        String sql = "UPDATE [dbo].[FeedbackProduct]\n" +
                "   SET Star=NULL,Feedback=NULL,Time=NULL\n" +
                " WHERE ProductID=? and CustomerID=?\n";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            st.setInt(2, customerID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteReplyComment(int replyID) {
        String sql = "UPDATE [dbo].[FeedbackProduct]\n" +
                "   SET Star=NULL,Feedback=NULL,Time=NULL\n" +
                " WHERE ReplyID=? \n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, replyID);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countFeedbackInProduct(int productID) {
        String sql = "Select Count(*) from [dbo].[FeedbackProduct] f where ProductID=?\n" +
                "\t AND f.Feedback IS NOT NULL \n" +
                "    AND f.[Star] IS NOT NULL\n" +
                "    AND f.[Time] IS NOT NULL";


        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;


    }

    public int averageStarInProduct(int productID) {
        String sql = "SELECT  AVG(f.Star) AS AverageStar\n" +
                "FROM FeedbackProduct f\n" +
                "WHERE f.ProductID = ?\n" +
                "AND f.Star IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int getTotalCustomerFeedback(int customerID, String searchName) {
        String sql = "select count(*) from FeedbackProduct fp join Customer c on fp.CustomerID=c.CustomerID\n" +
                "  join Account a on c.AccountID=a.AccountID\n" +
                "  where ProductID=? and Star is Not Null and Feedback is Not Null and fp.[Time] is Not Null \n" +
                "  and a.[Name] like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            st.setNString(2, "%" + searchName + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static void main(String[] args) {
    FeedbackProductDAO feedbackProductDAO=new FeedbackProductDAO();
    feedbackProductDAO.addFeedbackProductAfterOrder(10,1,null,null,null,null);

    }
}



