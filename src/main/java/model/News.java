package model;

import java.time.LocalDateTime;
import java.util.Date;

public class News {
    private int newsID;
    private int staffID;
    private String title;
    private int imgID;
    private LocalDateTime time;
    private String content;
    private Boolean active;

    public News() {
    }

    // Constructor without newsID (for creating new news)
    public News(int staffID, String title, int imgID, LocalDateTime time, String content, Boolean active) {
        this.staffID = staffID;
        this.title = title;
        this.imgID = imgID;
        this.time = time;
        this.content = content;
        this.active = active;
    }

    // Constructor with all fields (for retrieving news from database)
    public News(int newsID, int staffID, String title, int imgID, LocalDateTime time, String content, Boolean active) {
        this.newsID = newsID;
        this.staffID = staffID;
        this.title = title;
        this.imgID = imgID;
        this.time = time;
        this.content = content;
        this.active = active;
    }

    public News(int newsID, int staffID, String title, LocalDateTime time, String content, Boolean active) {
        this.newsID = newsID;
        this.staffID = staffID;
        this.title = title;
        this.time = time;
        this.content = content;
        this.active = active;
    }

    // Getters and setters
    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}