package dto;

import java.util.List;

public class FeedbackResponse {
    private int customerID;
    private String customerName;
    private int star;
    private String feedback,time;
    private int replyID;
    private int feedbackID;
    private List<FeedbackResponse> replies;


    public FeedbackResponse() {
    }

    public FeedbackResponse(int customerID,String customerName, int star, String feedback, String time,int replyID,int feedbackID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.star = star;
        this.feedback = feedback;
        this.time = time;
        this.replyID = replyID;
        this.feedbackID = feedbackID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReplyID() {
        return replyID;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }
    public List<FeedbackResponse> getReplies() {
        return replies;
    }

    public void setReplies(List<FeedbackResponse> replies) {
        this.replies = replies;
    }
}
