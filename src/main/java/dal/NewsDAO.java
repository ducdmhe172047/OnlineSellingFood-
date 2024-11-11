package dal;

import model.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDAO extends DBContext {

    @Override
    protected Object getObjectByRs(ResultSet rs) throws SQLException {
        return new News(
                rs.getInt("NewsID"),
                rs.getInt("StaffID"),
                rs.getString("Title"),
                rs.getInt("ImgID"),
                rs.getTimestamp("Time").toLocalDateTime(),
                rs.getString("Content"),
                rs.getBoolean("Active")
        );
    }

    public boolean insert(News news) {
        String sql = "INSERT INTO News (StaffID, Title, ImgID, Time, Content, Active) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, news.getStaffID());
            st.setString(2, news.getTitle());
            st.setInt(3, news.getImgID());
            st.setTimestamp(4, Timestamp.valueOf(news.getTime()));
            st.setString(5, news.getContent());
            st.setBoolean(6, news.getActive());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting news: " + e.getMessage());
            return false;
        }
    }

    public List<News> getAll() {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while(rs.next()) {
                list.add((News)getObjectByRs(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all news: " + e.getMessage());
        }
        return list;
    }

    public News getById(int newsID) {
        String sql = "SELECT * FROM News WHERE NewsID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, newsID);
            try (ResultSet rs = st.executeQuery()) {
                if(rs.next()) {
                    return (News)getObjectByRs(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting news by ID: " + e.getMessage());
        }
        return null;
    }

    public boolean update(News news) {
        String sql = "UPDATE News SET StaffID=?, Title=?, Time=?, Content=?, Active=? WHERE NewsID=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, news.getStaffID());
            st.setString(2, news.getTitle());
            st.setTimestamp(3, Timestamp.valueOf(news.getTime()));
            st.setString(4, news.getContent());
            st.setBoolean(5, news.getActive());
            st.setInt(6, news.getNewsID());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.info("Error updating news: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int newsID) {
        String sql = "DELETE FROM News WHERE NewsID=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, newsID);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting news: " + e.getMessage());
            return false;
        }
    }

    public List<News> getByStaffId(int staffID) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE StaffID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, staffID);
            try (ResultSet rs = st.executeQuery()) {
                while(rs.next()) {
                    list.add((News)getObjectByRs(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting news by staff ID: " + e.getMessage());
        }
        return list;
    }

    public List<News> searchByTitle(String title) {
        List<News> list = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE Title LIKE ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + title + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add((News) getObjectByRs(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching news by title: " + e.getMessage());
        }
        return list;
    }

    public boolean createOrUpdateNews(int staffID,String title, Integer imgID,Date time, String content, Boolean active) {

        try {
            String query;
            if (imgID != null) {
                query = "INSERT INTO News (staffID, title, imgID, time, content, active) VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                query = "INSERT INTO News (staffID, title, time, content, active) VALUES (?, ?, ?, ?, ?)";
            }

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, staffID);
            stmt.setString(2, title);
            stmt.setDate(3, new java.sql.Date(time.getTime()));
            stmt.setString(4, content);
            stmt.setBoolean(5, active);

            if (imgID != null) {
                stmt.setInt(6, imgID); // Add image ID if present
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}